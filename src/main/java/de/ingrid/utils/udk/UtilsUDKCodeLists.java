/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2017 wemove digital solutions GmbH
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
 * Copyright (c) by wemove GmbH
 */
package de.ingrid.utils.udk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Code List utility class for extracting code list values and mapping between different code lists.
 * 
 * @author joachim@wemove.com
 */
public class UtilsUDKCodeLists {

	private static final Map<String, String> udk2codeList505Map;

	public static final String LANG_ID_ISO_ENTRY = "iso"; 
	/** DUMMY Language ID for fetching InGrid-Query value of a syslist entry
	 * (e.g. "LaermErschuetterung" for entry in env_topic syslist) */
	public static final String LANG_ID_INGRID_QUERY_VALUE = "req_value";

	public static final Long SYSLIST_ID_ENV_TOPICS = 1410L;

	static {
		/*
		 * mapping of UDK addresstypes to CSW address types
		 * 
		 * UDK | CSW (codelist 505)| Name 0 | 7 | Auskunft 1 | 3 | Datenhalter 2 |
		 * 2 | Datenverantwortung 3 | 1 | Anbieter 4 | 4 | Benutzer 5 | 5 |
		 * Vertrieb 6 | 6 | Herkunft 7 | 8 | Datenerfassung 8 | 9 | Auswertung 9 |
		 * 10 | Herausgeber 999 | keine Entsprechung, mapping auf codeListValue |
		 * Sonstige Angaben
		 */
		udk2codeList505Map = new HashMap<String, String>();
		udk2codeList505Map.put("0", "7");
		udk2codeList505Map.put("1", "3");
		udk2codeList505Map.put("3", "1");
		udk2codeList505Map.put("7", "8");
		udk2codeList505Map.put("8", "9");
		udk2codeList505Map.put("9", "10");
	}

	/**
	 * Return mapped value from the codeList505 to UDK mapping.
	 * 
	 * @param code
	 * @return
	 */
	public static String codeList505ToUDK(String code) {
		if (udk2codeList505Map.containsValue(code)) {
			for (Iterator<Map.Entry<String,String>> it = udk2codeList505Map.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String,String> entry = (Map.Entry<String,String>) it.next();
				if (((String) entry.getValue()).equals(code)) {
					return (String) entry.getKey();
				}
			}
		}
		return code;
	}

	/**
	 * Return mapped value from the UDK to codeList505 mapping.
	 * 
	 * @param code
	 * @return
	 */
	public static String udkToCodeList505(String code) {
		if (udk2codeList505Map.containsKey(code)) {
			return (String) udk2codeList505Map.get(code);
		}
		return code;
	}
	
}
