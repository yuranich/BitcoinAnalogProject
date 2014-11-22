package mipt.infosec.bitcoin.test;

import java.io.IOException;

import mipt.infosec.bitcoin.network.Notifier;
import mipt.infosec.bitcoin.network.Receiver;
import mipt.infosec.ejb.Transaction;

public class TestNetwork {

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Usage: java TestNetwork <address of a second node>");
			return;
		}
		Thread server = new MyThread();
		server.start();
		
		try {
			Notifier notifier = new Notifier();
			Transaction tr = new Transaction();
			tr.createTransaction(1, 2, 10);
			notifier.sendNewNodeInfo();
			notifier.sendNewTransactionMessage(tr.getTransaction(Transaction.getMaxId()));                   
			notifier.sendSuccessfulTransactionMessage(tr.getTransaction(Transaction.getMaxId()));
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
