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
package de.ingrid.utils.xml;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @deprecated Use de.ingrid.utils.xpath.XPathUtils instead
 */
@Deprecated
public class XPathUtils {

	final protected static Log log = LogFactory.getLog(XPathUtils.class);

	private static NamespaceContext nsContext = new Csw202NamespaceContext();

	private static XPath xpath = null;

	private static XPathUtils myInstance;

	private XPathUtils() {}

	/** Get The Singleton. */
	public static synchronized XPathUtils getInstance() {
		if (myInstance == null) {
			myInstance = new XPathUtils();
		}
		return myInstance;
	}

	/** Get The Singleton. Initialize with given NamespaceContext (Default). */
	public static synchronized XPathUtils getInstance(NamespaceContext nsContext) {
		if (myInstance == null) {
			myInstance = new XPathUtils();
		}
		XPathUtils.nsContext = nsContext;

		return myInstance;
	}

	/** Get XPath instance with default namespaces. */
	public static XPath getXPathInstance() {
		if (xpath == null) {
			xpath = createNewXPathInstance();
			xpath.setNamespaceContext(nsContext);
		}

		return xpath;
	}

	/** Get XPath instance with given namespaces. Pass null if default namespaces. */
	public static XPath getXPathInstance(NamespaceContext nsContext) {
		if (nsContext != null){
			if (xpath == null) {
				xpath = createNewXPathInstance();
			}
			// always set context ! might be a new one !
			xpath.setNamespaceContext(nsContext);
		}else{
			getXPathInstance();
		}

		return xpath;
	}

	private static XPath createNewXPathInstance() {
		return XPathFactory.newInstance().newXPath();
	}

	public static Integer getInt(Object source, String xpathExpression) {
		String value = getString(source, xpathExpression);

		if (value != null) {
			return Integer.valueOf(value);

		} else {
			return null;
		}
	}

	public static Double getDouble(Object source, String xpathExpression) {
		String value = getString(source, xpathExpression);

		if (value != null) {
			return Double.valueOf(value);

		} else {
			return null;
		}
	}

	public static Long getLong(Object source, String xpathExpression) {
		String value = getString(source, xpathExpression);

		if (value != null) {
			return Long.valueOf(value);

		} else {
			return null;
		}
	}

	public static boolean nodeExists(Object source, String xpathExpression) {
		try {
			if (source != null) {
				XPath xpath = getXPathInstance();
				Boolean exists = (Boolean) xpath.evaluate(xpathExpression, source, XPathConstants.BOOLEAN);
				return exists;
			}

		} catch (XPathExpressionException ex) {
			// Log the exception and continue.
			log.error("Error evaluating xpath expression: '"+xpathExpression+"'", ex);
		}

		// Source document was null. Return false
		return false;
	}

	public static String getString(Object source, String xpathExpression) {
		try {
			if (source != null) {
				XPath xpath = getXPathInstance();
				Node node = (Node) xpath.evaluate(xpathExpression, source, XPathConstants.NODE);
				if (node != null) {
					return node.getTextContent();
				}
			}

		} catch (XPathExpressionException ex) {
			// Log the exception and continue.
			log.error("Error evaluating xpath expression: '"+xpathExpression+"'", ex);
		}

		// Something went wrong. Either the source document was null or the string for xpathExpression could not be found
		// In either case return null
		return null;
	}

	public static Node getNode(Object source, String xpathExpression) {
		try {
			if (source != null) {
				XPath xpath = getXPathInstance();
				Node node = (Node) xpath.evaluate(xpathExpression, source, XPathConstants.NODE);
				return node;
			}

		} catch (XPathExpressionException ex) {
			// Log the exception and continue.
			log.error("Error evaluating xpath expression: '"+xpathExpression+"'", ex);
		}

		// Something went wrong. Either the source document was null or the xpathExpression could not be found
		// In either case return null
		return null;
	}

	public static NodeList getNodeList(Object source, String xpathExpression) {
		try {
			if (source != null) {
				XPath xpath = getXPathInstance();
				NodeList nodeList = (NodeList) xpath.evaluate(xpathExpression, source, XPathConstants.NODESET);
				return nodeList;
			}

		} catch (XPathExpressionException ex) {
			// Log the exception and continue.
			log.error("Error evaluating xpath expression: '"+xpathExpression+"'", ex);
		}

		// Something went wrong. Either the source document was null or the xpathExpression could not be found
		// In either case return null
		return null;
	}

	// Get node list with xPath instance for 'IDF'
	public static NodeList getNodeList(Object source, String xpathExpression, NamespaceContext nsContext) {
		try {
			if (source != null) {
				XPath xpath = getXPathInstance(nsContext);
				NodeList nodeList = (NodeList) xpath.evaluate(xpathExpression, source, XPathConstants.NODESET);
				return nodeList;
			}

		} catch (XPathExpressionException ex) {
			// Log the exception and continue.
			log.error("Error evaluating xpath expression: '"+xpathExpression+"'", ex);
		}

		// Something went wrong. Either the source document was null or the xpathExpression could not be found
		// In either case return null
		return null;
	}

	/**
	 * Creates xml nodes from xpath like expression (/node1/node2/node3). The existence of the nodes, starting
	 * from the root node of the xpath like expression will be checked, if nodes do not exist they will created.
	 * 
	 * Caution: If more nodes of the same name are present, only the first found node will be extended.
	 * 
	 * @param node
	 * @param xpath
	 * @return The last added node
	 */
	public static Node createElementFromXPath(Node node, String xpath) {
		Node refNode = null;
		if (xpath.startsWith("/")) {
			refNode = node.getOwnerDocument().getDocumentElement();
		} else {
			refNode = node;
		}
		//refNode = node;
		String[] xpathElements = xpath.split("/");
		String tmpXpath = ".";
		Node result = refNode;
		for (int i=0; i<xpathElements.length; i++) {
			if (xpathElements[i].length() > 0) {
				if (!XPathUtils.nodeExists(refNode, tmpXpath + "/" + xpathElements[i])) {
					if (tmpXpath.length() == 0) {
						throw new IllegalArgumentException("More than one root element is not allowed! The supplied absolute path MUST start with the existing root node!");
					} else {
						NodeList list = XPathUtils.getNodeList(refNode, tmpXpath);
						result = list.item(0).appendChild(refNode.getOwnerDocument().createElement(xpathElements[i]));
					}
				} else if (tmpXpath.length() > 0) {
					result = XPathUtils.getNodeList(refNode, tmpXpath+ "/" + xpathElements[i]).item(0);
				}
				tmpXpath = tmpXpath + "/" + xpathElements[i];
			} else {
				tmpXpath = "";
			}
		}
		return result;
	}

	public static Node createElementFromXPathAsSibling(Node node, String xpath) {
		boolean createSibling = XPathUtils.nodeExists(node, xpath);
		Node n = createElementFromXPath(node, xpath);
		if (createSibling) {
			n = XMLUtils.insertAfter(node.getOwnerDocument().createElement(xpath.split("/")[xpath.split("/").length-1]), n);
		}
		return n;
	}

}
