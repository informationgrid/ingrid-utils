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
        
        String languageName = UtilsUDKCodeLists.getCodeListEntryName(new Long(99999999), new Long(123), new Long(150));
        assertEquals(true, languageName.equals("Englisch"));
        
        assertEquals("Boden", UtilsUDKCodeLists.getCodeListEntryName(1410L, 4L, 150L));
        assertEquals("Risk-Assessment", UtilsUDKCodeLists.getCodeListEntryName(1400L, 4L, 123L));
        assertEquals("Book/Monograph/Series", UtilsUDKCodeLists.getCodeListEntryName(3385L, 4L, 123L));
        
    }

    public void testGetCodeListDomainId() {
        
        String languageName = UtilsUDKCodeLists.getCodeListDomainId(new Long(99999999), "englisch", new Long(150));
        assertEquals(true, languageName.equals("123"));
        languageName = UtilsUDKCodeLists.getCodeListDomainId(new Long(99999999), "Französisch", new Long(150));
        assertEquals(true, languageName.equals("137"));

        String value = UtilsUDKCodeLists.getCodeListDomainId(new Long(505), "Point of Contact", new Long(123));
        assertEquals(true, value.equals("7"));
        assertEquals(true, UtilsUDKCodeLists.codeList505ToUDK(value).equals("0"));
        
    	assertEquals("5129", UtilsUDKCodeLists.getCodeListDomainId(101L, "European Vertical Reference Frame 2000", new Long(123)));
    	
    	assertEquals("318", UtilsUDKCodeLists.getCodeListDomainId(6100L, "Habitats and biotopes", new Long(123)));

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
        
        List list = UtilsUDKCodeLists.getCodeList(new Long(505), new Long(123));
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
        	}
        }
        list = UtilsUDKCodeLists.getCodeList(new Long(99999999), new Long(150));
        assertTrue(list.size() > 0);
        for (int i=0; i<list.size(); i++) {
        	CodeListEntry entry = (CodeListEntry)list.get(i);
        	if (entry.getDomainId().compareTo(new Long(134)) == 0) {
        		assertEquals(entry.getValue(), "Finnisch");
        		return;
        	}
        }
        assertTrue(false);
    }
    
    public void testGetIgcIdFromIsoCodeListEntry() {
    	assertEquals("7", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(505L, "pointOfContact"));
    	assertEquals("5", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(517L, "theme"));
    	assertEquals("8", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(524L, "otherRestrictions"));
    	assertEquals("10", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(527L, "imageryBaseMapsEarthCover"));
    	assertEquals("6", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(5100L, "other"));
    	assertEquals("900004", UtilsUDKCodeLists.getIgcIdFromIsoCodeListEntry(520L, "broadcast"));
    }
    
    public void testGetIsoCodeListEntryFromIgcId() {
    	assertEquals("pointOfContact", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(505L, 7L));
    	assertEquals("theme", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(517L, 5L));
    	assertEquals("inOperation", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(523L, 900001L));
    	assertEquals("otherRestrictions", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(524L, 8L));
    	assertEquals("invoke", UtilsUDKCodeLists.getIsoCodeListEntryFromIgcId(5100L, 5L));
    }
    
}
