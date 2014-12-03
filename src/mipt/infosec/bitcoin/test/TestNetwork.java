package mipt.infosec.bitcoin.test;

import java.io.IOException;

import mipt.infosec.bitcoin.network.Notifier;
import mipt.infosec.bitcoin.network.Receiver;
import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;
import mipt.logiclayer.Controller;

public class TestNetwork {

	public static void main(String[] args) {
		
		Thread server = new MyThread();
		server.start();
		
		try {
			Controller.createTransaction(Receiver.MY_ADDR, "192.168.0.109", 10);
			//Transaction trans = new Transaction();
			//Controller.createBlock(trans.getTransaction(Transaction.getMaxId()));
		} catch ( IOException e) {
			e.printStackTrace();
			System.out.println("Messages are not sended");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
