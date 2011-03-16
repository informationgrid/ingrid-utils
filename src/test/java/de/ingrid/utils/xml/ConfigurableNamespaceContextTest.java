/**
 * 
 */
package de.ingrid.utils.xml;

import junit.framework.TestCase;

/**
 * @author joachim
 *
 */
public class ConfigurableNamespaceContextTest extends TestCase {

    /**
     * Test method for {@link de.ingrid.utils.xml.ConfigurableNamespaceContext#getNamespaceURI(java.lang.String)}.
     */
    public void testGetNamespaceURI() {
        ConfigurableNamespaceContext cnc = new ConfigurableNamespaceContext();
        cnc.addNamespaceContext(new Csw202NamespaceContext());
        assertEquals(cnc.getNamespaceURI("gmd"), "http://www.isotc211.org/2005/gmd");
        cnc.addNamespaceContext(new IgcProfileNamespaceContext());
        assertEquals(cnc.getNamespaceURI("igcp"), "http://www.portalu.de/igc-profile");
        assertEquals(cnc.getNamespaceURI("gmd"), "http://www.isotc211.org/2005/gmd");
        assertEquals(cnc.getNamespaceURI("idf"), "");
        cnc.addNamespaceContext(new IDFNamespaceContext());
        assertEquals(cnc.getNamespaceURI("igcp"), "http://www.portalu.de/igc-profile");
        assertEquals(cnc.getNamespaceURI("gmd"), "http://www.isotc211.org/2005/gmd");
        assertEquals(cnc.getNamespaceURI("idf"), "http://www.portalu.de/IDF/1.0");
    }

}
