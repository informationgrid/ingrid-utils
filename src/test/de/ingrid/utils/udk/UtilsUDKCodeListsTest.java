/*
 * Copyright (c) 1997-2007 by wemove GmbH
 */
package de.ingrid.utils.udk;

import junit.framework.TestCase;

public class UtilsUDKCodeListsTest extends TestCase {

    /*
     * Test method for 'de.ingrid.utils.udk.UtilsUDKCodeLists.getCodeListEntryName(Long, Long, Long)'
     */
    public void testGetCodeListEntryName() {
        
        String languageName = UtilsUDKCodeLists.getCodeListEntryName(new Long(99999999), new Long(94), new Long(121));
        assertEquals(true, languageName.equals("Englisch"));
    }

}
