package mipt.infosec.bitcoin.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;

import org.apache.commons.io.IOUtils;

public class Receiver {
	
	public static final String MY_ADDR = getIpAddress();

	
	public void receive() {
        try (ServerSocket welcomeSocket = new ServerSocket(Protocol.CONNECTION_PORT)) {
        	while(true)
        	{
        		try {
        			System.out.println("My addr is " + MY_ADDR);
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
	
	public static String getIpAddress() { 
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                        String ipAddress=inetAddress.getHostAddress().toString();
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("Socket exception in GetIP Address of Utilities");
        }
        return null; 
	}
	
	public void updateDBInfo(MessageInstance message) {
		switch (message.getType()) {
			case Protocol.NEW_NODE: //TODO: some actions.
				break;
			case Protocol.NEW_TRANSACTION: 
				new Transaction().createTransaction(message.getFrom(), message.getTo(), message.getMoney());
				break;
			case Protocol.SUCCESSFUL_TRANSACTION:
//				new Transaction().updateTransaction(message.getFrom(), message.getTo(), 
//						message.getMoney(), message.getTransactionId(), message.getTransactionHash());
//				Block recv = new Block();
//				recv.createReceivedBlock(message.getBlockId(), message.getBlockHash(), message.getPrevBlockHash());
//				recv.updateblock(message.getBlockId(), message.getTransactionId());
				break;
			default: throw new RuntimeException("Undefined type of message!!!");
		}
		
		message.dumpContent();
	}
}
