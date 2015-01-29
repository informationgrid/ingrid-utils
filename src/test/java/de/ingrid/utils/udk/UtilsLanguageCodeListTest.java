/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2015 wemove digital solutions GmbH
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
    	assertEquals(new Integer(101), UtilsLanguageCodelist.getCodeFromShortcut("cs"));
    	assertEquals(new Integer(312), UtilsLanguageCodelist.getCodeFromShortcut("nn"));
    	assertEquals(new Integer(137), UtilsLanguageCodelist.getCodeFromShortcut("fr"));
    	assertNull(UtilsLanguageCodelist.getCodeFromShortcut("99"));
    }

    public void testGetCodeFromIso639_2() {
    	assertEquals(IGC_CODE_DE, UtilsLanguageCodelist.getCodeFromIso639_2("deu"));
    	assertEquals(IGC_CODE_DE, UtilsLanguageCodelist.getCodeFromIso639_2("ger"));
    	assertEquals(IGC_CODE_EN, UtilsLanguageCodelist.getCodeFromIso639_2("eng"));
    	assertEquals(new Integer(101), UtilsLanguageCodelist.getCodeFromIso639_2("cze"));
    	assertEquals(new Integer(312), UtilsLanguageCodelist.getCodeFromIso639_2("nno"));
    	assertEquals(new Integer(137), UtilsLanguageCodelist.getCodeFromIso639_2("fre"));
    	assertNull(UtilsLanguageCodelist.getCodeFromIso639_2("999"));
    }

    public void testGetNameFromCode() {
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromCode(IGC_CODE_DE, "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromCode(IGC_CODE_DE, "en"));
    	assertEquals("Englisch", UtilsLanguageCodelist.getNameFromCode(IGC_CODE_EN, "de"));
    	assertEquals("Tschechisch", UtilsLanguageCodelist.getNameFromCode(new Integer(101), "de"));
    	assertEquals("Czech", UtilsLanguageCodelist.getNameFromCode(new Integer(101), "en"));
    	assertEquals("Norwegisch", UtilsLanguageCodelist.getNameFromCode(new Integer(312), "de"));
    	assertNull(UtilsLanguageCodelist.getNameFromCode(99999, "de"));
    }

    public void testGetNameFromShortcut() {
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromShortcut("de", "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromShortcut("de", "en"));
    	assertEquals("Englisch", UtilsLanguageCodelist.getNameFromShortcut("en", "de"));
    	assertEquals("English", UtilsLanguageCodelist.getNameFromShortcut("en", "en"));
    	assertEquals("Tschechisch", UtilsLanguageCodelist.getNameFromShortcut("cs", "de"));
    	assertEquals("Czech", UtilsLanguageCodelist.getNameFromShortcut("cs", "en"));
    	assertEquals("Norwegisch", UtilsLanguageCodelist.getNameFromShortcut("nn", "de"));
    	assertNull(UtilsLanguageCodelist.getNameFromShortcut("99", "de"));
    }

    public void testGetNameFromIso639_2() {
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromIso639_2("deu", "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromIso639_2("deu", "en"));
    	assertEquals("Deutsch", UtilsLanguageCodelist.getNameFromIso639_2("ger", "de"));
    	assertEquals("German", UtilsLanguageCodelist.getNameFromIso639_2("ger", "en"));
    	assertEquals("Englisch", UtilsLanguageCodelist.getNameFromIso639_2("eng", "de"));
    	assertEquals("English", UtilsLanguageCodelist.getNameFromIso639_2("eng", "en"));
    	assertEquals("Tschechisch", UtilsLanguageCodelist.getNameFromIso639_2("cze", "de"));
    	assertEquals("Czech", UtilsLanguageCodelist.getNameFromIso639_2("cze", "en"));
    	assertEquals("Norwegisch", UtilsLanguageCodelist.getNameFromIso639_2("nno", "de"));
    	assertNull(UtilsLanguageCodelist.getNameFromIso639_2("999", "de"));
    }

    public void testGetShortcutFromCode() {
    	assertEquals("de", UtilsLanguageCodelist.getShortcutFromCode(IGC_CODE_DE));
    	assertEquals("en", UtilsLanguageCodelist.getShortcutFromCode(IGC_CODE_EN));
    	assertEquals("cs", UtilsLanguageCodelist.getShortcutFromCode(101));
    	assertEquals("nn", UtilsLanguageCodelist.getShortcutFromCode(312));
    	assertEquals("fr", UtilsLanguageCodelist.getShortcutFromCode(137));
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
    	assertEquals("cze", UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			101, ISO_639_2_Type.TERMINOLOGY_CODE));
    	assertEquals("cze", UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			101, ISO_639_2_Type.BIBLIOGRAPHIC_CODE));
    	assertEquals("fre", UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			137, ISO_639_2_Type.BIBLIOGRAPHIC_CODE));
    	assertNull(UtilsLanguageCodelist.getLanguageISO639_2FromIGCCode(
    			-1, ISO_639_2_Type.TERMINOLOGY_CODE));
    }
}
