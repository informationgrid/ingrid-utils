/*
 * Copyright (c) 1997-2007 by wemove GmbH
 */
package de.ingrid.utils.udk;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.ingrid.utils.xml.XMLSerializer;

/**
 * TODO Describe your created type (class, etc.) here.
 * 
 * @author joachim@wemove.com
 */
public class UtilsUDKCodeLists {

	private static final HashMap codeLists;

	private static final HashMap udk2codeList505Map;

	static {
		try {
			// Create the SessionFactory
			InputStream resourceAsStream = UtilsUDKCodeLists.class.getResourceAsStream("udk_codelists_serialized.xml");
			if (resourceAsStream == null) {
				resourceAsStream = UtilsUDKCodeLists.class.getClassLoader().getResourceAsStream(
						"udk_codelists_serialized.xml");
			}
			XMLSerializer serializer = new XMLSerializer();
			codeLists = (HashMap) serializer.deSerialize(resourceAsStream);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}

		/*
		 * mapping of UDK addresstypes to CSW address types
		 * 
		 * UDK | CSW (codelist 505)| Name 0 | 7 | Auskunft 1 | 3 | Datenhalter 2 |
		 * 2 | Datenverantwortung 3 | 1 | Anbieter 4 | 4 | Benutzer 5 | 5 |
		 * Vertrieb 6 | 6 | Herkunft 7 | 8 | Datenerfassung 8 | 9 | Auswertung 9 |
		 * 10 | Herausgeber 999 | keine Entsprechung, mapping auf codeListValue |
		 * Sonstige Angaben
		 */
		udk2codeList505Map = new HashMap();
		udk2codeList505Map.put("0", "7");
		udk2codeList505Map.put("1", "3");
		udk2codeList505Map.put("3", "1");
		udk2codeList505Map.put("7", "8");
		udk2codeList505Map.put("8", "9");
		udk2codeList505Map.put("9", "10");
	}

	/**
	 * Return the code list value specified by the codelist id, the domain id
	 * and the language id. If the value cannot be found, return "".
	 * 
	 * @param codeListId
	 * @param domainId
	 * @param langId
	 * @return
	 */
	public static String getCodeListEntryName(Long codeListId, Long domainId, Long langId) {
		try {
			return (String) ((HashMap) ((HashMap) codeLists.get(codeListId)).get(domainId)).get(langId);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * Returns a list of all domains of a codelist entries for a given language.
	 * This method can be used to lookup codelists, i.e. for UI selectbox
	 * initialization.
	 * 
	 * @param codeListId
	 * @param langId
	 * @return
	 */
	public static List getCodeList(Long codeListId, Long langId) {
		ArrayList result = new ArrayList();
		HashMap domain = (HashMap) codeLists.get(codeListId);
		for (Iterator it = domain.entrySet().iterator(); it.hasNext();) {
			Map.Entry domainEntry = (Map.Entry) it.next();
			String domainEntryValue = (String) ((HashMap) domainEntry.getValue()).get(langId);
			String domainEntryKey = domainEntry.getKey().toString();
			result.add(new CodeListEntry(domainEntryValue, codeListId, Long.valueOf(domainEntryKey), langId));
		}
		return result;
	}

	/**
	 * Return the domain id specified by the codeListId, domainValue, language
	 * Id. If the domain id cannot be found, return null.
	 * 
	 * @param codeListId
	 * @param domainValue
	 * @param langId
	 * @return
	 */
	public static String getCodeListDomainId(Long codeListId, String domainValue, Long langId) {
		HashMap domain = (HashMap) codeLists.get(codeListId);
		if (domain != null) {
			for (Iterator it = domain.entrySet().iterator(); it.hasNext();) {
				Map.Entry domainEntry = (Map.Entry) it.next();
				String domainEntryValue = (String) ((HashMap) domainEntry.getValue()).get(langId);
				if (domainEntryValue.equalsIgnoreCase(domainValue)) {
					return domainEntry.getKey().toString();
				}
			}
		}
		return null;
	}

	/**
	 * Return mapped value from the codeList505 to UDK mapping.
	 * 
	 * @param code
	 * @return
	 */
	public static String codeList505ToUDK(String code) {
		if (udk2codeList505Map.containsValue(code)) {
			for (Iterator it = udk2codeList505Map.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
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
	
	/**
	 * Returns an iso codeList entry based on an IGC code list domain id.
	 * The Data is based on ISO code lists from
	 * http://www.isotc211.org/2005/resources/Codelist/gmxCodelists.xml
	 * If the iso codelist entry cannot be found, the english translation of the
	 * IGC syslist will be returned. If this also cannot be found, null is returned.
	 * 
	 * @param codeListId
	 * @param igcId
	 * @return
	 */
	public static String getIsoCodeListEntryFromIgcId(Long codeListId, Long igcId) {
		String isoCode = getCodeListEntryName(codeListId, igcId, 150150150L);
		if (isoCode == null) {
			isoCode = getCodeListEntryName(codeListId, igcId, 94L);
		}
		return isoCode;
	}
	
	/**
	 * Returns the IDC domain list ID (sys list) based on an ISO code list entry.
	 * If the ISO entry cannot be found in the specified codeList, null will be returned.
	 * 
	 * @param codeListId
	 * @param isoCodeListEntry
	 * @return
	 */
	public static String getIgcIdFromIsoCodeListEntry(Long codeListId, String isoCodeListEntry) {
		return getCodeListDomainId(codeListId, isoCodeListEntry, 150150150L);
	}

}
