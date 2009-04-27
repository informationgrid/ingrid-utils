package de.ingrid.utils.udk;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class contains the MOST RECENT IGC Country Codelist and helper functions.
 */
public class UtilsCountryCodelist {

	/** Syslist ID of country codelist. */
	static public int COUNTRY_SYSLIST_ID = 6200;

	/** MAP: syslist with german names of countries <ISO country code, german name of country> */
	static public HashMap<Integer, String> countryCodelist_de = new LinkedHashMap<Integer, String>();
	static {
		countryCodelist_de.put(new Integer("008"), "Albanien");
		countryCodelist_de.put(new Integer("020"), "Andorra");
		countryCodelist_de.put(new Integer("040"), "\u00d6sterreich");
		countryCodelist_de.put(new Integer("112"), "Wei\u00dfrussland");
		countryCodelist_de.put(new Integer("056"), "Belgien");
		countryCodelist_de.put(new Integer("070"), "Bosnien und Herzegowina");
		countryCodelist_de.put(new Integer("100"), "Bulgarien");
		countryCodelist_de.put(new Integer("191"), "Kroatien");
		countryCodelist_de.put(new Integer("196"), "Zypern");
		countryCodelist_de.put(new Integer("203"), "Tschechische Republik");
		countryCodelist_de.put(new Integer("208"), "D\u00e4nemark");
		countryCodelist_de.put(new Integer("233"), "Estland");
		countryCodelist_de.put(new Integer("246"), "Finnland");
		countryCodelist_de.put(new Integer("250"), "Frankreich");
		countryCodelist_de.put(new Integer("276"), "Deutschland");
		countryCodelist_de.put(new Integer("292"), "Gibraltar");
		countryCodelist_de.put(new Integer("300"), "Griechenland");
		countryCodelist_de.put(new Integer("348"), "Ungarn");
		countryCodelist_de.put(new Integer("352"), "Island");
		countryCodelist_de.put(new Integer("372"), "Irland");
		countryCodelist_de.put(new Integer("380"), "Italien");
		countryCodelist_de.put(new Integer("428"), "Lettland");
		countryCodelist_de.put(new Integer("438"), "Liechtenstein");
		countryCodelist_de.put(new Integer("440"), "Litauen");
		countryCodelist_de.put(new Integer("442"), "Luxemburg");
		countryCodelist_de.put(new Integer("807"), "Mazedonien");
		countryCodelist_de.put(new Integer("450"), "Madagaskar");
		countryCodelist_de.put(new Integer("470"), "Malta");
		countryCodelist_de.put(new Integer("498"), "Moldawien");
		countryCodelist_de.put(new Integer("492"), "Monaco");
		countryCodelist_de.put(new Integer("499"), "Montenegro");
		countryCodelist_de.put(new Integer("528"), "Niederlande");
		countryCodelist_de.put(new Integer("578"), "Norwegen");
		countryCodelist_de.put(new Integer("616"), "Polen");
		countryCodelist_de.put(new Integer("620"), "Portugal");
		countryCodelist_de.put(new Integer("642"), "Rum\u00e4nien");
		countryCodelist_de.put(new Integer("643"), "Russische F\u00f6deration");
		countryCodelist_de.put(new Integer("688"), "Serbien");
		countryCodelist_de.put(new Integer("703"), "Slowakei");
		countryCodelist_de.put(new Integer("705"), "Slowenien");
		countryCodelist_de.put(new Integer("724"), "Spanien");
		countryCodelist_de.put(new Integer("752"), "Schweden");
		countryCodelist_de.put(new Integer("756"), "Schweiz");
		countryCodelist_de.put(new Integer("792"), "T\u00fcrkei");
		countryCodelist_de.put(new Integer("804"), "Ukraine");
		countryCodelist_de.put(new Integer("826"), "Vereinigtes K\u00f6nigreich");
	}

	/** MAP: syslist with english names of countries <ISO country code, english name of country> */
	static public HashMap<Integer, String> countryCodelist_en = new LinkedHashMap<Integer, String>();
	static {
		countryCodelist_en.put(new Integer("008"), "Albania");
		countryCodelist_en.put(new Integer("020"), "Andorra");
		countryCodelist_en.put(new Integer("040"), "Austria");
		countryCodelist_en.put(new Integer("112"), "Belarus");
		countryCodelist_en.put(new Integer("056"), "Belgium");
		countryCodelist_en.put(new Integer("070"), "Bosnia and Herzegovina");
		countryCodelist_en.put(new Integer("100"), "Bulgaria");
		countryCodelist_en.put(new Integer("191"), "Croatia");
		countryCodelist_en.put(new Integer("196"), "Cyprus");
		countryCodelist_en.put(new Integer("203"), "Czech Republic");
		countryCodelist_en.put(new Integer("208"), "Denmark");
		countryCodelist_en.put(new Integer("233"), "Estonia");
		countryCodelist_en.put(new Integer("246"), "Finland");
		countryCodelist_en.put(new Integer("250"), "France");
		countryCodelist_en.put(new Integer("276"), "Germany");
		countryCodelist_en.put(new Integer("292"), "Gibraltar");
		countryCodelist_en.put(new Integer("300"), "Greece");
		countryCodelist_en.put(new Integer("348"), "Hungary");
		countryCodelist_en.put(new Integer("352"), "Iceland");
		countryCodelist_en.put(new Integer("372"), "Ireland");
		countryCodelist_en.put(new Integer("380"), "Italy");
		countryCodelist_en.put(new Integer("428"), "Latvia");
		countryCodelist_en.put(new Integer("438"), "Liechtenstein");
		countryCodelist_en.put(new Integer("440"), "Lithuania");
		countryCodelist_en.put(new Integer("442"), "Luxembourg");
		countryCodelist_en.put(new Integer("807"), "Macedonia");
		countryCodelist_en.put(new Integer("450"), "Madagascar");
		countryCodelist_en.put(new Integer("470"), "Malta");
		countryCodelist_en.put(new Integer("498"), "Moldova, Republic of");
		countryCodelist_en.put(new Integer("492"), "Monaco");
		countryCodelist_en.put(new Integer("499"), "Montenegro");
		countryCodelist_en.put(new Integer("528"), "Netherlands");
		countryCodelist_en.put(new Integer("578"), "Norway");
		countryCodelist_en.put(new Integer("616"), "Poland");
		countryCodelist_en.put(new Integer("620"), "Portugal");
		countryCodelist_en.put(new Integer("642"), "Romania");
		countryCodelist_en.put(new Integer("643"), "Russian Federation");
		countryCodelist_en.put(new Integer("688"), "Serbia");
		countryCodelist_en.put(new Integer("703"), "Slovakia");
		countryCodelist_en.put(new Integer("705"), "Slovenia");
		countryCodelist_en.put(new Integer("724"), "Spain");
		countryCodelist_en.put(new Integer("752"), "Sweden");
		countryCodelist_en.put(new Integer("756"), "Switzerland");
		countryCodelist_en.put(new Integer("792"), "Turkey");
		countryCodelist_en.put(new Integer("804"), "Ukraine");
		countryCodelist_en.put(new Integer("826"), "United Kingdom");
	}

	/** MAP: 2 letter ISO country shortcut ("DE", "GB") to ISO country code <2 letter ISO country shortcut, ISO country code>*/
	static private HashMap<String, Integer> countryShortcut2ToCode = new HashMap<String, Integer>(); 
	static {
		countryShortcut2ToCode.put("AL", new Integer("008"));
		countryShortcut2ToCode.put("AD", new Integer("020"));
		countryShortcut2ToCode.put("AT", new Integer("040"));
		countryShortcut2ToCode.put("BY", new Integer("112"));
		countryShortcut2ToCode.put("BE", new Integer("056"));
		countryShortcut2ToCode.put("BA", new Integer("070"));
		countryShortcut2ToCode.put("BG", new Integer("100"));
		countryShortcut2ToCode.put("HR", new Integer("191"));
		countryShortcut2ToCode.put("CY", new Integer("196"));
		countryShortcut2ToCode.put("CZ", new Integer("203"));
		countryShortcut2ToCode.put("DK", new Integer("208"));
		countryShortcut2ToCode.put("EE", new Integer("233"));
		countryShortcut2ToCode.put("FI", new Integer("246"));
		countryShortcut2ToCode.put("FR", new Integer("250"));
		countryShortcut2ToCode.put("DE", new Integer("276"));
		countryShortcut2ToCode.put("GI", new Integer("292"));
		countryShortcut2ToCode.put("GR", new Integer("300"));
		countryShortcut2ToCode.put("HU", new Integer("348"));
		countryShortcut2ToCode.put("IS", new Integer("352"));
		countryShortcut2ToCode.put("IE", new Integer("372"));
		countryShortcut2ToCode.put("IT", new Integer("380"));
		countryShortcut2ToCode.put("LV", new Integer("428"));
		countryShortcut2ToCode.put("LI", new Integer("438"));
		countryShortcut2ToCode.put("LT", new Integer("440"));
		countryShortcut2ToCode.put("LU", new Integer("442"));
		countryShortcut2ToCode.put("MK", new Integer("807"));
		countryShortcut2ToCode.put("MG", new Integer("450"));
		countryShortcut2ToCode.put("MT", new Integer("470"));
		countryShortcut2ToCode.put("MD", new Integer("498"));
		countryShortcut2ToCode.put("MC", new Integer("492"));
		countryShortcut2ToCode.put("ME", new Integer("499"));
		countryShortcut2ToCode.put("NL", new Integer("528"));
		countryShortcut2ToCode.put("NO", new Integer("578"));
		countryShortcut2ToCode.put("PL", new Integer("616"));
		countryShortcut2ToCode.put("PT", new Integer("620"));
		countryShortcut2ToCode.put("RO", new Integer("642"));
		countryShortcut2ToCode.put("RU", new Integer("643"));
		countryShortcut2ToCode.put("RS", new Integer("688"));
		countryShortcut2ToCode.put("SK", new Integer("703"));
		countryShortcut2ToCode.put("SI", new Integer("705"));
		countryShortcut2ToCode.put("ES", new Integer("724"));
		countryShortcut2ToCode.put("SE", new Integer("752"));
		countryShortcut2ToCode.put("CH", new Integer("756"));
		countryShortcut2ToCode.put("TR", new Integer("792"));
		countryShortcut2ToCode.put("UA", new Integer("804"));
		countryShortcut2ToCode.put("GB", new Integer("826"));
		countryShortcut2ToCode.put("UK", new Integer("826"));
	}

	/** MAP: 3 letter ISO country shortcut ("DEU", "GBR") to ISO country code <3 letter ISO country shortcut, ISO country code>*/
	static private HashMap<String, Integer> countryShortcut3ToCode = new HashMap<String, Integer>(); 
	static {
		countryShortcut3ToCode.put("ALB", new Integer("008"));
		countryShortcut3ToCode.put("AND", new Integer("020"));
		countryShortcut3ToCode.put("AUT", new Integer("040"));
		countryShortcut3ToCode.put("BLR", new Integer("112"));
		countryShortcut3ToCode.put("BEL", new Integer("056"));
		countryShortcut3ToCode.put("BIH", new Integer("070"));
		countryShortcut3ToCode.put("BGR", new Integer("100"));
		countryShortcut3ToCode.put("HRV", new Integer("191"));
		countryShortcut3ToCode.put("CYP", new Integer("196"));
		countryShortcut3ToCode.put("CZE", new Integer("203"));
		countryShortcut3ToCode.put("DNK", new Integer("208"));
		countryShortcut3ToCode.put("EST", new Integer("233"));
		countryShortcut3ToCode.put("FIN", new Integer("246"));
		countryShortcut3ToCode.put("FRA", new Integer("250"));
		countryShortcut3ToCode.put("DEU", new Integer("276"));
		countryShortcut3ToCode.put("GIB", new Integer("292"));
		countryShortcut3ToCode.put("GRC", new Integer("300"));
		countryShortcut3ToCode.put("HUN", new Integer("348"));
		countryShortcut3ToCode.put("ISL", new Integer("352"));
		countryShortcut3ToCode.put("IRL", new Integer("372"));
		countryShortcut3ToCode.put("ITA", new Integer("380"));
		countryShortcut3ToCode.put("LVA", new Integer("428"));
		countryShortcut3ToCode.put("LIE", new Integer("438"));
		countryShortcut3ToCode.put("LTU", new Integer("440"));
		countryShortcut3ToCode.put("LUX", new Integer("442"));
		countryShortcut3ToCode.put("MKD", new Integer("807"));
		countryShortcut3ToCode.put("MDG", new Integer("450"));
		countryShortcut3ToCode.put("MLT", new Integer("470"));
		countryShortcut3ToCode.put("MDA", new Integer("498"));
		countryShortcut3ToCode.put("MCO", new Integer("492"));
		countryShortcut3ToCode.put("MNE", new Integer("499"));
		countryShortcut3ToCode.put("NLD", new Integer("528"));
		countryShortcut3ToCode.put("NOR", new Integer("578"));
		countryShortcut3ToCode.put("POL", new Integer("616"));
		countryShortcut3ToCode.put("PRT", new Integer("620"));
		countryShortcut3ToCode.put("ROU", new Integer("642"));
		countryShortcut3ToCode.put("RUS", new Integer("643"));
		countryShortcut3ToCode.put("SRB", new Integer("688"));
		countryShortcut3ToCode.put("SVK", new Integer("703"));
		countryShortcut3ToCode.put("SVN", new Integer("705"));
		countryShortcut3ToCode.put("ESP", new Integer("724"));
		countryShortcut3ToCode.put("SWE", new Integer("752"));
		countryShortcut3ToCode.put("CHE", new Integer("756"));
		countryShortcut3ToCode.put("TUR", new Integer("792"));
		countryShortcut3ToCode.put("UKR", new Integer("804"));
		countryShortcut3ToCode.put("GBR", new Integer("826"));
	}

	/** Determine country name from ISO country code.
	 * @param countryCode ISO code of country
	 * @param languageShortcut in which language should the name be returned, e.g. "de" for german name
	 * @return country name of ISO country code or null if not found !
	 */
	static public String getNameFromCode(Integer countryCode, String languageShortcut) {
		String langName = null;

		if ("de".equals(languageShortcut)) {
			langName = countryCodelist_de.get(countryCode);
		} else if ("en".equals(languageShortcut)) {
			langName = countryCodelist_en.get(countryCode);			
		}

		return langName;
	}

	/** Determine 2 letter country shortcut (e.g. "DE", "GB" ...") from ISO country code.
	 * @param countryCode ISO code of country
	 * @return country shortcut or null if not found
	 */
	static public String getShortcut2FromCode(Integer countryCode) {
		String shortcut = null;

		for (Map.Entry<String, Integer> entry : countryShortcut2ToCode.entrySet()) {
			if (entry.getValue().equals(countryCode)) {
				shortcut = entry.getKey();
				// specials
				if (shortcut.equals("UK")) {
					shortcut = "GB";
				}
				break;
			}
		}
		return shortcut;
	}

	/** Determine 3 letter country shortcut (e.g. "DEU", "GBR" ...") from ISO country code.
	 * @param countryCode ISO code of country
	 * @return country shortcut or null if not found
	 */
	static public String getShortcut3FromCode(Integer countryCode) {
		String shortcut = null;

		for (Map.Entry<String, Integer> entry : countryShortcut3ToCode.entrySet()) {
			if (entry.getValue().equals(countryCode)) {
				shortcut = entry.getKey();
				break;
			}
		}
		return shortcut;
	}

	/** Determine ISO country code from 2 letter ISO country shortcut (e.g. "DE", "GB" ...").
	 * @param countryShortcut2 2 letter ISO country shortcut (e.g. "DE", "GB" ...")
	 * @return ISO country code or null if not found
	 */
	static public Integer getCodeFromShortcut2(String countryShortcut2) {
		return countryShortcut2ToCode.get(countryShortcut2);
	}


	/** Determine ISO country code from 3 letter ISO country shortcut (e.g. "DEU", "GBR" ...").
	 * @param countryShortcut3 3 letter ISO country shortcut (e.g. "DEU", "GBR" ...")
	 * @return ISO country code or null if not found
	 */
	static public Integer getCodeFromShortcut3(String countryShortcut3) {
		return countryShortcut3ToCode.get(countryShortcut3);
	}
}
