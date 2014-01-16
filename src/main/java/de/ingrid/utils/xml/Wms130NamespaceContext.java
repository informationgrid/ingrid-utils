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
public class Wms130NamespaceContext implements NamespaceContext {

	public static String NAMESPACE_URI_WMS = "http://www.opengis.net/wms";
	public static String NAMESPACE_URI_XLINK = "http://www.w3.org/1999/xlink";
	public static String NAMESPACE_URI_INSPIRE_COMMON = "http://inspire.ec.europa.eu/schemas/common/1.0"; 
	public static String NAMESPACE_URI_INSPIRE_VS = "http://inspire.ec.europa.eu/schemas/inspire_vs/1.0";	
	
	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("wms")) {
            return NAMESPACE_URI_WMS;
		} else if (prefix.equals("inspire_vs")) {
		    return NAMESPACE_URI_INSPIRE_VS;
		} else if (prefix.equals("inspire_common")) {
		    return NAMESPACE_URI_INSPIRE_COMMON;
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
