/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
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

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author joachim
 *
 */
public class XMLUtilsTest {
    
    de.ingrid.utils.xpath.XPathUtils xpathUtils = new de.ingrid.utils.xpath.XPathUtils();

    /**
     * Test method for {@link de.ingrid.utils.xml.XMLUtils#toString(org.w3c.dom.Document)}.
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     * @throws UnsupportedEncodingException 
     * @throws TransformerException 
     */
    @Test
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
    @Test
    public void testCreateOrReplaceTextNode() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1>Hallo</child1></test>".getBytes("UTF-8")));
		Node n = XMLUtils.createOrReplaceTextNode(doc.getDocumentElement().getFirstChild(), "Welt");
		assertEquals("Welt", n.getTextContent());
	}

    @Test
    public void testInsertAfter() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1>Hallo</child1><child2>Hello</child2></test>".getBytes("UTF-8")));
		NodeList nl = xpathUtils.getNodeList(doc, "/test/child1");
		assertEquals(1, nl.getLength());
		Node n = doc.createElement("child1");
		Node refNode = xpathUtils.getNode(doc, "/test/child1");
		XMLUtils.insertAfter(n, refNode);
		nl = xpathUtils.getNodeList(doc, "/test/child1");
		assertEquals(2, nl.getLength());
		
		n = doc.createElement("child2");
		refNode = xpathUtils.getNode(doc, "/test/child2");
		XMLUtils.insertAfter(n, refNode);
		nl = xpathUtils.getNodeList(doc, "/test/child2");
		assertEquals(2, nl.getLength());
		System.out.println(XMLUtils.toString(doc));
		
	}

    @Test
    public void testRemoveNode() throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1 a=\"1\">Hallo</child1><child2>Hello</child2></test>".getBytes("UTF-8")));
		Node n = xpathUtils.getNode(doc, "//child2");
		Node result = XMLUtils.remove(n);
		assertEquals(false, xpathUtils.nodeExists(doc, "//child2"));
		assertEquals("test", result.getNodeName());
		n = xpathUtils.getNode(doc, "//child1/@a");
		result = XMLUtils.remove(n);
		assertEquals(false, xpathUtils.nodeExists(doc, "//child2/@a"));
		assertEquals("child1", result.getNodeName());
	}

}
