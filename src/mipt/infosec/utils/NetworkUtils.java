package mipt.infosec.utils;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

import mipt.infosec.bitcoin.network.Protocol;

import org.apache.commons.io.IOUtils;

public class NetworkUtils {

	public static void sendMessage (Collection<String> message) {
		try (Socket receiver = new Socket("address", Protocol.CONNECTION_PORT)) {
			OutputStream out = receiver.getOutputStream();
			IOUtils.writeLines(message, "\n", out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
