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
public class WcsNamespaceContext implements NamespaceContext {

	public static String NAMESPACE_URI_WCS = "http://www.opengis.net/wcs";
	public static String NAMESPACE_URI_DEGREE = "http://www.deegree.org";
	public static String NAMESPACE_URI_XLINK = "http://www.w3.org/1999/xlink";
	public static String NAMESPACE_URI_GML = "http://www.opengis.net/gml";
	
	
	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("wcs")) {
            return NAMESPACE_URI_WCS;
		} else if (prefix.equals("degree")) {
		    return NAMESPACE_URI_DEGREE;
		} else if (prefix.equals("xlink")) {
		    return NAMESPACE_URI_XLINK;
		} else if (prefix.equals("gml")) {
		    return NAMESPACE_URI_GML;
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
