/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
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

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author joachim
 *
 */
public class XPathUtilsTest {
    
    de.ingrid.utils.xpath.XPathUtils xpathUtils = new de.ingrid.utils.xpath.XPathUtils();

    /**
     * Test method for {@link de.ingrid.utils.xml.XPathUtils#createElementFromXPath(org.w3c.dom.Node, java.lang.String)}.
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testCreateElementFromXPath() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1></child1></test>".getBytes("UTF-8")));
		xpathUtils.createElementFromXPath(doc.getDocumentElement(), "/test/child1/grandchild1/grandgrandchild1");
		assertTrue(xpathUtils.nodeExists(doc.getDocumentElement(), "/test/child1/grandchild1/grandgrandchild1"));

		Node n = xpathUtils.createElementFromXPath(doc.getDocumentElement(), "/test/child1");
		assertEquals("child1", n.getNodeName());
		n = xpathUtils.createElementFromXPath(n, "newChild");
		assertEquals("newChild", n.getNodeName());
		n = xpathUtils.createElementFromXPath(n, "/test/newRootChild");
		assertEquals("newRootChild", n.getNodeName());
		assertTrue(xpathUtils.nodeExists(doc.getDocumentElement(), "/test/newRootChild"));
		
		try {
			n = xpathUtils.createElementFromXPath(n, "/newRootChild");
			fail("must throw IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
		}
	}

    @Test
    public void testCreateElementFromXPathAsSibling() throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream("<test><child1></child1></test>".getBytes("UTF-8")));
		xpathUtils.createElementFromXPath(doc.getDocumentElement(), "/test/child1/grandchild1/grandgrandchild1");
		assertEquals(1, xpathUtils.getNodeList(doc, "/test/child1/grandchild1/grandgrandchild1").getLength());
		xpathUtils.createElementFromXPathAsSibling(doc.getDocumentElement(), "/test/child1/grandchild1/grandgrandchild1");
		assertEquals(1, xpathUtils.getNodeList(doc, "/test/child1/grandchild1").getLength());
		assertEquals(2, xpathUtils.getNodeList(doc, "/test/child1/grandchild1/grandgrandchild1").getLength());
	}

}
