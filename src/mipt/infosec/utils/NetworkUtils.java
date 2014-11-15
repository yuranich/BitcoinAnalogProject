package mipt.infosec.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import mipt.infosec.bitcoin.network.Protocol;

import org.apache.commons.io.IOUtils;

public class NetworkUtils {

	public static void sendNotificationToAll(Properties addresses, Collection<String> message) throws IOException {
		for (Object value : addresses.values()) {
			sendNotification((String)value, message);
		}
	}
	
	public static void sendNotification (String address, Collection<String> message) throws IOException {
		try (Socket receiver = new Socket(address, Protocol.CONNECTION_PORT)) {
			OutputStream out = receiver.getOutputStream();
			IOUtils.writeLines(message, "\n", out);
		} 
	}
}
