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

package de.ingrid.utils.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategorizedKeysTest {

    private static final String BUNDLE_NAME = "/de/ingrid/utils/messages/messages.properties"; //$NON-NLS-1$

    @Test
    public void testGetValue() throws Exception {
        CategorizedKeys keys = CategorizedKeys.get(BUNDLE_NAME);
        String value = keys.getString("aKey0");
        assertEquals("aValue0", value);
        String value1 = keys.getString("aKey1");
        assertEquals("aValue1", value1);
    }

    @Test
    public void testGetCategories() throws Exception {
        CategorizedKeys keys = CategorizedKeys.get(BUNDLE_NAME);
        String[] categories = keys.getCategories();
        assertEquals(2, categories.length);
        String[] catKeys = keys.getKeysForCategory(categories[0]);
        assertEquals(1, catKeys.length);
    }

}
