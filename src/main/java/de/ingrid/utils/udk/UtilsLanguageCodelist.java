/*
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
package de.ingrid.utils.udk;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.enumeration.IDbEnum;

/**
 * This class contains the MOST RECENT IGC Language Codelist and helper functions, e.g. mapping of language shortcut to language code.
 * For mapping of ALL languages to numeric IGC language code see the according word document
 * -> svn\ingrid-udk-importer\trunk\doc\syslist\ISO 639-2 Language Code List - *.doc
 */
public class UtilsLanguageCodelist {

    private final static Log log = LogFactory.getLog(UtilsLanguageCodelist.class);

	/** Type of ISO 639-2 code (bibliographic code, e.g. "ger", or terminology code, e.g. "deu") */
	public enum ISO_639_2_Type implements IDbEnum {
		BIBLIOGRAPHIC_CODE(":B"),
		TERMINOLOGY_CODE(":T");
		
		ISO_639_2_Type(String postfixInMap) {
			this.postfixInMap = postfixInMap;
		}
		/** returns the postfix added to the ISO 639-2 code in the map ! */
		public String getDbValue() {
			return postfixInMap;
		}
		String postfixInMap;
	}

	/** Syslist ID of language codelist. */
	static public int LANGUAGE_SYSLIST_ID = 99999999;

	/** IGC code of german language (= entryId in syslist).
	 * IGC codes are determined by doc of Kst, 
	 * see ingrid-udk-importer\trunk\doc\syslist\ISO 639-2 Language Code List - Codes for the representation of names of languages (Library of Congress).doc */
	static private int IGC_CODE_GERMAN = 150;
	/** IGC code of english language (= entryId in syslist).
	 * IGC codes are determined by doc of Kst, 
	 * see ingrid-udk-importer\trunk\doc\syslist\ISO 639-2 Language Code List - Codes for the representation of names of languages (Library of Congress).doc */
	static private int IGC_CODE_ENGLISH = 123;

	/** MAP: IGC language code (150, 123, ...) to name of language in german <IGC language code, language name in german> */
	static public HashMap<Integer, String> languageCodelist_de = new LinkedHashMap<Integer, String>();
	static {
		languageCodelist_de.put(IGC_CODE_GERMAN, "Deutsch");
		languageCodelist_de.put(IGC_CODE_ENGLISH, "Englisch");
		languageCodelist_de.put(65, "Bulgarisch");
		languageCodelist_de.put(101, "Tschechisch");
		languageCodelist_de.put(103, "Dänisch");
		languageCodelist_de.put(401, "Spanisch");
		languageCodelist_de.put(134, "Finnisch");
		languageCodelist_de.put(137, "Französisch");
		languageCodelist_de.put(164, "Griechisch");
		languageCodelist_de.put(183, "Ungarisch");
		languageCodelist_de.put(116, "Niederländisch");
		languageCodelist_de.put(346, "Polnisch");
		languageCodelist_de.put(348, "Portugiesisch");
		languageCodelist_de.put(360, "Rumänisch");
		languageCodelist_de.put(385, "Slowakisch");
		languageCodelist_de.put(386, "Slowenisch");
		languageCodelist_de.put(202, "Italienisch");
		languageCodelist_de.put(126, "Estnisch");
		languageCodelist_de.put(247, "Lettisch");
		languageCodelist_de.put(251, "Litauisch");
		languageCodelist_de.put(312, "Norwegisch");
		languageCodelist_de.put(363, "Russisch");
		languageCodelist_de.put(413, "Schwedisch");
		languageCodelist_de.put(284, "Maltesisch");
        languageCodelist_de.put(467, "Sorbisch");
        languageCodelist_de.put(182, "Obersorbisch");
        languageCodelist_de.put(113, "Niedersorbisch");
        languageCodelist_de.put(142, "Friesisch");
        languageCodelist_de.put(306, "Niedersächsisch");
	}

	/** MAP: IGC language code (150, 123, ...) to name of language in english <IGC language code, language name in english> */
	static public HashMap<Integer, String> languageCodelist_en = new LinkedHashMap<Integer, String>();
	static {
		languageCodelist_en.put(IGC_CODE_GERMAN, "German");
		languageCodelist_en.put(IGC_CODE_ENGLISH, "English");
		languageCodelist_en.put(65, "Bulgarian");
		languageCodelist_en.put(101, "Czech");
		languageCodelist_en.put(103, "Danish");
		languageCodelist_en.put(401, "Spanish");
		languageCodelist_en.put(134, "Finish");
		languageCodelist_en.put(137, "French");
		languageCodelist_en.put(164, "Greek");
		languageCodelist_en.put(183, "Hungarian");
		languageCodelist_en.put(116, "Dutch");
		languageCodelist_en.put(346, "Polish");
		languageCodelist_en.put(348, "Portuguese");
		languageCodelist_en.put(360, "Romanian");
		languageCodelist_en.put(385, "Slovakian");
		languageCodelist_en.put(386, "Slovenian");
		languageCodelist_en.put(202, "Italian");
		languageCodelist_en.put(126, "Estonian");
		languageCodelist_en.put(247, "Latvian");
		languageCodelist_en.put(251, "Lithuanian");
		languageCodelist_en.put(312, "Norwegian");
		languageCodelist_en.put(363, "Russian");
		languageCodelist_en.put(413, "Swedish");
		languageCodelist_en.put(284, "Maltese");
        languageCodelist_en.put(467, "Sorbian");
        languageCodelist_en.put(182, "Upper Sorbian");
        languageCodelist_en.put(113, "Lower Sorbian");
        languageCodelist_en.put(142, "Western Frisian");
        languageCodelist_en.put(306, "Low Saxon");
	}

	/** MAP: ISO 639-1 language "shortcut" ("de", "en") to IGC language code <ISO 639-1 "shortcut", IGC language code>*/
	static private HashMap<String, Integer> languageISO639_1ToIGCCode = new HashMap<String, Integer>(); 
	static {
		languageISO639_1ToIGCCode.put("de", IGC_CODE_GERMAN);
		languageISO639_1ToIGCCode.put("en", IGC_CODE_ENGLISH);
		languageISO639_1ToIGCCode.put("bg", 65);
		languageISO639_1ToIGCCode.put("cs", 101);
		languageISO639_1ToIGCCode.put("da", 103);
		languageISO639_1ToIGCCode.put("es", 401);
		languageISO639_1ToIGCCode.put("fi", 134);
		languageISO639_1ToIGCCode.put("fr", 137);
		languageISO639_1ToIGCCode.put("el", 164);
		languageISO639_1ToIGCCode.put("hu", 183);
		languageISO639_1ToIGCCode.put("nl", 116);
		languageISO639_1ToIGCCode.put("pl", 346);
		languageISO639_1ToIGCCode.put("pt", 348);
		languageISO639_1ToIGCCode.put("ro", 360);
		languageISO639_1ToIGCCode.put("sk", 385);
		languageISO639_1ToIGCCode.put("sl", 386);
		languageISO639_1ToIGCCode.put("it", 202);
		languageISO639_1ToIGCCode.put("et", 126);
		languageISO639_1ToIGCCode.put("lv", 247);
		languageISO639_1ToIGCCode.put("lt", 251);
		languageISO639_1ToIGCCode.put("nn", 312);
		languageISO639_1ToIGCCode.put("ru", 363);
		languageISO639_1ToIGCCode.put("sv", 413);
		languageISO639_1ToIGCCode.put("mt", 284);
        languageISO639_1ToIGCCode.put("fy", 142);
// NO ISO 639-1 codes for following languages !
//      languageISO639_1ToIGCCode.put("wen", 467);
//      languageISO639_1ToIGCCode.put("hsb", 182);
//      languageISO639_1ToIGCCode.put("dsb", 113);
//      languageISO639_1ToIGCCode.put("nds", 306);
	}

	/** MAP: ISO 639-2 language shortcut ("ger", "deu", "eng") to IGC language code <ISO 639-2 shortcut, IGC language code>*/
	static private HashMap<String, Integer> languageISO639_2ToIGCCode = new HashMap<String, Integer>(); 
	static {
		languageISO639_2ToIGCCode.put("deu", IGC_CODE_GERMAN);
		languageISO639_2ToIGCCode.put("ger", IGC_CODE_GERMAN);
		// if there are two ISO 639-2 codes for one language (see enum above ISO_639_2_Type), add postfix, so we can separate ! */
		languageISO639_2ToIGCCode.put("deu" + ISO_639_2_Type.TERMINOLOGY_CODE.getDbValue(), IGC_CODE_GERMAN);
		languageISO639_2ToIGCCode.put("ger" + ISO_639_2_Type.BIBLIOGRAPHIC_CODE.getDbValue(), IGC_CODE_GERMAN);
		languageISO639_2ToIGCCode.put("eng", IGC_CODE_ENGLISH);
		languageISO639_2ToIGCCode.put("bul", 65);
		languageISO639_2ToIGCCode.put("cze", 101);
		languageISO639_2ToIGCCode.put("dan", 103);
		languageISO639_2ToIGCCode.put("spa", 401);
		languageISO639_2ToIGCCode.put("fin", 134);
		languageISO639_2ToIGCCode.put("fre", 137);
		languageISO639_2ToIGCCode.put("gre", 164);
		languageISO639_2ToIGCCode.put("hun", 183);
		languageISO639_2ToIGCCode.put("dut", 116);
		languageISO639_2ToIGCCode.put("pol", 346);
		languageISO639_2ToIGCCode.put("por", 348);
		languageISO639_2ToIGCCode.put("rum", 360);
		languageISO639_2ToIGCCode.put("slo", 385);
		languageISO639_2ToIGCCode.put("slv", 386);
		languageISO639_2ToIGCCode.put("ita", 202);
		languageISO639_2ToIGCCode.put("est", 126);
		languageISO639_2ToIGCCode.put("lav", 247);
		languageISO639_2ToIGCCode.put("lit", 251);
		languageISO639_2ToIGCCode.put("nno", 312);
		languageISO639_2ToIGCCode.put("rus", 363);
		languageISO639_2ToIGCCode.put("swe", 413);
		languageISO639_2ToIGCCode.put("mlt", 284);
        languageISO639_2ToIGCCode.put("wen", 467);
        languageISO639_2ToIGCCode.put("hsb", 182);
        languageISO639_2ToIGCCode.put("dsb", 113);
        languageISO639_2ToIGCCode.put("fry", 142);
        languageISO639_2ToIGCCode.put("nds", 306);
	}
	

	/** Determine IGC language code from ISO 639-1 language code.
	 * @param langIso639_1 e.g. "de" or "en"
	 * @return IGC language code or null if not found
	 */
	static public Integer getCodeFromShortcut(String langIso639_1) {
		return languageISO639_1ToIGCCode.get(langIso639_1);
	}

	/** Determine IGC language code from ISO 639-2 language code.
	 * @param langIso639_2 e.g. "ger" or "eng"
	 * @return IGC language code or null if not found
	 */
	static public Integer getCodeFromIso639_2(String langIso639_2) {
		return languageISO639_2ToIGCCode.get(langIso639_2);
	}
	
	/** Determine language name from IGC language code. USES ENGLISH NAME if translation is missing.
	 * @param langCodeIGC IGC code of language, e.g. 150
	 * @param nameLangIso639_1 in which language should the name be returned, e.g. "de" for german name
	 * @return language name of IGC language code or null if not found !
	 */
	static public String getNameFromCode(Integer langCodeIGC, String nameLangIso639_1) {
		String langName = null;

		if ("de".equals(nameLangIso639_1)) {
			langName = languageCodelist_de.get(langCodeIGC);
		} else if ("en".equals(nameLangIso639_1)) {
			langName = languageCodelist_en.get(langCodeIGC);			
		} else {
			// default is ENGLISH !
			if (log.isWarnEnabled()) {
				log.warn("Translation of language code into language name not present for language '" + nameLangIso639_1 + "', we use name of language in ENGLISH !!!");
			}
			langName = languageCodelist_en.get(langCodeIGC);			
		}

		return langName;
	}

	/** Determine language name from ISO 639-1 code.
	 * @param langIso639_1 ISO 639-1 code of language, e.g. "de"
	 * @param nameLangIso639_1 ISO 639-1 code in which language should the name be returned, e.g. "de" for german name
	 * @return language name of IGC language code or null if not found !
	 */
	static public String getNameFromShortcut(String langIso639_1, String nameLangIso639_1) {
		return UtilsLanguageCodelist.getNameFromCode(UtilsLanguageCodelist.getCodeFromShortcut(langIso639_1), nameLangIso639_1);
	}

	/** Determine language name from ISO 639-2 code.
	 * @param langIso639_2 ISO 639-2 code of language, e.g. "ger"
	 * @param nameLangIso639_1 ISO 639-1 code in which language should the name be returned, e.g. "de" for german name
	 * @return language name of IGC language code or null if not found !
	 */
	static public String getNameFromIso639_2(String langIso639_2, String nameLangIso639_1) {
		return UtilsLanguageCodelist.getNameFromCode(UtilsLanguageCodelist.getCodeFromIso639_2(langIso639_2), nameLangIso639_1);
	}
	
	/** Determine ISO 639-1 language shortcut (e.g. "de, "en" ...") from IGC language code.
	 * @param langCodeIGC IGC code of language
	 * @return ISO 639-1 language shortcut or null if not found
	 */
	static public String getShortcutFromCode(Integer langCodeIGC) {
		for (Map.Entry<String, Integer> entry : languageISO639_1ToIGCCode.entrySet()) {
			if (entry.getValue().equals(langCodeIGC)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/** Determine ISO 639-2 language shortcut (e.g. "ger, "eng" ...") from IGC language code.
	 * @param langCodeIGC IGC code of language
	 * @param whichISO639_2 for some languages there are two ISO 639-2 codes, e.g. "ger" (BIBLIOGRAPHIC_CODE)
	 * 	or "deu" (TERMINOLOGY_CODE) for german ! Which one to get ?
	 * @return ISO 639-2 language shortcut (bibliographic code) or null if not found
	 */
	static public String getLanguageISO639_2FromIGCCode(Integer langCodeIGC, ISO_639_2_Type whichISO639_2) {
		String retValue = null;
		for (Map.Entry<String, Integer> entry : languageISO639_2ToIGCCode.entrySet()) {
			if (entry.getValue().equals(langCodeIGC)) {
				String iso639_2 = entry.getKey();
				// check whether we have multiple ISO 639-2, fetch correct one !
				if (iso639_2.length() == 3) {
					// no postfix, we remember this one, may be the only one !
					retValue = iso639_2;
				} else if (iso639_2.contains(whichISO639_2.getDbValue())) {
					// postfix set and matches, this is the right one !
					retValue = iso639_2.substring(0, 3);
					break;
				}
			}
		}
		return retValue;
	}
}
