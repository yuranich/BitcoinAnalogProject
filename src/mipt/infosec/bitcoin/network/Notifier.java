package mipt.infosec.bitcoin.network;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;
import mipt.infosec.utils.NetworkUtils;

public class Notifier {
	
	private final Properties addresses;

	private final MessageInstance message = new MessageInstance();
	
	public Notifier() throws IOException {
		InputStream stream = new FileInputStream("addresses.properties");
		this.addresses = new Properties();
		addresses.load(stream);
	}

	public Properties getAddresses() {
		return addresses;
	}
	
	public void sendNewTransactionMessage(Transaction transaction) throws IOException {
		message.setType(Protocol.NEW_TRANSACTION);
		message.setFrom(transaction.getFrom());
		message.setTo(transaction.getTo());
		message.setTransactionId(transaction.getId());
		message.setTransactionHash(transaction.getHash());
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
	
	public void sendBlockCreatedMessage(Block block, Transaction transaction) throws IOException {
		message.setType(Protocol.SUCCESSFUL_TRANSACTION);
		message.setFrom(transaction.getFrom());
		message.setTo(transaction.getTo());
		message.setMoney(transaction.getMoney());
		message.setTransactionId(transaction.getId());
		message.setTransactionHash(transaction.getHash());
		message.setBlockId(block.getId());
		message.setBlockHash(block.getHash());
		message.setPrevBlockHash(block.getPrevHash());
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
	
	public void sendNewNodeInfo() throws IOException {
		message.setType(Protocol.NEW_NODE);
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
}
