/*
 * Copyright (c) 1997-2007 by wemove GmbH
 */
package de.ingrid.utils.udk;

import junit.framework.TestCase;
import de.ingrid.utils.udk.UtilsLanguageCodelist.ISO_639_2_Type;

public class UtilsLanguageCodeListTest extends TestCase {

	/** IGC code of german language (= entryId in syslist).
	 * IGC codes are determined by doc of Kst, 
	 * see ingrid-udk-importer\trunk\doc\syslist\ISO 639-2 Language Code List - Codes for the representation of names of languages (Library of Congress).doc */
	private static Integer IGC_CODE_DE = 150;
	private static Integer IGC_CODE_EN = 123;
	
    public void testGetCodeFromShortcut() {
    	assertEquals(IGC_CODE_DE, UtilsLanguageCodelist.getCodeFromShortcut("de"));
    	assertEquals(IGC_CODE_EN, UtilsLanguageCodelist.getCodeFromShortcut("en"));
    	assertNull(UtilsLanguageCodelist.getCodeFromShortcut("99"));
    }

    public void testGetCodeFromIso639_2() {
    	assertEquals(IGC_CODE_DE, UtilsLanguageCodelist.getCodeFromIso639_2("deu"));
    	assertEquals(IGC_CODE_DE, UtilsLanguageCodelist.getCodeFromIso639_2("ger"));
    	assertEquals(IGC_CODE_EN, UtilsLanguageCodelist.getCodeFromIso639_2("eng"));
    	assertNull(UtilsLanguageCodelist.getCodeFromIso639_2("999"));
    }

    public void testGetNameFromCode() {
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromCode(IGC_CODE_DE, "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromCode(IGC_CODE_DE, "en"));
    	assertEquals("Englisch", UtilsLanguageCodelist.getNameFromCode(IGC_CODE_EN, "de"));
    	assertEquals("English", UtilsLanguageCodelist.getNameFromCode(IGC_CODE_EN, "en"));
    	assertNull(UtilsLanguageCodelist.getNameFromCode(99999, "de"));
    }

    public void testGetNameFromShortcut() {
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromShortcut("de", "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromShortcut("de", "en"));
    	assertEquals("Englisch", UtilsLanguageCodelist.getNameFromShortcut("en", "de"));
    	assertEquals("English", UtilsLanguageCodelist.getNameFromShortcut("en", "en"));
    	assertNull(UtilsLanguageCodelist.getNameFromShortcut("99", "de"));
    }

    public void testGetNameFromIso639_2() {
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromIso639_2("deu", "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromIso639_2("deu", "en"));
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromIso639_2("ger", "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromIso639_2("ger", "en"));
    	assertEquals("Englisch", UtilsLanguageCodelist.getNameFromIso639_2("eng", "de"));
    	assertEquals("English", UtilsLanguageCodelist.getNameFromIso639_2("eng", "en"));
    	assertNull(UtilsLanguageCodelist.getNameFromIso639_2("999", "de"));
    }

    public void testGetShortcutFromCode() {
    	assertEquals("de", UtilsLanguageCodelist.getShortcutFromCode(IGC_CODE_DE));
    	assertEquals("en", UtilsLanguageCodelist.getShortcutFromCode(IGC_CODE_EN));
    	assertNull(UtilsLanguageCodelist.getShortcutFromCode(-1));
    }

    public void testGetLanguageISO639_2FromIGCCode() {
    	assertEquals("ger", UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			IGC_CODE_DE, ISO_639_2_Type.BIBLIOGRAPHIC_CODE));
    	assertEquals("deu", UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			IGC_CODE_DE, ISO_639_2_Type.TERMINOLOGY_CODE));
    	assertEquals("eng", UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			IGC_CODE_EN, ISO_639_2_Type.BIBLIOGRAPHIC_CODE));
    	assertEquals("eng", UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			IGC_CODE_EN, ISO_639_2_Type.TERMINOLOGY_CODE));
    	assertNull(UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			-1, ISO_639_2_Type.TERMINOLOGY_CODE));
    }
}
