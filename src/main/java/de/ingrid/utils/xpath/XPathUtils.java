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
package de.ingrid.utils.xpath;

import java.util.ArrayList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.ingrid.utils.xml.XMLUtils;

public class XPathUtils {

    final protected static Log log = LogFactory.getLog(XPathUtils.class);

    private XPath xpath = null;

    /**
     * Constructor
     * 
     * @param nsContext
     *            NamespaceContext to use, no namespace is used if null
     */
    public XPathUtils(NamespaceContext nsContext) {
        this.xpath = XPathFactory.newInstance().newXPath();
        if (nsContext != null) {
            this.xpath.setNamespaceContext(nsContext);
        }
    }
    
    /**
     * Create a xpath util without a namespace context.
     * 
     */
    public XPathUtils() {
        this(null);
    }

    public Integer getInt(Object source, String xpathExpression) {
        String value = this.getString(source, xpathExpression);

        if (value != null) {
            try {
                return Integer.valueOf(value);
            } catch (NumberFormatException e) {
                log.error("Unable to parse '" + value + "' as Integer.");
                return null;
            }
        } else {
            return null;
        }
    }

    public Double getDouble(Object source, String xpathExpression) {
        String value = this.getString(source, xpathExpression);

        if (value != null) {
            try {
                return Double.valueOf(value);
            } catch (NumberFormatException e) {
                log.error("Unable to parse '" + value + "' as Double.");
                return null;
            }
        } else {
            return null;
        }
    }

    public Long getLong(Object source, String xpathExpression) {
        String value = this.getString(source, xpathExpression);

        if (value != null) {
            try {
                return Long.valueOf(value);
            } catch (NumberFormatException e) {
                log.error("Unable to parse '" + value + "' as Integer.");
                return null;
            }

        } else {
            return null;
        }
    }

    public boolean nodeExists(Object source, String xpathExpression) {
        try {
            if (source != null) {
                Boolean exists = (Boolean) this.xpath.evaluate(xpathExpression, source, XPathConstants.BOOLEAN);
                return exists;
            }
        } catch (XPathExpressionException ex) {
            // Log the exception and continue.
            log.error("Error evaluating xpath expression: '" + xpathExpression + "'", ex);
        }

        // Source document was null. Return false
        return false;
    }

    public String getString(Object source, String xpathExpression) {
        try {
            if (source != null) {
                Node node = (Node) this.xpath.evaluate(xpathExpression, source, XPathConstants.NODE);
                if (node != null) {
                    return node.getTextContent();
                }
            }
        } catch (XPathExpressionException ex) {
            // Log the exception and continue.
            log.error("Error evaluating xpath expression: '" + xpathExpression + "'", ex);
        }

        // Something went wrong. Either the source document was null or the
        // string for xpathExpression could not be found
        // In either case return null
        return null;
    }

    public Node getNode(Object source, String xpathExpression) {
        try {
            if (source != null) {
                Node node = (Node) this.xpath.evaluate(xpathExpression, source, XPathConstants.NODE);
                return node;
            }
        } catch (XPathExpressionException ex) {
            // Log the exception and continue.
            log.error("Error evaluating xpath expression: '" + xpathExpression + "'", ex);
        }

        // Something went wrong. Either the source document was null or the
        // xpathExpression could not be found
        // In either case return null
        return null;
    }

    public NodeList getNodeList(Object source, String xpathExpression) {
        try {
            if (source != null) {
                NodeList nodeList = (NodeList) this.xpath.evaluate(xpathExpression, source, XPathConstants.NODESET);
                return nodeList;
            }
        } catch (XPathExpressionException ex) {
            // Log the exception and continue.
            log.error("Error evaluating xpath expression: '" + xpathExpression + "'", ex);
        }

        // Something went wrong. Either the source document was null or the
        // xpathExpression could not be found
        // In either case return null
        return null;
    }

    // Get node list with xPath instance for 'IDF'
    public NodeList getNodeList(Object source, String xpathExpression, NamespaceContext nsContext) {
        try {
            if (source != null) {
                NodeList nodeList = (NodeList) this.xpath.evaluate(xpathExpression, source, XPathConstants.NODESET);
                return nodeList;
            }
        } catch (XPathExpressionException ex) {
            // Log the exception and continue.
            log.error("Error evaluating xpath expression: '" + xpathExpression + "'", ex);
        }

        // Something went wrong. Either the source document was null or the
        // xpathExpression could not be found
        // In either case return null
        return null;
    }

    /**
     * Creates xml nodes from xpath like expression (/node1/node2/node3). The
     * existence of the nodes, starting from the root node of the xpath like
     * expression will be checked, if nodes do not exist they will created.
     * 
     * Caution: If more nodes of the same name are present, only the first found
     * node will be extended.
     * 
     * @param node
     * @param xpath
     * @return The last added node
     */
    public Node createElementFromXPath(Node node, String xpath) {
        Node refNode = null;
        if (xpath.startsWith("/")) {
            refNode = node.getOwnerDocument().getDocumentElement();
        } else {
            refNode = node;
        }
        // refNode = node;
        String[] xpathElements = xpath.split("/");
        String tmpXpath = ".";
        Node result = refNode;
        for (int i = 0; i < xpathElements.length; i++) {
            if (xpathElements[i].length() > 0) {
                if (!this.nodeExists(refNode, tmpXpath + "/" + xpathElements[i])) {
                    if (tmpXpath.length() == 0) {
                        throw new IllegalArgumentException(
                                "More than one root element is not allowed! The supplied absolute path MUST start with the existing root node!");
                    } else {
                        NodeList list = this.getNodeList(refNode, tmpXpath);
                        result = list.item(0).appendChild(refNode.getOwnerDocument().createElement(xpathElements[i]));
                    }
                } else if (tmpXpath.length() > 0) {
                    result = this.getNodeList(refNode, tmpXpath + "/" + xpathElements[i]).item(0);
                }
                tmpXpath = tmpXpath + "/" + xpathElements[i];
            } else {
                tmpXpath = "";
            }
        }
        return result;
    }

    public Node createElementFromXPathAsSibling(Node node, String xpath) {
        boolean createSibling = this.nodeExists(node, xpath);
        Node n = this.createElementFromXPath(node, xpath);
        if (createSibling) {
            n = XMLUtils.insertAfter(node.getOwnerDocument().createElement(
                    xpath.split("/")[xpath.split("/").length - 1]), n);
        }
        return n;
    }
    
    
    /**
     * Remove an element at a specific XPath.
     * 
     * @param node is the root node to start the XPath from
     * @param xpath is the XPath to the node to delete
     * @return the parent node if node to delete was found, otherwise null
     */
    public Node removeElementAtXPath(Node node, String xpath) {
        Node parent = null;
        Node nodeToDelete = getNode( node, xpath);
        if (nodeToDelete != null) {
            parent = XMLUtils.remove( nodeToDelete );
        }
        return parent;
    }
    
	public String[] getStringArray(Object source, String evalExpression) {
		try {
			NodeList nl = (NodeList) xpath.evaluate(evalExpression, source,
					XPathConstants.NODESET);
			if (nl != null) {
				String[] entries = new String[nl.getLength()];
				for (int i = 0; i < nl.getLength(); i++) {
					entries[i] = nl.item(i).getTextContent();
				}
				return entries;
			}
		} catch (XPathExpressionException e) {
			log.error("Error creating record ids.", e);
		}
		return new String[0];
	}

    /** Get siblings of a node.
     * @param source Root node
     * @param xpathExpression get siblings of this node(s)
     * @param siblingNodeName name of the sibling nodes to return, pass null if all siblings matter
     * @param includeSelection include the node of which the siblings are detected 
     * @return ArrayList of sibling nodes
     */
    public ArrayList<Node> getSiblingsFromXPath(Object source, String xpathExpression, String siblingNodeName, boolean includeSelection) {
        ArrayList<Node> retList = new ArrayList<Node>();

        NodeList nodeList = getNodeList(source, xpathExpression);
        if(nodeList == null) {
            return retList;
        }

        // remember parents to parse children only once
        ArrayList<Node> parentsParsed = new ArrayList<Node>();

        // process all found nodes, may have same parent !
        for (int i=0; i < nodeList.getLength(); i++) {
            Node selectedNode = nodeList.item(i);
            
            // if parent already processed then skip
            Node parentNode = selectedNode.getParentNode();
            if (parentNode == null || parentsParsed.contains( parentNode )) {
                continue;
            }

            // process children, remember parent
            parentsParsed.add( parentNode );
            NodeList children = parentNode.getChildNodes();
            for (int j=0; j<children.getLength(); j++) {
                Node child = children.item(j);

                // exclude starting node ?
                if (!includeSelection) {
                    if (child.equals( selectedNode )) {
                        continue;
                    }
                }
                // exclude siblings not of given name
                if (siblingNodeName != null) {
                    if (!child.getNodeName().equals( siblingNodeName )) {
                        continue;
                    }
                }
                if (!retList.contains( child )) {
                    retList.add(child);                        
                }
            }
        }
        return retList;
    }
}
