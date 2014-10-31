package mipt.infosec.bitcoin.network;

import java.util.Properties;

import mipt.infosec.utils.NetworkUtils;

public class Notifier {
	
	private final Properties addresses;

	private MessageInstance message;
	
	public Notifier(Properties addresses) {
		this.addresses = addresses;
	}

	public void sendNewTransactionMessage() {
		message.setType(Protocol.TYPES.NEW_TRANSACTION.toString());
		message.setData("New transaction created!");
		NetworkUtils.sendMessage (message.formMessage());
	}
	
	public void sendSuccessfulTransactionMessage() {
		message.setType(Protocol.TYPES.SUCCESSFUL_TRANSACTION.toString());
		message.setData("Transaction successfully executed!");
		NetworkUtils.sendMessage (message.formMessage());
	}
	
	public void sendNewNodeInfo() {
		message.setType(Protocol.TYPES.NEW_NODE.toString());
		message.setData("New node created!");
		NetworkUtils.sendMessage (message.formMessage());
	}
	


	public Properties getAddresses() {
		return addresses;
	}
}
