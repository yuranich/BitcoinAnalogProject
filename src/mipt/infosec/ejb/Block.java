package mipt.infosec.ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
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

public class Block implements Serializable {
	public static final String FILE_NAME = "block.xml";
	public static final String DEFAULT_TAG = "<blocks>\n</blocks>";
	private int id;
	private String hash;
	private String prevHash;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPrevHash() {
		return prevHash;
	}

	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}

	public Block getBlock(int id){
		File f = new File(FILE_NAME);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(f);
			NodeList nl = document.getElementsByTagName("block");
			Element block = null;
			System.out.println(nl.getLength());
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				System.out.println(Integer.parseInt(node.getAttribute("id")));
				if (Integer.parseInt(node.getAttribute("id")) == id) {
					block = node;
				}
			}
			if (block == null) {
				return null;
			}
			Block bl = new Block();
			bl.setId(id);
			bl.setHash(block.getElementsByTagName("current_hash").item(0).getTextContent());
			bl.setPrevHash(block.getElementsByTagName("prev_hash").item(0).getTextContent());
			return bl;
			
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
		return null;
			
	}
	
	
	
	public void createReceivedBlock(int blockId, String blockHash, String prevHash) {
		File f = new File(FILE_NAME);
		
		if (!f.exists() || f.length() == 0){
			try (PrintStream out = new PrintStream(new FileOutputStream(FILE_NAME))) {
				out.print(DEFAULT_TAG);
			
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
			Element node = document.createElement("block");
			node.setAttribute("id", Integer.toString(blockId));
			
			Element hashs = document.createElement("current_hash");
			hashs.appendChild(document.createTextNode(blockHash.toString()));
			node.appendChild(hashs);
			
			Element lhashs = document.createElement("prev_hash");
			lhashs.appendChild(document.createTextNode(prevHash));
			node.appendChild(lhashs);
			root.appendChild(node);
			
			root.setAttribute("maxid",Integer.toString(id));
			
			try (PrintStream out = new PrintStream(new FileOutputStream(FILE_NAME))) {
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
	
	public void updateblock(int blockId, int transacId){
		Transaction tr = new Transaction();
		Transaction cur = tr.getTransaction(transacId);
		if ( transacId != 0) tr.deleteTransaction(transacId);
		File f = new File(FILE_NAME);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(f);
			NodeList nl = document.getElementsByTagName("block");
			Element block = null;
			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				if (Integer.parseInt(node.getAttribute("id")) == blockId) {
					block = node;
				}
			}
			if (block == null) {
				System.out.println("block doesn't found!");
				return;
			}
			Element transac = document.createElement("transaction");
			transac.setAttribute("id", Integer.toString(transacId));
			Element sendFrom = document.createElement("from");
			sendFrom.appendChild(document.createTextNode(cur.getFrom()));
			Element sendTO = document.createElement("to");
			sendTO.appendChild(document.createTextNode(cur.getTo()));
			Element coin = document.createElement("coin");
			coin.appendChild(document.createTextNode(Double.toString(cur.getMoney())));
			Element hashs = document.createElement("hash");
			hashs.appendChild(document.createTextNode(cur.getHash()));
			
			transac.appendChild(sendFrom);
			transac.appendChild(sendTO);
			transac.appendChild(coin);
			transac.appendChild(hashs);
			
			block.appendChild(transac);
						
			try (PrintStream out = new PrintStream(new FileOutputStream(FILE_NAME))) {
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
	
	public void createBlock() {
		File f = new File(FILE_NAME);
		
		if (!f.exists() || f.length() == 0){
			try (PrintStream out = new PrintStream(new FileOutputStream(FILE_NAME))) {
				out.print(DEFAULT_TAG);
			
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
				
				byte[] id = BigInteger.valueOf(maxId+ 1).toByteArray();
				Stribog stb = new Stribog(256);
				byte[] hash = stb.getHash(id);
				BigInteger bhash = new BigInteger(hash);
				
				Element hashs = document.createElement("current_hash");
				hashs.appendChild(document.createTextNode(bhash.toString()));
				node.appendChild(hashs);
				
				this.hash = bhash.toString();

				byte[] lid = BigInteger.valueOf(maxId).toByteArray();
				Stribog lstb = new Stribog(256);
				byte[] lhash = lstb.getHash(lid);
				BigInteger lbhash = new BigInteger(lhash);

				Element lhashs = document.createElement("prev_hash");
				lhashs.appendChild(document.createTextNode(lbhash.toString()));
				node.appendChild(lhashs);
				root.appendChild(node);
				
				this.prevHash = lhashs.toString();
				
				root.setAttribute("maxid",Integer.toString(maxId+1));
				
				try (PrintStream out = new PrintStream(new FileOutputStream(FILE_NAME))) {
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
		File file = new File(FILE_NAME);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		int maxId = 0;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			Element root = document.getDocumentElement();
			return Integer.parseInt(root.getAttribute("maxid"));

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

