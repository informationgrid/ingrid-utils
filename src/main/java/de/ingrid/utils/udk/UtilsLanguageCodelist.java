package de.ingrid.utils.udk;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class contains the MOST RECENT IGC Language Codelist and helper functions, e.g. mapping of language shortcut to language code.
 * For mapping of ALL languages to numeric IGC language code see the according word document
 * -> svn\ingrid-udk-importer\trunk\doc\syslist\ISO 639-2 Language Code List - *.doc
 */
public class UtilsLanguageCodelist {

	/** Syslist ID of language codelist. */
	static public int LANGUAGE_SYSLIST_ID = 99999999;

	/** IGC code of german language (= entryId in syslist) */
	static private int IGC_CODE_GERMAN = 150;
	/** IGC code of english language (= entryId in syslist) */
	static private int IGC_CODE_ENGLISH = 123;

	/** MAP: syslist with german names of languages <IGC language code, german language name> */
	static public HashMap<Integer, String> languageCodelist_de = new LinkedHashMap<Integer, String>();
	static {
		languageCodelist_de.put(IGC_CODE_GERMAN, "Deutsch");
		languageCodelist_de.put(IGC_CODE_ENGLISH, "Englisch");
	}

	/** MAP: syslist with english names of languages <IGC language code, english language name> */
	static public HashMap<Integer, String> languageCodelist_en = new LinkedHashMap<Integer, String>();
	static {
		languageCodelist_en.put(IGC_CODE_GERMAN, "German");
		languageCodelist_en.put(IGC_CODE_ENGLISH, "English");
	}

	/** MAP: language shortcut ("de", "en") to language code <ISO language shortcut, IGC language code>*/
	static private HashMap<String, Integer> languageShortcutToCode = new HashMap<String, Integer>(); 
	static {
		languageShortcutToCode.put("de", IGC_CODE_GERMAN);
		languageShortcutToCode.put("en", IGC_CODE_ENGLISH);
	}


	/** Determine IGC language code from language shortcut.
	 * @param languageShortcut e.g. "de" or "en"
	 * @return IGC language code or null if not found
	 */
	static public Integer getCodeFromShortcut(String languageShortcut) {
		return languageShortcutToCode.get(languageShortcut);
	}

	/** Determine language name from IGC language code.
	 * @param languageCode IGC code of language
	 * @param languageShortcut in which language should the name be returned, e.g. "de" for german name
	 * @return language name of IGC language code or null if not found !
	 */
	static public String getNameFromCode(Integer languageCode, String languageShortcut) {
		String langName = null;

		if ("de".equals(languageShortcut)) {
			langName = languageCodelist_de.get(languageCode);
		} else if ("en".equals(languageShortcut)) {
			langName = languageCodelist_en.get(languageCode);			
		}

		return langName;
	}

	/** Determine language name from IGC language code.
	 * @param languageIsoCode ISO 639-1 code of language
	 * @param languageShortcut in which language should the name be returned, e.g. "de" for german name
	 * @return language name of IGC language code or null if not found !
	 */
	static public String getNameFromShortcut(String languageIsoCode, String languageShortcut) {
		return UtilsLanguageCodelist.getNameFromCode(UtilsLanguageCodelist.getCodeFromShortcut(languageIsoCode), languageShortcut);
	}
	
	/** Determine language shortcut (e.g. "de, "en" ...") from IGC language code.
	 * @param languageCode IGC code of language
	 * @return language shortcut or null if not found
	 */
	static public String getShortcutFromCode(Integer languageCode) {
		for (Map.Entry<String, Integer> entry : languageShortcutToCode.entrySet()) {
			if (entry.getValue().equals(languageCode)) {
				return entry.getKey();
			}
		}
		return null;
	}
}
