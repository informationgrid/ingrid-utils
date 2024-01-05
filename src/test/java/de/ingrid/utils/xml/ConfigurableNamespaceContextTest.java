/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
/**
 * 
 */
package de.ingrid.utils.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author joachim
 *
 */
public class ConfigurableNamespaceContextTest {

    /**
     * Test method for {@link de.ingrid.utils.xml.ConfigurableNamespaceContext#getNamespaceURI(java.lang.String)}.
     */
    @Test
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
