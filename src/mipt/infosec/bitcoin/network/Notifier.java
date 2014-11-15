package mipt.infosec.bitcoin.network;

import java.io.IOException;
import java.util.Properties;

import mipt.infosec.utils.NetworkUtils;

public class Notifier {
	
	private final Properties addresses;

	private final MessageInstance message = new MessageInstance();
	
	public Notifier(Properties addresses) {
		this.addresses = addresses;
	}

	public Properties getAddresses() {
		return addresses;
	}
	
	public void sendNewTransactionMessage() throws IOException {
		message.setType(Protocol.NEW_TRANSACTION);
		message.setData("New transaction created!");
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
	
	public void sendSuccessfulTransactionMessage() throws IOException {
		message.setType(Protocol.SUCCESSFUL_TRANSACTION);
		message.setData("Transaction successfully executed!");
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
	
	public void sendNewNodeInfo() throws IOException {
		message.setType(Protocol.NEW_NODE);
		message.setData("New node created!");
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
}
