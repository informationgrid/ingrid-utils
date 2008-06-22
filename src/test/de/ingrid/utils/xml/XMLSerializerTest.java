/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.xml;

import java.io.File;

import de.ingrid.utils.config.Property;

import junit.framework.TestCase;

public class XMLSerializerTest extends TestCase {

    public void testWriteDescription() throws Exception {
        DummyBean description = getDescription();
        File target = new File(System.getProperty("java.io.tmpdir"), "test.xml");
        XMLSerializer serializer = new XMLSerializer();
        serializer.serialize(description, target);
        DummyBean description2 = (DummyBean) serializer.deSerialize(target);
        assertEquals(description.getPlugId().getValue(), description2.getPlugId().getValue());

    }

    private DummyBean getDescription() {
        DummyBean description = new DummyBean();
        description.setCronBasedIndexing(new Property("true", "des"));
        description.setDataType(new Property("bla", "desc"));
        description.setOraganisation(new Property("organisation", "des"));
        description.setPersoneMail(new Property("mail", "des"));
        description.setPersonName(new Property("name", "des"));
        description.setPersonSureName(new Property("surename", "des"));
        description.setPlugId(new Property("aId", "desäü"));
        description.setWorkinDirectory(new Property(new File(System.getProperty("java.io.tmpdir")).getAbsolutePath(),
                "des"));
        return description;
    }
}
