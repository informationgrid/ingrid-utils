/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.io.File;

import junit.framework.TestCase;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.xml.XMLSerializer;

public class PlugDescriptionTest extends TestCase {

    public void testSerialize() throws Exception {
        File target = new File("./testFile.xml");
        PlugDescription description1 = new PlugDescription();
        description1.setPersonName("bla");

        XMLSerializer serializer = new XMLSerializer();
        serializer.aliasClass(PlugDescription.class.getName(),
                PlugDescription.class);
        serializer.serialize(description1, target);

        PlugDescription description2 = (PlugDescription) serializer.deSerialize(target);

        assertEquals(description1, description2);
        target.delete();

    }
    
    public void testDataType() throws Exception {
        File target = new File("./testFile.xml");
        PlugDescription description = new PlugDescription();
        description.addDataType("A");
        description.addDataType("B");
        assertEquals(2, description.getDataTypes().length);
        assertEquals("A", description.getDataTypes()[0]);
        assertEquals("B", description.getDataTypes()[1]);
        XMLSerializer serializer = new XMLSerializer();
        serializer.aliasClass(PlugDescription.class.getName(),
                PlugDescription.class);
        serializer.serialize(description, target);

        PlugDescription description2 = (PlugDescription) serializer.deSerialize(target);
        assertEquals(2, description2.getDataTypes().length);
        assertEquals("A", description2.getDataTypes()[0]);
        assertEquals("B", description2.getDataTypes()[1]);
    }
    
    public void testAddBus() throws Exception {
        PlugDescription description = new PlugDescription();
        for (int i = 0; i < 100; i++) {
            description.addBusUrl(""+i);
            assertEquals(i+1, description.getBusUrls().length);
        }
        
        for (int i = 100; i >= 0; i--) {
            description.removeBusUrl(""+i);
            assertEquals(i, description.getBusUrls().length);
        }
    }

}
