/**
 * 
 */
package de.ingrid.utils.xml;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * NamespaceContext representing the IGC profile. Maps the namespace URI
 * http://www.portalu.de/igc-profile to the prefix "igcp".
 * 
 * 
 * @author joachim
 * 
 */
public class IgcProfileNamespaceContext implements NamespaceContext {

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
     */
    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix.equals("igcp")) {
            return "http://www.portalu.de/igc-profile";
        } else {
            return XMLConstants.NULL_NS_URI;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.namespace.NamespaceContext#getPrefix(java.lang.String)
     */
    @Override
    public String getPrefix(String namespaceURI) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.namespace.NamespaceContext#getPrefixes(java.lang.String)
     */
    @Override
    public Iterator getPrefixes(String namespaceURI) {
        throw new UnsupportedOperationException();
    }

}
