package mipt.infosec.bitcoin.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class Receiver {

	public void Receive() {
        try (ServerSocket welcomeSocket = new ServerSocket(Protocol.CONNECTION_PORT)) {
        	while(true)
        	{
        		try {
        			Socket connectionSocket = welcomeSocket.accept();
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
		message.dumpContent();
	}
}
