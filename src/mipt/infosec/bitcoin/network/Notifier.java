package mipt.infosec.bitcoin.network;

import java.net.UnknownHostException;
import java.util.Properties;

import mipt.infosec.utils.NetworkUtils;

public class Notifier {
	
	private final Properties addresses;

	private final MessageInstance message = new MessageInstance();
	
	public Notifier(Properties addresses) {
		this.addresses = addresses;
	}

	public void sendNewTransactionMessage() throws UnknownHostException {
		message.setType(Protocol.NEW_TRANSACTION);
		message.setData("New transaction created!");
		NetworkUtils.sendMessage (addresses.getProperty("first"), message.formMessage());
	}
	
	public void sendSuccessfulTransactionMessage() throws UnknownHostException {
		message.setType(Protocol.SUCCESSFUL_TRANSACTION);
		message.setData("Transaction successfully executed!");
		NetworkUtils.sendMessage (addresses.getProperty("first"), message.formMessage());
	}
	
	public void sendNewNodeInfo() throws UnknownHostException {
		message.setType(Protocol.NEW_NODE);
		message.setData("New node created!");
		NetworkUtils.sendMessage (addresses.getProperty("first"), message.formMessage());
	}
	


	public Properties getAddresses() {
		return addresses;
	}
}
