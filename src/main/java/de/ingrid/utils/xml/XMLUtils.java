package de.ingrid.utils.xml;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public final class XMLUtils {

	public static String toString(Document document) throws TransformerException {
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.transform(new DOMSource(document.getDocumentElement()), streamResult);
        return stringWriter.toString();
	}
	
	public static Node createOrReplaceTextNode(Node n, String data) {
		NodeList nodes = n.getChildNodes();
		for (int i=0; i< nodes.getLength(); i++) {
			if (nodes.item(i) instanceof Text) {
				((Text)nodes.item(i)).setData(data);
				return n;
			}
		}
		n.appendChild(n.getOwnerDocument().createTextNode(data));
		return n;
	}
	
}
