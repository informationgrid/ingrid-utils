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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

/**
 * @author joachim
 *
 */
public class XPathUtilsTest extends TestCase {

	/**
	 * Test method for {@link de.ingrid.utils.xml.XPathUtils#createElementFromXPath(org.w3c.dom.Node, java.lang.String)}.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws UnsupportedEncodingException 
	 */
	public void testCreateElementFromXPath() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1></child1></test>".getBytes("UTF-8")));
		XPathUtils.createElementFromXPath(doc.getDocumentElement(), "/test/child1/grandchild1/grandgrandchild1");
		assertTrue(XPathUtils.nodeExists(doc.getDocumentElement(), "/test/child1/grandchild1/grandgrandchild1"));

		Node n = XPathUtils.createElementFromXPath(doc.getDocumentElement(), "/test/child1");
		assertEquals("child1", n.getNodeName());
	
	}

}
