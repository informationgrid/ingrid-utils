package de.ingrid.utils.xpath;

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
     *            NamespaceContext to use
     */
    public XPathUtils(NamespaceContext nsContext) {
        this.xpath = XPathFactory.newInstance().newXPath();
        this.xpath.setNamespaceContext(nsContext);
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

}
