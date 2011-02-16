package de.ingrid.utils.xml;


/**
 * @author toan
 *
 */
public class IDFNamespaceContext extends Csw202NamespaceContext {

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("idf")) {
            return "http://www.portalu.de/IDF/1.0";
		} else {
            return super.getNamespaceURI(prefix);
		}
	}
}
