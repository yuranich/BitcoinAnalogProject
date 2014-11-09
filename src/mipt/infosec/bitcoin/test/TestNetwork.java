package mipt.infosec.bitcoin.test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import mipt.infosec.bitcoin.network.Notifier;
import mipt.infosec.bitcoin.network.Receiver;

public class TestNetwork {

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Usage: java <programm> <address of a second node");
			return;
		}
		Thread server = new MyThread();
		server.start();
		
		Properties prop = new Properties();
		prop.put("first", args[0]);
		Notifier notifier = new Notifier(prop);
		try {
			notifier.sendNewNodeInfo();
			notifier.sendNewTransactionMessage();
			notifier.sendSuccessfulTransactionMessage();
			System.out.println("Messages are sended");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Messages are not sended");
		}
	}
}

class MyThread extends Thread {
	
	@Override
	public void run () {
		Receiver receiver = new Receiver();
		try {
			receiver.setStaticIP();
		} catch (IOException e) {
			e.printStackTrace();
		}
		receiver.receive();
	}
}
