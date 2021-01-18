/*-
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
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
package de.ingrid.utils.index;

import de.ingrid.utils.ElasticDocument;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vikram
 */
public class IndexUtilsTests {

    private IndexUtils idxUtils;

    @Before
    public void init() {
        ElasticDocument doc = new ElasticDocument();
        idxUtils = new IndexUtils(doc);
    }

    /**
     * Tests whether the index fields in the {@code ElasticDocument} are
     * converted to lowercase or not.
     * 
     * @see <a href="https://redmine.informationgrid.eu/issues/944">Issue 944</a>
     */
    @Test
    public void testCaseInsensitiveIndexing() throws IOException {
        String testValue = "mock";
        String [] testKeys = {
            "lowercase",
            "UPPERCASE",
            "lowerCamelCase",
            "UpperCamelCase",
            "SNAKE_UPPER_CASE"
        };
        for(String key: testKeys) {
            idxUtils.add(key, testValue);
        }
        ElasticDocument doc = idxUtils.getDocument();

        assertTrue(doc.containsKey("lowercase"));

        assertTrue(doc.containsKey("uppercase"));
        assertFalse(doc.containsKey("UPPERCASE"));

        assertTrue(doc.containsKey("lowercamelcase"));
        assertFalse(doc.containsKey("lowerCamelCase"));

        assertTrue(doc.containsKey("uppercamelcase"));
        assertFalse(doc.containsKey("UpperCamelCase"));

        assertTrue(doc.containsKey("snake_upper_case"));
        assertFalse(doc.containsKey("SNAKE_UPPER_CASE"));
    }
    
}
