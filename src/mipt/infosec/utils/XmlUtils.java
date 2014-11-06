package mipt.infosec.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.TransformerException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

public class XmlUtils {

	public static String toXML(Document document) throws TransformerException, IOException {
		 
        try {
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
 
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
 
    }
}
