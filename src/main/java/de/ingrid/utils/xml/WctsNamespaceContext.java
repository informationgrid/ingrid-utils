/**
 * 
 */
package de.ingrid.utils.xml;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * @author joachim
 *
 */
public class WctsNamespaceContext implements NamespaceContext {

	public static String NAMESPACE_URI_WCTS = "http://www.opengeospatial.net/wcts";
	public static String NAMESPACE_URI_OWSGEO = "http://www.opengeospatial.net/ows";
	public static String NAMESPACE_URI_XLINK = "http://www.w3.org/1999/xlink";
	
	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("wcts")) {
            return NAMESPACE_URI_WCTS;
		} else if (prefix.equals("owsgeo")) {
		    return NAMESPACE_URI_OWSGEO;
		} else if (prefix.equals("xlink")) {
		    return NAMESPACE_URI_XLINK;
		} else {
            return XMLConstants.NULL_NS_URI;
		}
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefix(java.lang.String)
	 */
	@Override
	public String getPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefixes(java.lang.String)
	 */
	@Override
	public Iterator getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

}
