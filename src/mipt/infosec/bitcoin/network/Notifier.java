package mipt.infosec.bitcoin.network;

import java.io.IOException;
import java.util.Properties;

import mipt.infosec.ejb.Transaction;
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
	
	public void sendNewTransactionMessage(Transaction transaction) throws IOException {
		message.setType(Protocol.NEW_TRANSACTION);
		message.setFrom(transaction.getFrom());
		message.setTo(transaction.getTo());
		message.setTransactionId(transaction.getId());
		message.setTransactionHash(transaction.getHash());
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
	
	public void sendSuccessfulTransactionMessage(Transaction transaction) throws IOException {
		message.setType(Protocol.SUCCESSFUL_TRANSACTION);
		message.setFrom(transaction.getFrom());
		message.setTo(transaction.getTo());
		message.setMoney(transaction.getMoney());
		message.setTransactionId(transaction.getId());
		message.setTransactionHash(transaction.getHash());
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
	
	public void sendNewNodeInfo() throws IOException {
		message.setType(Protocol.NEW_NODE);
		NetworkUtils.sendNotificationToAll(addresses, message.formMessage());
	}
}
