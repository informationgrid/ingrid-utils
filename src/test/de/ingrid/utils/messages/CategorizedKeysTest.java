/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.messages;

import junit.framework.TestCase;

public class CategorizedKeysTest extends TestCase {

    private static final String BUNDLE_NAME = "/de/ingrid/utils/messages/messages.properties"; //$NON-NLS-1$

    public void testGetValue() throws Exception {
        CategorizedKeys keys = CategorizedKeys.get(BUNDLE_NAME);
        String value = keys.getString("aKey0");
        assertEquals("aValue0", value);
        String value1 = keys.getString("aKey1");
        assertEquals("aValue1", value1);
    }

    public void testGetCategories() throws Exception {
        CategorizedKeys keys = CategorizedKeys.get(BUNDLE_NAME);
        String[] categories = keys.getCategories();
        assertEquals(2, categories.length);
        String[] catKeys = keys.getKeysForCategory(categories[0]);
        assertEquals(1, catKeys.length);
    }

}
