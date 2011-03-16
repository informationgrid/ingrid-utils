/**
 * 
 */
package de.ingrid.utils.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * This class acts as a container for namespaces. It can be used to supply
 * different namespace contexts to a XPATH instance.
 * 
 * 
 * @author joachim@wemove.com
 * 
 */
public class ConfigurableNamespaceContext implements NamespaceContext {

    List<NamespaceContext> namespaceContextList = new ArrayList<NamespaceContext>();

    public void addNamespaceContext(NamespaceContext nc) {
        namespaceContextList.add(nc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
     */
    @Override
    public String getNamespaceURI(String prefix) {
        String uri = null;
        for (NamespaceContext nc : namespaceContextList) {
            uri = nc.getNamespaceURI(prefix);
            if (uri != null && !uri.equals(XMLConstants.NULL_NS_URI)) {
                return uri;
            }
        }
        return XMLConstants.NULL_NS_URI;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.namespace.NamespaceContext#getPrefix(java.lang.String)
     */
    @Override
    public String getPrefix(String arg0) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.namespace.NamespaceContext#getPrefixes(java.lang.String)
     */
    @Override
    public Iterator getPrefixes(String arg0) {
        throw new UnsupportedOperationException();
    }

}
