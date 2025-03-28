/*-
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
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
package de.ingrid.utils.index;

import de.ingrid.utils.ElasticDocument;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vikram
 */
public class IndexUtilsTests {

    private IndexUtils idxUtils;

    @BeforeEach
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
    void testCaseInsensitiveIndexing() throws IOException {
        String testValue = "mock";
        String [] testKeys = {
                "lowercase",
                "UPPERCASE",
                "lowerCamelCase",
                "UpperCamelCase",
                "SNAKE_UPPER_CASE"
        };
        for (String key : testKeys) {
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
