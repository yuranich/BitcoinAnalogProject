package mipt.infosec.bitcoin.network;

import java.util.ArrayList;
import java.util.List;

public class MessageInstance {
	
	private String type;
	private String data;
	
	public MessageInstance () {
		
	}
	
	public MessageInstance (List<String> message) {
		parseMessage(message);
	}
	
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	//TODO: add more fields into class and made actual parse.
	public void parseMessage(List<String> message) {
		type = message.get(0);
		data = message.subList(1, message.size()).toString();
	}
	
	public List<String> formMessage() {
		List<String> newMessage = new ArrayList<>(2);
		newMessage.add(type);
		newMessage.add(data);
		return newMessage;
	}
	public void dumpContent() {
		System.out.println("The type of message: " + type + "\nData: " + data);
	}

}
