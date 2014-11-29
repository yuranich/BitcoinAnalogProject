package mipt.infosec.bitcoin.network;

import java.util.ArrayList;
import java.util.List;

public class MessageInstance {
	
	private int type;
	private String from;
	private String to;
	private int money;
	private int transactionId;
	private String transactionHash;
	private int blockId;
	private String blockHash;
	private String prevBlockHash;
	
	public MessageInstance () {
		
	}
	
	public MessageInstance (List<String> message) {
		parseMessage(message);
	}
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}
	
	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public String getBlockHash() {
		return blockHash;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	public String getPrevBlockHash() {
		return prevBlockHash;
	}

	public void setPrevBlockHash(String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
	}

	public void parseMessage(List<String> message) {
		type = Integer.parseInt(message.get(0));
		switch (type) {
			case Protocol.NEW_NODE:
				//TODO: some actions
				break;
			case Protocol.NEW_TRANSACTION:
				from = message.get(1);
				to   = message.get(2);
				transactionId   = Integer.parseInt(message.get(3));
				transactionHash = message.get(4);
				break;
			case Protocol.SUCCESSFUL_TRANSACTION:
				from  = message.get(1);
				to    = message.get(2);
				money = Integer.parseInt(message.get(3));
				transactionId   = Integer.parseInt(message.get(4));
				transactionHash = message.get(5);
				blockId = Integer.parseInt(message.get(6));
				blockHash = message.get(7);
				prevBlockHash = message.get(8);
				break;			
			default: throw new RuntimeException("Unknown message type!");
		}
	}
	
	public List<String> formMessage() {
		List<String> newMessage = new ArrayList<>();
		newMessage.add(Integer.toString(type));
		switch(type) {
			case Protocol.NEW_NODE:
				//TODO: write some actions
				break;
			case Protocol.NEW_TRANSACTION:
				newMessage.add(from);
				newMessage.add(to);
				newMessage.add(Integer.toString(transactionId));
				newMessage.add(transactionHash);
				break;
			case Protocol.SUCCESSFUL_TRANSACTION:
				newMessage.add(from);
				newMessage.add(to);
				newMessage.add(Integer.toString(money));
				newMessage.add(Integer.toString(transactionId));
				newMessage.add(transactionHash);
				newMessage.add(Integer.toString(blockId));
				newMessage.add(blockHash);
				newMessage.add(prevBlockHash);
				break;
			default: throw new RuntimeException("Unknown transaction type!");
		}
		return newMessage;
	}
	
	public void dumpContent() {
		System.out.println("[Server says]: The type of message: " + type
					   + "\n               from: " + from
					   + "\n               to: " + to
					   + "\n               money: " + money
					   + "\n               transaction id: " + transactionId
					   + "\n               transaction hash: " + transactionHash
					   + "\n               block id: " + blockId
					   + "\n               block hash: " + blockHash
					   + "\n               prev block hash: " + prevBlockHash);
	}
}
