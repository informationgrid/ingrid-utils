/*
 * Copyright (c) 1997-2007 by wemove GmbH
 */
package de.ingrid.utils.udk;

import java.util.List;

import junit.framework.TestCase;

public class UtilsUDKCodeListsTest extends TestCase {

    /*
     * Test method for 'de.ingrid.utils.udk.UtilsUDKCodeLists.getCodeListEntryName(Long, Long, Long)'
     */
    public void testGetCodeListEntryName() {
        
        String languageName = UtilsUDKCodeLists.getCodeListEntryName(new Long(99999999), new Long(94), new Long(121));
        assertEquals(true, languageName.equals("Englisch"));
    }

    public void testGetCodeListDomainId() {
        
        String languageName = UtilsUDKCodeLists.getCodeListDomainId(new Long(99999999), "englisch", new Long(121));
        assertEquals(true, languageName.equals("94"));
        
        String value = UtilsUDKCodeLists.getCodeListDomainId(new Long(505), "Point of Contact", new Long(94));
        assertEquals(true, value.equals("7"));
        assertEquals(true, UtilsUDKCodeLists.codeList505ToUDK(value).equals("0"));
    }
    
    
    public void testCodeList505ToUDK() {
        /* mapping of UDK addresstypes to CSW address types
        
        UDK | CSW (codelist 505)| Name
        0 | 7 | Auskunft
        1 | 3 | Datenhalter
        2 | 2 | Datenverantwortung
        3 | 1 | Anbieter
        4 | 4 | Benutzer
        5 | 5 | Vertrieb
        6 | 6 | Herkunft
        7 | 8 | Datenerfassung
        8 | 9 | Auswertung
        9 | 10 | Herausgeber
        999 | keine Entsprechung, mapping auf codeListValue | Sonstige Angaben
         */
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("7"), "0");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("3"), "1");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("2"), "2");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("1"), "3");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("4"), "4");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("5"), "5");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("6"), "6");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("8"), "7");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("9"), "8");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("10"), "9");
    }

    public void testUdkToCodeList505() {
        /* mapping of UDK addresstypes to CSW address types
        
        UDK | CSW (codelist 505)| Name
        0 | 7 | Auskunft
        1 | 3 | Datenhalter
        2 | 2 | Datenverantwortung
        3 | 1 | Anbieter
        4 | 4 | Benutzer
        5 | 5 | Vertrieb
        6 | 6 | Herkunft
        7 | 8 | Datenerfassung
        8 | 9 | Auswertung
        9 | 10 | Herausgeber
        999 | keine Entsprechung, mapping auf codeListValue | Sonstige Angaben
         */
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("0"), "7");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("1"), "3");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("2"), "2");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("3"), "1");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("4"), "4");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("5"), "5");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("6"), "6");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("7"), "8");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("8"), "9");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("9"), "10");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("999"), "999");
    }

    public void testGetCodeList() {
        
        List list = UtilsUDKCodeLists.getCodeList(new Long(505), new Long(94));
        assertTrue(list.size() > 0);
        for (int i=0; i<list.size(); i++) {
        	CodeListEntry entry = (CodeListEntry)list.get(i);
        	if (entry.getDomainId().compareTo(new Long(7)) == 0) {
        		assertEquals(entry.getValue(), "Point of Contact");
        	}
        }
        list = UtilsUDKCodeLists.getCodeList(new Long(517), new Long(150150150));
        assertTrue(list.size() > 0);
        for (int i=0; i<list.size(); i++) {
        	CodeListEntry entry = (CodeListEntry)list.get(i);
        	if (entry.getDomainId().compareTo(new Long(5)) == 0) {
        		assertEquals(entry.getValue(), "theme");
        		return;
        	}
        }
        assertTrue(false);
    }
    
    public void testGetIgcIdFromIsoCodeListEntry() {
    	assertEquals("7", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(505L, "pointOfContact"));
    	assertEquals("5", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(517L, "theme"));
    }
    
    public void testGetIsoCodeListEntryFromIgcId() {
    	assertEquals("pointOfContact", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(505L, 7L));
    	assertEquals("theme", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(517L, 5L));
    	assertEquals("inOperation", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(523L, 900001L));
    }
    
}
