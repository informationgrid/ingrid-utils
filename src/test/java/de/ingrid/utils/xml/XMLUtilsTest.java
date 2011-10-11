/**
 * 
 */
package de.ingrid.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

/**
 * @author joachim
 *
 */
public class XMLUtilsTest extends TestCase {

	/**
	 * Test method for {@link de.ingrid.utils.xml.XMLUtils#toString(org.w3c.dom.Document)}.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws UnsupportedEncodingException 
	 * @throws TransformerException 
	 */
	public void testToStringDocument() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1>Hallo</child1></test>".getBytes("UTF-8")));
		String xml = XMLUtils.toString(doc);
		assertTrue(xml.length() > 0);
        doc = db.parse(new ByteArrayInputStream("<test><child1>Hall&#252;</child1></test>".getBytes("UTF-8")));
        xml = XMLUtils.toString(doc);
        assertTrue(xml.length() > 0);
	}

	/**
	 * Test method for {@link de.ingrid.utils.xml.XMLUtils#createOrReplaceTextNode(javax.xml.soap.Node, java.lang.String)}.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws UnsupportedEncodingException 
	 */
	public void testCreateOrReplaceTextNode() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1>Hallo</child1></test>".getBytes("UTF-8")));
		Node n = XMLUtils.createOrReplaceTextNode(doc.getDocumentElement().getFirstChild(), "Welt");
		assertEquals("Welt", n.getTextContent());
	}
	
	public void testInsertAfter() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1>Hallo</child1><child2>Hello</child2></test>".getBytes("UTF-8")));
		NodeList nl = XPathUtils.getNodeList(doc, "/test/child1");
		assertEquals(1, nl.getLength());
		Node n = doc.createElement("child1");
		Node refNode = XPathUtils.getNode(doc, "/test/child1");
		XMLUtils.insertAfter(n, refNode);
		nl = XPathUtils.getNodeList(doc, "/test/child1");
		assertEquals(2, nl.getLength());
		
		n = doc.createElement("child2");
		refNode = XPathUtils.getNode(doc, "/test/child2");
		XMLUtils.insertAfter(n, refNode);
		nl = XPathUtils.getNodeList(doc, "/test/child2");
		assertEquals(2, nl.getLength());
		System.out.println(XMLUtils.toString(doc));
		
	}

}
