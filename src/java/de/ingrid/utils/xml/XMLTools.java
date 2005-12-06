/**
 * Copyright (c) 2005 by GIStec GmbH
 */

package de.ingrid.utils.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class provides a set of frequently needed functions when working with
 * XML documents.
 * 
 * @author nbeck
 * @version 0.1
 */
public class XMLTools {

    /**
     * The logging object
     */
    private static Log log = LogFactory.getLog(XMLTools.class);

    /**
     * Searches XML tree starting from <code>element</code> for tags called
     * <code>tagName</code>. The wanted tag must not be a direct child of
     * <code>element</code>. The text value of the tag with index
     * <code>itemnr</code> is returned, e.g. <code>itemnr=2</code> will
     * return the second text value that was found. If itemnr is larger than the
     * count of found tags, null will be returned to indicate an error (same on
     * other kind of errors).
     * 
     * @param element
     *            XML element to start from
     * @param tagName
     *            The name of the tag whose value should be returned.
     * @param itemnr
     *            Index or count of the wanted text value.
     * @return Text value of tag or null in case of an error.
     */
    public static String getTextNodeValue(Element element, String tagName, int itemnr) {
        // Checking preconditions for this method
        if (element == null || tagName == null || tagName == "" || itemnr < 0) {
            log.error("Preconditions unfilled! NULL will be returned.");
            return null;
        }

        NodeList nl = element.getElementsByTagName(tagName);

        if (nl != null && nl.getLength() > 0 && itemnr < nl.getLength()) {
            try {
                Node n = nl.item(itemnr).getFirstChild();
                return (n != null) ? n.getNodeValue() : "";
            } catch (DOMException e) {
                log.error("Failed to get text value of '" + tagName + "' (ItemNr: " + itemnr + ").");
                e.printStackTrace();
            }
        }
        // Return null in case of an error.
        return null;
    }

    /**
     * Same as <code>getTextNodeValue()</code> but with additional name space support.
     * 
     * @param element
     *            XML element to start from
     * @param nameSpace
     *            The URI of name space
     * @param tagName
     *            The name of the tag whose value should be returned.
     * @param itemnr
     *            Index or count of the wanted text value.
     * @return Text value of tag or null in case of an error.
     * @see getTextNodeValue()
     */
    public static String getTextNodeValueNS(Element element, String nameSpace, String tagName, int itemnr) {
        return getTextNodeValue(element, nameSpace + ":" + tagName, itemnr);
    }

    /**
     * Searches XML tree starting from <code>element</code> for first tag
     * called <code>tagName</code>. The wanted tag must not be a direct child
     * of <code>element</code>. The result will be a string array with text
     * values of all found tags called 'tagName'.
     * 
     * @param element
     *            XML element to start from
     * @param tagName
     *            The name of the tag whose value should be returned.
     * @return String array with all found text values.
     */
    public static String[] getTextNodeValues(Element element, String tagName) {
        // Checking preconditions for this method
        if (element == null || tagName == null || tagName == "") {
            log.error("Preconditions unfilled!");
            return null;
        }

        NodeList nl = element.getElementsByTagName(tagName);

        if (nl != null && nl.getLength() > 0) {
            String[] vals = new String[nl.getLength()];
           
            try {
                for (int i = 0; i < nl.getLength(); i++) {
                    Node n = nl.item(i).getFirstChild();
                    vals[i] = n.getNodeValue();
                }
                return vals;
            } catch (DOMException e) {
                log.error("Failed to get text value of '" + tagName + "'.");
                e.printStackTrace();
            }
        }
        // Return null in case of an error.
        return null;
    }
    
    /**
     * Same as <code>getTextNodeValues()</code> but with additional name space support.
     * 
     * @param element
     *            XML element to start from
     * @param nameSpace
     *            The URI of name space
     * @param tagName
     *            The name of the tag whose value should be returned.
     * @return String array with all found text values.
     */
    public static String[] getTextNodeValuesNS(Element element, String nameSpace, String tagName) {
        return getTextNodeValues(element, nameSpace + ":" + tagName);
    }

    /**
     * Searches XML tree starting from <code>element</code> for first tag
     * called <code>tagName</code>. The wanted tag must not be a direct child
     * of <code>element</code>. Also if more then one tags are found, only
     * the first one is returned.
     * 
     * @param element
     *            XML element to start from
     * @param tagName
     *            The name of the tag whose value should be returned.
     * @return Text value of (first found) tag or null in case of an error.
     */
    public static String getFirstTextNodeValue(Element element, String tagName) {
        return getTextNodeValue(element, tagName, 0);
    }
    
    /**
     * Same as <code>getFirstTextNodeValue()</code> but with additional name space support.
     * 
     * @param element
     *            XML element to start from
     * @param nameSpace
     *            The URI of name space
     * @param tagName
     *            The name of the tag whose value should be returned.
     * @return Text value of (first found) tag or null in case of an error.
     */
    public static String getFirstTextNodeValueNS(Element element, String nameSpace, String tagName) {
        return getTextNodeValue(element, nameSpace + ":" + tagName, 0);
    }
    
    /**
     * Prints the XML document <code>dom</code> to the file named
     * <code>fileName</code>. If <code>fileName</code> is <code>null</code>
     * or an empty string, the document will be written to
     * <code>System.out</code>.
     * 
     * @param dom
     *            The XML document to print out
     * @param fileName
     *            Name of the file where <code>dom</code> shall be written.
     */
    public static void printToFile(Document dom, String fileName) {
        try {
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);
            XMLSerializer serializer = null;

            if (fileName == null || fileName == "") {
                serializer = new XMLSerializer(System.out, format);
            } else {
                serializer = new XMLSerializer(new FileOutputStream(new File(fileName)), format);
            }
            serializer.serialize(dom);

        } catch (IOException e) {
            log.error("Faild to print File '" + fileName + "'.");
            e.printStackTrace();
        }
    }
}
