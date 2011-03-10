package de.ingrid.utils.udk;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import de.ingrid.utils.enumeration.IDbEnum;

/**
 * This class contains the MOST RECENT IGC Language Codelist and helper functions, e.g. mapping of language shortcut to language code.
 * For mapping of ALL languages to numeric IGC language code see the according word document
 * -> svn\ingrid-udk-importer\trunk\doc\syslist\ISO 639-2 Language Code List - *.doc
 */
public class UtilsLanguageCodelist {

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
	}

	/** MAP: IGC language code (150, 123, ...) to name of language in english <IGC language code, language name in english> */
	static public HashMap<Integer, String> languageCodelist_en = new LinkedHashMap<Integer, String>();
	static {
		languageCodelist_en.put(IGC_CODE_GERMAN, "German");
		languageCodelist_en.put(IGC_CODE_ENGLISH, "English");
	}

	/** MAP: ISO 639-1 language "shortcut" ("de", "en") to IGC language code <ISO 639-1 "shortcut", IGC language code>*/
	static private HashMap<String, Integer> languageISO639_1ToIGCCode = new HashMap<String, Integer>(); 
	static {
		languageISO639_1ToIGCCode.put("de", IGC_CODE_GERMAN);
		languageISO639_1ToIGCCode.put("en", IGC_CODE_ENGLISH);
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
	
	/** Determine language name from IGC language code.
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
