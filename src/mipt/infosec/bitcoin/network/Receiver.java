package mipt.infosec.bitcoin.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import mipt.infosec.ejb.Transaction;

import org.apache.commons.io.IOUtils;

public class Receiver {
	
	public static final String MY_ADDR = "192.168.0.101";

	public void receive() {
        try (ServerSocket welcomeSocket = new ServerSocket(Protocol.CONNECTION_PORT)) {
        	while(true)
        	{
        		try {
        			System.out.println("Before acception of socket connection");
        			Socket connectionSocket = welcomeSocket.accept();
        			System.out.println("Socket connection accepted");
        			InputStreamReader inputFromParticipant = new InputStreamReader(connectionSocket.getInputStream());
        			MessageInstance message = new MessageInstance(IOUtils.readLines(inputFromParticipant));
        			updateDBInfo(message);
        		} catch (IOException e) {
        			e.printStackTrace();
        		}        	
        	}
        } catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	public void updateDBInfo(MessageInstance message) {
		switch (message.getType()) {
			case Protocol.NEW_NODE: //TODO: some actions.
				break;
			case Protocol.NEW_TRANSACTION: 
				new Transaction().createTransaction(message.getFrom(), message.getTo(), message.getMoney());
				break;
			case Protocol.SUCCESSFUL_TRANSACTION:
				new Transaction().updateTransaction(message.getFrom(), message.getTo(), 
						message.getMoney(), message.getTransactionId(), message.getTransactionHash());
				//TODO: update block;
				break;
			default: throw new RuntimeException("Undefined type of message!!!");
		}
		
		message.dumpContent();
	}
}
