package mipt.infosec.bitcoin.network;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;
import mipt.logiclayer.Controller;

import org.apache.commons.io.IOUtils;

public class Receiver {
	
	public static final String MY_ADDR = getIpAddress();

	
	public void receive() {
        try (ServerSocket welcomeSocket = new ServerSocket(Protocol.CONNECTION_PORT)) {
        	System.out.println("My addr is " + MY_ADDR);
        	while(true)
        	{
        		try {
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
	
	public static void updateDBInfo(MessageInstance message) {
		switch (message.getType()) {
			case Protocol.NEW_PUBLIC_KEY:
				updatePublicKeys(message.getKeyAddress(), message.getKeyValue());
				break;
			case Protocol.NEW_TRANSACTION: 
				Transaction.createReceivedTransaction(message.getTransactionId(), message.getFrom(), 
						message.getTo(), message.getMoney(), message.getTransactionHash(), message.getSignature());
				break;
			case Protocol.SUCCESSFUL_TRANSACTION:
				Transaction.updateTransaction(message.getFrom(), message.getTo(), 
						message.getMoney(), message.getTransactionId(), message.getTransactionHash(), message.getSignature());
				
				if (MY_ADDR.equals(message.getTo())) {
					Controller.addToWallet(message.getMoney());
				}
				
				if (Transaction.getEmissionTransaction() == null) {
					Transaction.createReceivedTransaction(message.getEmissionTransId(), message.getEmissionFrom(), 
							message.getEmissionTo(), message.getEmissMoney(), message.getEmissionHash(), message.getEmissionSignature());
				} else {					
					Transaction.updateTransaction(message.getEmissionFrom(), message.getEmissionTo(), 
							message.getEmissMoney(), message.getEmissionTransId(), message.getEmissionHash(), message.getEmissionSignature());
				}
				
				Block recv = new Block();
				recv.createReceivedBlock(message.getBlockId(), message.getBlockHash(), message.getPrevBlockHash());
				recv.updateblock(message.getBlockId(), message.getTransactionId());
				recv.updateblock(message.getBlockId(), message.getEmissionTransId());
				break;
			default: throw new RuntimeException("Undefined type of message!!!");
		}
		
		message.dumpContent();
	}
	
	public static void updatePublicKeys(String addr, String key) {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(Controller.KEYS_FILE));
			
			if (prop.containsKey(addr)) {
				prop.remove(addr);
			}
			prop.put(addr, key);
			prop.list(new PrintStream(Controller.KEYS_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
