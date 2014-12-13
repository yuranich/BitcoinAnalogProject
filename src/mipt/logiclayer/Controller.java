package mipt.logiclayer;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

import mipt.infosec.bitcoin.network.Notifier;
import mipt.infosec.bitcoin.network.Receiver;
import mipt.infosec.bitcoin.wallet.Wallet;
import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;
import mipt.infosec.secutils.crypto.DigitalSignature;
import mipt.infosec.secutils.crypto.StringKeyPair;
import mipt.infosec.secutils.hash.Stribog;

public class Controller {

	private static final String UTF_8 = "UTF-8";
	public static final String KEYS_FILE = "Keys.properties";
	
	//This method is used for creating and sending broadcast about new transaction created
	public static Boolean createTransaction(String from, String to, double money, PrivateKey pr) throws IOException {
		Transaction.createTransaction(from, to, money);
		Transaction.setSignatureForTransaction(Transaction.getMaxId(),pr);
		Notifier notifier = new Notifier();
		notifier.sendNewTransactionMessage(Transaction.getTransaction(Transaction.getMaxId()));
		
		return Boolean.TRUE;
	}
	
	//This method is used for generating and sending broadcast about new block generated
	public static Boolean createBlock(Transaction transaction) throws Exception {
		
		if (DigitalSignature.checkSignature(Integer.toString(transaction.getId()), 
				transaction.getSignature().getBytes(UTF_8), getPublicKeyFromFile(transaction.getFrom())) != true) {
			//TODO show message to user;
			
			//Transaction.deleteTransaction(transaction.getId());
			return false;
		}
		Stribog stribog = new Stribog(512);
		Block block = new Block();
		int i = 0;
		while(true) {
			if ((stribog.getHash(serialize(block))[0] & 0x80) != 0) {
				break;
			}
			i++;
			if (i > 100000) {
				System.out.println("You not gonna make it!!!");
				break;
			}
			block.createBlock();
		}
		Notifier notifier = new Notifier();
		
		Transaction trans = Transaction.getEmissionTransaction();
		if (trans == null) {
			Transaction.createReceivedTransaction(0, "", Receiver.MY_ADDR, 10, "no hash", "no signature");
			trans = Transaction.getTransaction(0);
		}
		block.updateblock(Block.getMaxId(), transaction.getId());
		block.updateblock(Block.getMaxId(), trans.getId());
		notifier.sendBlockCreatedMessage(block.getBlock(Block.getMaxId()), transaction, trans);
		
		addToWallet(trans.getMoney());
		
		return Boolean.TRUE;
	}
	
	public static StringKeyPair generateKeys() throws NoSuchAlgorithmException, IOException {

		StringKeyPair pair = DigitalSignature.getKeyPair();
		Properties prop = new Properties();
		prop.load(new FileInputStream(KEYS_FILE));
		if (prop.containsKey(Receiver.MY_ADDR)) {
			prop.remove(Receiver.MY_ADDR);
		}
		prop.put(Receiver.MY_ADDR, pair.getPublicKey());
		prop.list(new PrintStream(KEYS_FILE));
		
		Notifier notifier = new Notifier();
		notifier.sendPublicKey(pair.getPublicKey());
		return pair;
	}
	
	public static PublicKey getPublicKeyFromFile(String addr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(KEYS_FILE));
		
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(((String)prop.get(addr)).getBytes(UTF_8));
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    return kf.generatePublic(spec);
	}
	
	public static void addToWallet (double quantity) {
		Wallet.getInstance().increaseSumm(quantity);
		System.out.println(Wallet.getInstance().getSumm());
	}
	
	public static byte[] serialize(Object object) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(bytes);
        output.writeObject(object);
        return bytes.toByteArray();
    }
}
