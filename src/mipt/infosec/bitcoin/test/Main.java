package mipt.infosec.bitcoin.test;

import java.util.Properties;

import mipt.infosec.bitcoin.network.Notifier;
import mipt.infosec.bitcoin.network.Receiver;

public class Main {
	public static void main(String[] argv) {
		
		if (argv.length < 1) {
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
	}

}

class MyThread extends Thread {
	
	@Override
	public void run () {
		Receiver receiver = new Receiver();
		receiver.receive();
	}
}