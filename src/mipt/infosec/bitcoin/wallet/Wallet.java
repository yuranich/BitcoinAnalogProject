package mipt.infosec.bitcoin.wallet;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mipt.infosec.bitcoin.network.Receiver;
import mipt.infosec.ejb.Block;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Wallet {
	private double summ;
	private static Wallet instance;
	private static double INITIAL_MONEY = 10.0;
	
	private Wallet() {
		summ = INITIAL_MONEY;
		
		File file = new File(Block.FILE_NAME);
		if (!file.exists() || file.length() == 0) {
			return;
		}
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nl = document.getElementsByTagName("transaction");
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (Receiver.MY_ADDR.equals(node.getElementsByTagName("to").item(0).getTextContent())) {					
					summ += Double.parseDouble(node.getElementsByTagName("coin").item(0).getTextContent());
				}
			}
		}catch (ParserConfigurationException e) {
			System.out.println("Can't parse file");
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static Wallet getInstance() {
		if (instance == null) {
			instance = new Wallet();
		}
		return instance;
	}
	
	public double getSumm() {
		return this.summ;
	}
	
	public double increaseSumm(double money) {
		this.summ = this.summ + money;
		return this.summ;
	}
	
	public double reduceSumm(double money) {
		this.summ = this.summ - money;
		return this.summ;	
	}
}
