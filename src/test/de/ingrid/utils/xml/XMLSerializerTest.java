/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.xml;

import java.io.File;

import junit.framework.TestCase;
import de.ingrid.utils.DescribableValue;

public class XMLSerializerTest extends TestCase {

    public void testWriteDescription() throws Exception {
        DummyBean description = getDescription();
        File target = new File(System.getProperty("java.io.tmpdir"), "test.xml");
        XMLSerializer.serializeAsXML(description, target);
        DummyBean description2 = (DummyBean) XMLSerializer.loadDescriptionFromXML(DummyBean.class, target);
        assertEquals(description.getPlugId().getValue(), description2.getPlugId().getValue());

    }

    private DummyBean getDescription() {
        DummyBean description = new DummyBean();
        description.setCronBasedIndexing(new DescribableValue("true", "des"));
        description.setDataType(new DescribableValue("bla", "desc"));
        description.setOraganisation(new DescribableValue("organisation", "des"));
        description.setPersoneMail(new DescribableValue("mail", "des"));
        description.setPersonName(new DescribableValue("name", "des"));
        description.setPersonSureName(new DescribableValue("surename", "des"));
        description.setPlugId(new DescribableValue("aId", "des"));
        description.setWorkinDirectory(new DescribableValue(new File(System.getProperty("java.io.tmpdir"))
                .getAbsolutePath(), "des"));
        return description;
    }
}
