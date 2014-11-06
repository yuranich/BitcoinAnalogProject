package mipt.infosec.bitcoin.test;



import mipt.infosec.bitcoin.network.Notifier;
import mipt.infosec.bitcoin.network.Receiver;
import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;

public class Main {
	public static void main(String[] argv) {
		
	/*	if (argv.length < 1) {
			System.out.println("Usage: java <programm> <address of a second node");
		}
		Thread server = new MyThread();
		server.start();
		
		Properties prop = new Properties();
		prop.put("first", argv[0]);
		Notifier notifier = new Notifier(prop);
		notifier.sendNewNodeInfo();
		notifier.sendNewTransactionMessage();
		notifier.sendSuccessfulTransactionMessage();
*/
		Transaction tr = new Transaction();
	//	tr.createTransaction(11,232, 321);
		System.out.println(Block.getMaxId());
		Block b = new Block();
		b.createBlock();
		b.addTransaction(114,222);
	}

}

class MyThread extends Thread {
	
	@Override
	public void run () {
		Receiver receiver = new Receiver();
		receiver.receive();
	}
}

