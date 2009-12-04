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

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("csw")) {
            return "http://www.opengis.net/cat/csw/2.0.2";
		} else if (prefix.equals("ogc")) {
            return "http://www.opengis.net/ogc";
		} else if (prefix.equals("gmd")) {
            return "http://www.isotc211.org/2005/gmd";
		} else if (prefix.equals("gco")) {
            return "http://www.isotc211.org/2005/gco";
		} else if (prefix.equals("srv")) {
            return "http://www.isotc211.org/2005/srv";
		} else if (prefix.equals("iso")) {
            return "http://www.opengis.net/cat/csw/apiso/1.0";
		} else if (prefix.equals("xlink")) {
            return "http://www.w3.org/1999/xlink";
		} else if (prefix.equals("gml")) {
            return "http://www.opengis.net/gml";
		} else if (prefix.equals("ows")) {
            return "http://www.opengis.net/ows";
		} else if (prefix.equals("dct")) {
            return "http://purl.org/dc/terms/";
		} else if (prefix.equals("dc")) {
            return "http://purl.org/dc/elements/1.1/";
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
