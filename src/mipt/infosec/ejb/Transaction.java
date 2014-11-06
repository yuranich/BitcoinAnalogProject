package mipt.infosec.ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;

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

import sun.io.ByteToCharConverter;

public class Transaction {
	public static final String filename = "transaction.xml";
	public static final String defaultfile = "<transactions>\n</transactions>";

	public void createTransaction(int from, int to, int money) {
		File f = new File(filename);
		
		if (!f.exists() || f.length() == 0){
			try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
				out.print("<transactions>"+'\n'+"</transactions>");
			
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		
		int maxId = Transaction.getMaxId();
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(f);

				Element root = document.getDocumentElement();
				Element node = document.createElement("transaction");
				node.setAttribute("id", Integer.toString(maxId + 1));
				root.appendChild(node);
				Element sendFrom = document.createElement("from");
				sendFrom.appendChild(document.createTextNode(Integer.toString(from)));
				Element sendTO = document.createElement("to");
				sendTO.appendChild(document.createTextNode(Integer.toString(to)));
				Element coin = document.createElement("coin");
				coin.appendChild(document.createTextNode(Integer.toString(money)));
				byte[] id =  BigInteger.valueOf(maxId+1).toByteArray();
				Stribog stb = new Stribog(256);
		//		byte[] hash = stb.getHash(id);
		//		BigInteger bhash = new BigInteger(hash);
				
				Element hashs = document.createElement("hash");
				hashs.appendChild(document.createTextNode("hash 1234"));
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
		int maxId = 0;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList nl = document.getElementsByTagName("transaction");
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (maxId < Integer.parseInt(node.getAttribute("id")))
					maxId = Integer.parseInt(node.getAttribute("id"));
			}
			return maxId;

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
}