package mipt.infosec.bitcoin.network;

import java.util.ArrayList;
import java.util.List;

public class MessageInstance {
	
	private int type;
	private String from;
	private String to;
	private double money;
	private int transactionId;
	private String transactionHash;
	private int blockId;
	private String blockHash;
	private String prevBlockHash;
	private int emissionTransId;
	private String emissionTo;
	private String emissionFrom;
	private double emissMoney;
	private String emissionHash;
	private String signature;
	private String emissionSignature;
	
	public String getEmissionFrom() {
		return emissionFrom;
	}

	public void setEmissionFrom(String emissionFrom) {
		this.emissionFrom = emissionFrom;
	}

	public int getEmissionTransId() {
		return emissionTransId;
	}

	public void setEmissionTransId(int emissionTransId) {
		this.emissionTransId = emissionTransId;
	}

	public String getEmissionTo() {
		return emissionTo;
	}

	public void setEmissionTo(String emissionTo) {
		this.emissionTo = emissionTo;
	}

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

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
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

	
	public String getEmissionHash() {
		return emissionHash;
	}

	public void setEmissionHash(String emissionHash) {
		this.emissionHash = emissionHash;
	}

	public double getEmissMoney() {
		return emissMoney;
	}

	public void setEmissMoney(double emissMoney) {
		this.emissMoney = emissMoney;
	}
	
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getEmissionSignature() {
		return emissionSignature;
	}

	public void setEmissionSignature(String emissionSignature) {
		this.emissionSignature = emissionSignature;
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
				money = Double.parseDouble(message.get(3));
				transactionId   = Integer.parseInt(message.get(3));
				transactionHash = message.get(4);
				signature = message.get(5);
				break;
			case Protocol.SUCCESSFUL_TRANSACTION:
				from  = message.get(1);
				to    = message.get(2);
				money = Double.parseDouble(message.get(3));
				transactionId   = Integer.parseInt(message.get(4));
				transactionHash = message.get(5);
				signature = message.get(6);
				blockId = Integer.parseInt(message.get(7));
				blockHash = message.get(8);
				prevBlockHash = message.get(9);
				emissionFrom = message.get(10);
				emissionTo   = message.get(11);
				emissMoney   = Double.parseDouble(message.get(12));
				emissionTransId = Integer.parseInt(message.get(13));
				emissionHash    = message.get(14);
				emissionSignature = message.get(15);
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
				newMessage.add(Double.toString(money));
				newMessage.add(Integer.toString(transactionId));
				newMessage.add(transactionHash);
				newMessage.add(signature);
				break;
			case Protocol.SUCCESSFUL_TRANSACTION:
				newMessage.add(from);
				newMessage.add(to);
				newMessage.add(Double.toString(money));
				newMessage.add(Integer.toString(transactionId));
				newMessage.add(transactionHash);
				newMessage.add(Integer.toString(blockId));
				newMessage.add(blockHash);
				newMessage.add(prevBlockHash);
				newMessage.add(emissionFrom);
				newMessage.add(emissionTo);
				newMessage.add(Double.toString(emissMoney));
				newMessage.add(Integer.toString(emissionTransId));
				newMessage.add(emissionHash);
				newMessage.add(emissionSignature);
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
					   + "\n               transaction signature: " + signature
					   + "\n               block id: " + blockId
					   + "\n               block hash: " + blockHash
					   + "\n               prev block hash: " + prevBlockHash
					   + "\n               emission from: " + emissionFrom
					   + "\n               emission to: " + emissionTo
					   + "\n               emission money: " + emissMoney
					   + "\n               emission id: " + emissionTransId
					   + "\n               emission hash: " + emissionHash
					   + "\n               emission signature: " + emissionSignature);
	}
}
