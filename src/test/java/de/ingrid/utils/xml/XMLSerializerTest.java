/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2017 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
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

package de.ingrid.utils.xml;

import java.io.File;

import junit.framework.TestCase;
import de.ingrid.utils.config.Property;

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
        description.setPlugId(new Property("aId", "des"));
        description.setWorkinDirectory(new Property(new File(System.getProperty("java.io.tmpdir")).getAbsolutePath(),
                "des"));
        return description;
    }
}
