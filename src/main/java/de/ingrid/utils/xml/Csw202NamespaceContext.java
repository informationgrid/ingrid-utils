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
public class Csw202NamespaceContext implements NamespaceContext {

	public static String NAMESPACE_URI_CSW = "http://www.opengis.net/cat/csw/2.0.2";
	public static String NAMESPACE_URI_OGC = "http://www.opengis.net/ogc";
	public static String NAMESPACE_URI_GMD = "http://www.isotc211.org/2005/gmd";
	public static String NAMESPACE_URI_GCO = "http://www.isotc211.org/2005/gco";
	public static String NAMESPACE_URI_SRV = "http://www.isotc211.org/2005/srv";
	public static String NAMESPACE_URI_ISO = "http://www.opengis.net/cat/csw/apiso/1.0";
	public static String NAMESPACE_URI_XLINK = "http://www.w3.org/1999/xlink";
	public static String NAMESPACE_URI_GML = "http://www.opengis.net/gml";
	public static String NAMESPACE_URI_OWS = "http://www.opengis.net/ows";
	public static String NAMESPACE_URI_DCT = "http://purl.org/dc/terms/";
	public static String NAMESPACE_URI_DC = "http://purl.org/dc/elements/1.1/";
	
	
	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("csw")) {
            return NAMESPACE_URI_CSW;
		} else if (prefix.equals("ogc")) {
            return NAMESPACE_URI_OGC;
		} else if (prefix.equals("gmd")) {
            return NAMESPACE_URI_GMD;
		} else if (prefix.equals("gco")) {
            return NAMESPACE_URI_GCO;
		} else if (prefix.equals("srv")) {
            return NAMESPACE_URI_SRV;
		} else if (prefix.equals("iso")) {
            return NAMESPACE_URI_ISO;
		} else if (prefix.equals("xlink")) {
            return NAMESPACE_URI_XLINK;
		} else if (prefix.equals("gml")) {
            return NAMESPACE_URI_GML;
		} else if (prefix.equals("ows")) {
            return NAMESPACE_URI_OWS;
		} else if (prefix.equals("dct")) {
            return NAMESPACE_URI_DCT;
		} else if (prefix.equals("dc")) {
            return NAMESPACE_URI_DC;
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
