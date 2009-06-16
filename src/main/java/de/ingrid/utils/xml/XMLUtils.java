package de.ingrid.utils.xml;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public final class XMLUtils {

	final private static Log log = LogFactory.getLog(XMLUtils.class);
	
	public static String toString(Document document)
			throws TransformerException {
		StringWriter stringWriter = new StringWriter();
		StreamResult streamResult = new StreamResult(stringWriter);
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.transform(new DOMSource(document.getDocumentElement()),
				streamResult);
		return stringWriter.toString();
	}

	public static Node createOrReplaceTextNode(Node n, String data) {
		if (data == null) {
			return n;
		}
		NodeList nodes = n.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i) instanceof org.w3c.dom.Text) {
				((Text) nodes.item(i)).setData(data);
				return n;
			} else if (nodes.item(i) instanceof org.w3c.dom.CDATASection) {
				n.replaceChild(n.getOwnerDocument().createTextNode(data),
						nodes.item(i));
				return n;
			}
		}
		n.appendChild(n.getOwnerDocument().createTextNode(data));
		return n;
	}
	
	public static Node createOrReplaceAttribute(Node n, String attribute, String data) {
		NamedNodeMap atts = n.getAttributes();
		Node node = atts.getNamedItem(attribute);
		if (node != null && node instanceof Attr) {
			((Attr)node).setValue(data);
		} else {
			Attr att = n.getOwnerDocument().createAttribute(attribute);
	        att.setValue(data);    
	        atts.setNamedItem(att);
		}
        return n;
	}

	/**
	 * This method ensures that the output String has only
	 * valid XML unicode characters as specified by the
	 * XML 1.0 standard. For reference, please see
	 * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
	 * standard</a>. This method will return an empty
	 * String if the input is null or empty.
	 *
	 * @param in The String whose non-valid characters we want to replace.
	 * @return The in String, non-valid characters are replaced with '_'.
	 */
	public static String stripNonValidXMLCharacters(String in) {

		StringBuffer out = new StringBuffer(); // Used to hold the output.
		char current; // Used to reference the current character.
		if (in == null || ("".equals(in))) {
			return "";
		} // vacancy test.
		for (int i = 0; i < in.length(); i++) {
			current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught
									// here; it should not happen.
			if ((current == 0x9) ||
				(current == 0xA) ||
				(current == 0xD) ||
				((current >= 0x20) && (current <= 0xD7FF)) ||
				((current >= 0xE000) && (current <= 0xFFFD)) ||
				((current >= 0x10000) && (current <= 0x10FFFF))) {
					out.append(current);
			} else {
				log.debug("found invalid char");
				out.append("_");
			}
		}

		return out.toString();

	}

}
