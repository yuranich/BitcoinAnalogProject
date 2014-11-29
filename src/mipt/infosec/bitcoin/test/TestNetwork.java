package mipt.infosec.bitcoin.test;

import java.io.IOException;

import mipt.infosec.bitcoin.network.Notifier;
import mipt.infosec.bitcoin.network.Receiver;
import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;

public class TestNetwork {

	public static void main(String[] args) {
		
		Thread server = new MyThread();
		server.start();
		
		try {
			Notifier notifier = new Notifier();
			Transaction tr = new Transaction();
			tr.createTransaction("1.1.1.1", "2.2.2.2", 10);
			Block block = new Block();
			block.createBlock();
			notifier.sendNewNodeInfo();
			notifier.sendNewTransactionMessage(tr.getTransaction(Transaction.getMaxId()));                   
			//notifier.sendBlockCreatedMessage(block.getBlock(Block.getMaxId()), tr.getTransaction(Transaction.getMaxId()));
			System.out.println("Messages are sended");
		} catch ( IOException e) {
			e.printStackTrace();
			System.out.println("Messages are not sended");
		}
	}
}

class MyThread extends Thread {
	
	@Override
	public void run () {
		Receiver receiver = new Receiver();
		receiver.receive();
	}
}
