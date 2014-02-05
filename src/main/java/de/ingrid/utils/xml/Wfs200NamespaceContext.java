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
public class Wfs200NamespaceContext implements NamespaceContext {

	public static String NAMESPACE_URI_WFS = "http://www.opengis.net/wfs/2.0";
	public static String NAMESPACE_URI_OWS11 = "http://www.opengis.net/ows/1.1";
	public static String NAMESPACE_URI_XLINK = "http://www.w3.org/1999/xlink";
	public static String NAMESPACE_URI_INSPIRE_COMMON = "http://inspire.ec.europa.eu/schemas/common/1.0";
	public static String NAMESPACE_URI_INSPIRE_DLS = "http://inspire.ec.europa.eu/schemas/inspire_dls/1.0";
	
	
	
	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("wfs20")) {
            return NAMESPACE_URI_WFS;
		} else if (prefix.equals("ows11")) {
		    return NAMESPACE_URI_OWS11;
		} else if (prefix.equals("inspire_dls")) {
            return NAMESPACE_URI_INSPIRE_DLS;
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
