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
import javax.xml.transform.TransformerException;

import mipt.infosec.secutils.hash.Stribog;
import mipt.infosec.utils.XmlUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Block {
	public static final String filename = "block.xml";
	public static final String defaultfile = "<blocks>\n</blocks>";
	
	public void createBlock() {
		File f = new File(filename);
		
		if (!f.exists() || f.length() == 0){
			try (PrintStream out = new PrintStream(new FileOutputStream(filename))) {
				out.print(defaultfile);
			
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		
		int maxId = Block.getMaxId();
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(f);

				Element root = document.getDocumentElement();
				Element node = document.createElement("block");
				node.setAttribute("id", Integer.toString(maxId + 1));
				root.appendChild(node);
				
				
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
	
	public void addTransaction(int blockid, int transid)
	{
		File f = new File(filename);

			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(f);

				Element root = document.getDocumentElement();
				NodeList nl = document.getElementsByTagName("block");
				Element node = null;
				for( int i = 0; i < nl.getLength(); i++){
					Element e = (Element) nl.item(i);
					if ( Integer.parseInt(e.getAttribute("id")) == blockid ) node = e;
				}
				
				Element transac = document.createElement("transaction");
				transac.appendChild(document.createTextNode(Integer.toString(transid)));
				
				root.appendChild(node);
				node.appendChild(transac);
				
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
			NodeList nl = document.getElementsByTagName("block");
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

