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
