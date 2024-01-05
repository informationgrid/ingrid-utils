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
/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.io.File;

import de.ingrid.utils.xml.XMLSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for {@link de.ingrid.utils.PlugDescription}.
 * 
 * <p/>created on 29.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class PlugDescriptionTest {

    private File fXmlFile = new File("target/testFile.xml");

    @BeforeEach
    public void setUp() throws Exception {
        this.fXmlFile.getParentFile().mkdirs();
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.fXmlFile.delete();
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSerialize() throws Exception {
        PlugDescription description1 = new PlugDescription();
        description1.setPersonName("bla");

        XMLSerializer serializer = new XMLSerializer();
        serializer.aliasClass(PlugDescription.class.getName(), PlugDescription.class);
        serializer.serialize(description1, this.fXmlFile);

        PlugDescription description2 = (PlugDescription) serializer.deSerialize(this.fXmlFile);
        assertEquals(description1, description2);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testDataType() throws Exception {
        PlugDescription description = new PlugDescription();
        description.addDataType("A");
        description.addDataType("B");
        assertEquals(2, description.getDataTypes().length);
        assertEquals("A", description.getDataTypes()[0]);
        assertEquals("B", description.getDataTypes()[1]);
        XMLSerializer serializer = new XMLSerializer();
        serializer.aliasClass(PlugDescription.class.getName(), PlugDescription.class);
        serializer.serialize(description, this.fXmlFile);

        PlugDescription description2 = (PlugDescription) serializer.deSerialize(this.fXmlFile);
        assertEquals(2, description2.getDataTypes().length);
        assertEquals("A", description2.getDataTypes()[0]);
        assertEquals("B", description2.getDataTypes()[1]);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testAddBus() throws Exception {
        PlugDescription description = new PlugDescription();
        for (int i = 0; i < 100; i++) {
            description.addBusUrl("" + i);
            assertEquals(i + 1, description.getBusUrls().length);
        }

        for (int i = 100; i >= 0; i--) {
            description.removeBusUrl("" + i);
            assertEquals(i, description.getBusUrls().length);
        }
    }


    @Test
    public void testHashCode() throws Exception {
        PlugDescription plugDescription = new PlugDescription();
        plugDescription.addPartner("foo");
        plugDescription.addProvider("bar");
        plugDescription.addBusUrl("foobar");

        PlugDescription plugDescription2 = new PlugDescription();
        plugDescription2.addPartner("foo");
        plugDescription2.addProvider("bar");
        plugDescription2.addBusUrl("foobar");

        assertEquals(plugDescription.hashCode(), plugDescription2.hashCode());
        assertEquals(plugDescription, plugDescription2);

        plugDescription = new PlugDescription();
        plugDescription.addPartner("bar");
        plugDescription.addProvider("foo");
        plugDescription.addBusUrl("foobar");

        plugDescription2 = new PlugDescription();
        plugDescription2.addPartner("foo");
        plugDescription2.addProvider("bar");
        plugDescription2.addBusUrl("foobar");

        assertNotNull(plugDescription2.hashCode());
        assertNotEquals(plugDescription, plugDescription2);

        
    }
}
