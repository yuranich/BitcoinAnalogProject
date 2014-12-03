package mipt.infosec.ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mipt.infosec.secutils.hash.Stribog;
import mipt.infosec.utils.XmlUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Transaction {
	public static final String filename = "transaction.xml";
	public static final String defaultfile = "<transactions>\n</transactions>";
	private String from = "";
	private String to = "";
	private double money = 0;
	private String hash = "";
	private String signature = "";
	private int id = 0;

	public static void updateTransaction(String from, String to, double money, int id, String hash) {
		File file = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nl = document.getElementsByTagName("transaction");
			Element trans = null;
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (Integer.parseInt(node.getAttribute("id")) == id) {
					
					trans = node;
				}
			}
			trans.getElementsByTagName("from").item(0).setTextContent(from);
			trans.getElementsByTagName("to").item(0).setTextContent(to);
			trans.getElementsByTagName("coin").item(0).setTextContent(Double.toString(money));
			trans.getElementsByTagName("hash").item(0).setTextContent(hash);
			try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
				out.print(XmlUtils.toXML(document));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void createTransaction(String from, String to, double money) {
		File f = new File(filename);

		if (!f.exists() || f.length() == 0) {
			try (PrintStream out = new PrintStream(new FileOutputStream(
					filename))) {
				out.print("<transactions>" + '\n' + "</transactions>");

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		int maxId = Transaction.getMaxId();
			
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(f);

			Element root = document.getDocumentElement();
			Element node = document.createElement("transaction");
			node.setAttribute("id", Integer.toString(maxId + 1));
			root.appendChild(node);
			root.setAttribute("maxid", Integer.toString(maxId+1));
			Element sendFrom = document.createElement("from");
			sendFrom.appendChild(document.createTextNode(from));
			Element sendTO = document.createElement("to");
			sendTO.appendChild(document.createTextNode(to));
			Element coin = document.createElement("coin");
			coin.appendChild(document.createTextNode(Double.toString(money)));
			byte[] id = BigInteger.valueOf(maxId + 1).toByteArray();
			Stribog stb = new Stribog(256);
			byte[] hash = stb.getHash(id);
			BigInteger bhash = new BigInteger(hash);

			Element hashs = document.createElement("hash");
			hashs.appendChild(document.createTextNode(bhash.toString()));
			node.appendChild(sendFrom);
			node.appendChild(sendTO);
			node.appendChild(coin);
			node.appendChild(hashs);

			try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
				out.print(XmlUtils.toXML(document));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void createReceivedTransaction(int id, String from, String to, double money, String hash) {
		boolean not_max = false;
		if (id <= Transaction.getMaxId()) {
			if (Transaction.getTransaction(id) != null) {
				Transaction.updateTransaction(from, to, money, id, hash);
				return;
			}
			not_max = true;
		}
		File f = new File(filename);

		if (!f.exists() || f.length() == 0) {
			try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
				out.print("<transactions>" + '\n' + "</transactions>");

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(f);

			Element root = document.getDocumentElement();
			Element node = document.createElement("transaction");
			node.setAttribute("id", Integer.toString(id));
			root.appendChild(node);
			
			if (!not_max)
				root.setAttribute("maxid", Integer.toString(id));
			
			Element sendFrom = document.createElement("from");
			sendFrom.appendChild(document.createTextNode(from));
			Element sendTO = document.createElement("to");
			sendTO.appendChild(document.createTextNode(to));
			Element coin = document.createElement("coin");
			coin.appendChild(document.createTextNode(Double.toString(money)));

			Element hashs = document.createElement("hash");
			hashs.appendChild(document.createTextNode(hash));
			node.appendChild(sendFrom);
			node.appendChild(sendTO);
			node.appendChild(coin);
			node.appendChild(hashs);

			try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
				out.print(XmlUtils.toXML(document));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static int getMaxId() {
		File file = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			Element root = document.getDocumentElement();
		
			return "".equals(root.getAttribute("maxid"))? 0: Integer.parseInt(root.getAttribute("maxid"));

		} catch (ParserConfigurationException e) {
			System.out.println("Can parse file");
			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public static Transaction getTransaction(int id) {
		File file = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nl = document.getElementsByTagName("transaction");
			Element trans = null;
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (Integer.parseInt(node.getAttribute("id")) == id) {
					trans = node;
				}
			}
			if (trans == null) {
				return null;
			}
			Transaction tr = new Transaction();
			tr.setId(id);
			tr.setFrom(trans.getElementsByTagName("from").item(0).getTextContent());
			tr.setTo(trans.getElementsByTagName("to").item(0).getTextContent());
			tr.setMoney(Double.parseDouble(trans.getElementsByTagName("coin").item(0).getTextContent()));
			tr.setHash(trans.getElementsByTagName("hash").item(0).getTextContent());
			return tr;

		} catch (ParserConfigurationException e) {
			System.out.println("Can parse file");
			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}
	
	public static void deleteTransaction(int id){
		File file = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nl = document.getElementsByTagName("transaction");
			Element trans = null;
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (Integer.parseInt(node.getAttribute("id")) == id) {
					trans = node;
				}
			}
			Element root = document.getDocumentElement();
			root.removeChild((Node)trans);
			
			try (PrintStream out = new PrintStream(new FileOutputStream(
					filename))) {
				out.print(XmlUtils.toXML(document));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}catch (ParserConfigurationException e) {
				System.out.println("Can parse file");
				e.printStackTrace();
		} catch (SAXException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		
	}
	
	public static Transaction getEmissionTransaction(){
		Transaction tr = new Transaction();
		File file = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nl = document.getElementsByTagName("transaction");
			Element trans = null;
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (Integer.parseInt(node.getAttribute("id")) == 0) {
					trans = node;
				}
			}
			double coin = Double.parseDouble(trans.getElementsByTagName("coin").item(0).getTextContent());
			coin = coin / 2.0;
			trans.getElementsByTagName("coin").item(0).setTextContent(Double.toString(coin));
			
			try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
				out.print(XmlUtils.toXML(document));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch (ParserConfigurationException e) {
			System.out.println("Can't parse file");
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tr.getTransaction(0);
	}
	
	public List<Transaction> getTrascationToProve(){
		File file = new File(filename);
		List<Transaction> l = new ArrayList<Transaction>();
		Transaction tr = new Transaction();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nl = document.getElementsByTagName("transaction");
			Element trans = null;
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (Integer.parseInt(node.getAttribute("id")) != 0) l.add(tr.getTransaction(Integer.parseInt(node.getAttribute("id")))); 
			}
			return l;
		}catch (ParserConfigurationException e) {
			System.out.println("Can parse file");
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
