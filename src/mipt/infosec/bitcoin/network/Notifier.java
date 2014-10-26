package mipt.infosec.bitcoin.network;

import java.util.Properties;

public class Notifier {
	
	private final Properties addresses;

	
	public Notifier(Properties addresses) {
		this.addresses = addresses;
	}

	public void sendNewTransactionMessage() {
		
	}
	
	public void sendSuccessfulTransactionMessage() {
		
	}
	
	public void sendNewNodeInfo() {
		
	}
}
