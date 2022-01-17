/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2022 wemove digital solutions GmbH
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

package de.ingrid.utils.configuration;

import java.io.File;

import de.ingrid.utils.config.Configuration;
import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {

    public void testAddProperty() throws Exception {
        Configuration configuration = new Configuration();
        assertEquals(0, configuration.size());
        String value = "12";
        String key = "key";
        configuration.set(key, value, null);
        assertEquals(1, configuration.size());
        String string = configuration.get(key, null);
        assertEquals(value, string);
        assertEquals(12, configuration.getAsInt(key, 23));
    }

    public void testLoadConfiguration() throws Exception {
        Configuration configuration = new Configuration();
        for (int i = 0; i < 20; i++) {
            configuration.set("key" + i, "value" + i, "descriptopn" + i);
        }
        configuration.save(new File(System.getProperty("java.io.tmpdir"), "test.xml"));
        
        configuration.load(new File(System.getProperty("java.io.tmpdir"), "test.xml"));
        configuration.load(new File(System.getProperty("java.io.tmpdir"), "test.xml"));

    }
}
