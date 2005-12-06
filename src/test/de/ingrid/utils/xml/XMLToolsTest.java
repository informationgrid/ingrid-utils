/**
 * Copyright (c) 2005 by GIStec GmbH
 */

package de.ingrid.utils.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import junit.framework.TestCase;

public class XMLToolsTest extends TestCase {

    private DocumentBuilderFactory dbf = null;
    private Document dom = null;
    
    protected void setUp() throws Exception {
        dbf = DocumentBuilderFactory.newInstance();
        System.out.println(System.getProperty("user.dir"));
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse("test.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void tearDown() throws Exception {
    }
  
    public void testGetFirstTextNodeValue() {
        Element docElement = dom.getDocumentElement();   
        // Test methods without name space:
        assertEquals(XMLTools.getFirstTextNodeValue(docElement, "vorname"), "Dirk");
        assertEquals(XMLTools.getFirstTextNodeValue(docElement, "name"), "Kommer"); 
        assertNull(XMLTools.getFirstTextNodeValue(docElement, "foo")); 
        // Test methods with name space:
        assertEquals(XMLTools.getFirstTextNodeValueNS(docElement, "cio", "name"), "Dr. Hubert"); 
        assertEquals(XMLTools.getFirstTextNodeValueNS(docElement, "stuff", "name"), "Goebel"); 
        assertNull(XMLTools.getFirstTextNodeValueNS(docElement, "foobar", "name")); 
    }
    
    public void testGetTextNodeValueByItemNr() {
        Element docElement = dom.getDocumentElement();        
        // Test methods without name space:
        assertEquals(XMLTools.getTextNodeValue(docElement, "vorname", 1), "Joseph");
        assertEquals(XMLTools.getTextNodeValue(docElement, "vorname", 3), "Michael");
        assertNull(XMLTools.getTextNodeValue(docElement, "vorname", 999));
        assertNull(XMLTools.getTextNodeValue(docElement, "vorname", -1));
        assertNull(XMLTools.getTextNodeValue(docElement, "foo", 2));
        // Test methods with name space:
        assertEquals(XMLTools.getTextNodeValueNS(docElement, "stuff", "vorname", 1), "Susanne");
        assertEquals(XMLTools.getTextNodeValueNS(docElement, "stuff", "vorname", 0), "Bernd");
        assertNull(XMLTools.getTextNodeValueNS(docElement, "stuff", "vorname", 9));
        assertNull(XMLTools.getTextNodeValueNS(docElement, "foobar", "name", 1)); 
    }
    
    public void testGetTextNodeValues() {
        Element docElement = dom.getDocumentElement();        
        // Test methods without name space:
        assertEquals(XMLTools.getTextNodeValues(docElement, "name").length, 4);
        assertEquals(XMLTools.getTextNodeValues(docElement, "telefon").length, 3);
        assertEquals(XMLTools.getTextNodeValues(docElement, "name")[0], "Kommer");
        assertEquals(XMLTools.getTextNodeValues(docElement, "telefon")[2], "15482");
        assertTrue(XMLTools.getTextNodeValues(docElement, "telefon").length == 3);
        assertNull(XMLTools.getTextNodeValues(docElement, "foo"));
        // Test methods with name space:
        assertEquals(XMLTools.getTextNodeValuesNS(docElement, "stuff", "name")[0], "Goebel");
        assertEquals(XMLTools.getTextNodeValuesNS(docElement, "stuff", "name")[1], "Henkel");
        assertTrue(XMLTools.getTextNodeValuesNS(docElement, "stuff", "name").length == 2);
    }
    
    public void testCreateXMLDoc() {
        Document MyDom = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            MyDom = db.newDocument();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Element root = MyDom.createElement("InGridDocuments");
        MyDom.appendChild(root);
        
        Element indoc = MyDom.createElement("Test:InGridDocument");
        indoc.setAttribute("id", "1354687534");
        root.appendChild(indoc);
        
        XMLTools.printToFile(MyDom, null);
    }
    
    
    
    
    public void testPrintToFile() {
        //XMLTools.printToFile(dom, "output.xml");
        //XMLTools.printToFile(dom, null);
    }
    
}
