package de.ingrid.utils.xml;


/**
 * @author toan
 *
 */
public class IDFNamespaceContext extends Csw202NamespaceContext {

	public static String NAMESPACE_URI_IDF = "http://www.portalu.de/IDF/1.0";
	public static String NAMESPACE_URI_KML = "http://www.opengis.net/kml/2.2";
	
	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("idf")) {
            return NAMESPACE_URI_IDF;
		} else if (prefix.equals("kml")) {
            return NAMESPACE_URI_KML;
		} else {
            return super.getNamespaceURI(prefix);
		}
	}
}
