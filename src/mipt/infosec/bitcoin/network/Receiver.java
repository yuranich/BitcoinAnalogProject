package mipt.infosec.bitcoin.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class Receiver {

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
	
	public void setStaticIP() throws IOException {
		String str1="192.168.0.201";
		String str2="255.255.255.0";
		String[] command1 = { "netsh", "interface", "ip", "set", "address",
		"name=", "Local Area Connection" ,"source=static", "addr=",str1,
		"mask=", str2};
		Runtime.getRuntime().exec(command1);
	}
	
	public void updateDBInfo(MessageInstance message) {
		message.dumpContent();
	}
}
