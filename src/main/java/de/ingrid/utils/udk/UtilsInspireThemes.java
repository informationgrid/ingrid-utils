/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * This class contains the MOST RECENT Inspire Themes Codelist and helper functions, e.g. mapping of searchterms to Inspire Themes.
 */
public class UtilsInspireThemes {

	// INSPIRE THEMES
	// --------------
	static public int noInspireThemeId = 99999;

	// german syslist <ThemeId, german ThemeName>
	static public LinkedHashMap<Integer, String> inspireThemes_de = new LinkedHashMap<Integer, String>();
	static {
		inspireThemes_de.put(101, "Koordinatenreferenzsysteme");
		inspireThemes_de.put(102, "Geografische Gittersysteme");
		inspireThemes_de.put(103, "Geografische Bezeichnungen");
		inspireThemes_de.put(104, "Verwaltungseinheiten");
		inspireThemes_de.put(105, "Adressen");
		inspireThemes_de.put(106, "Flurst\u00fccke/Grundst\u00fccke (Katasterparzellen)");
		inspireThemes_de.put(107, "Verkehrsnetze");
		inspireThemes_de.put(108, "Gew\u00e4ssernetz");
		inspireThemes_de.put(109, "Schutzgebiete");
		inspireThemes_de.put(201, "H\u00f6he");
		inspireThemes_de.put(202, "Bodenbedeckung");
		inspireThemes_de.put(203, "Orthofotografie");
		inspireThemes_de.put(204, "Geologie");
		inspireThemes_de.put(301, "Statistische Einheiten");
		inspireThemes_de.put(302, "Geb\u00e4ude");
		inspireThemes_de.put(303, "Boden");
		inspireThemes_de.put(304, "Bodennutzung");
		inspireThemes_de.put(305, "Gesundheit und Sicherheit");
		inspireThemes_de.put(306, "Versorgungswirtschaft und staatliche Dienste");
		inspireThemes_de.put(307, "Umwelt\u00fcberwachung");
		inspireThemes_de.put(308, "Produktions- und Industrieanlagen");
		inspireThemes_de.put(309, "Landwirtschaftliche Anlagen und Aquakulturanlagen");
		inspireThemes_de.put(310, "Verteilung der Bev\u00f6lkerung - Demografie");
		inspireThemes_de.put(311, "Bewirtschaftungsgebiete/Schutzgebiete/geregelte Gebiete und Berichterstattungseinheiten");
		inspireThemes_de.put(312, "Gebiete mit naturbedingten Risiken");
		inspireThemes_de.put(313, "Atmosph\u00e4rische Bedingungen");
		inspireThemes_de.put(314, "Meteorologisch-geografische Kennwerte");
		inspireThemes_de.put(315, "Ozeanografisch-geografische Kennwerte");
		inspireThemes_de.put(316, "Meeresregionen");
		inspireThemes_de.put(317, "Biogeografische Regionen");
		inspireThemes_de.put(318, "Lebensr\u00e4ume und Biotope");
		inspireThemes_de.put(319, "Verteilung der Arten");
		inspireThemes_de.put(320, "Energiequellen");
		inspireThemes_de.put(321, "Mineralische Bodensch\u00e4tze");
		inspireThemes_de.put(noInspireThemeId, "Kein INSPIRE-Thema");
	}
	// german "searchterm" to theme ids <term, [ThemeIds]>
	// term is LOWERCASE because of comparison
	static private HashMap<String, Integer[]> termToThemeIds_de = new HashMap<String, Integer[]>(); 
	static {
		termToThemeIds_de.put("koordinatenreferenzsystem", new Integer[]{101});
		termToThemeIds_de.put("geografische gittersystem", new Integer[]{102});
		termToThemeIds_de.put("geografische bezeichnung", new Integer[]{103});
		termToThemeIds_de.put("verwaltungseinheit", new Integer[]{104});
		termToThemeIds_de.put("adresse", new Integer[]{105});
		termToThemeIds_de.put("flurst\u00fcck", new Integer[]{106});
		termToThemeIds_de.put("grundst\u00fcck", new Integer[]{106});
		termToThemeIds_de.put("katasterparzelle", new Integer[]{106});
		termToThemeIds_de.put("verkehrsnetz", new Integer[]{107});
		termToThemeIds_de.put("gew\u00e4ssernetz", new Integer[]{108});
		termToThemeIds_de.put("schutzgebiet", new Integer[]{109, 311});
		termToThemeIds_de.put("h\u00f6he", new Integer[]{201});
		termToThemeIds_de.put("bodenbedeckung", new Integer[]{202});
		termToThemeIds_de.put("orthofotografie", new Integer[]{203});
		termToThemeIds_de.put("geologie", new Integer[]{204});
		termToThemeIds_de.put("statistische einheit", new Integer[]{301});
		termToThemeIds_de.put("geb\u00e4ude", new Integer[]{302});
		termToThemeIds_de.put("boden", new Integer[]{303});
		termToThemeIds_de.put("bodennutzung", new Integer[]{304});
		termToThemeIds_de.put("gesundheit", new Integer[]{305});
		termToThemeIds_de.put("sicherheit", new Integer[]{305});
		termToThemeIds_de.put("versorgungswirtschaft", new Integer[]{306});
		termToThemeIds_de.put("staatlicher dienst", new Integer[]{306});
		termToThemeIds_de.put("umwelt\u00fcberwachung", new Integer[]{307});
		termToThemeIds_de.put("produktionsanlage", new Integer[]{308});
		termToThemeIds_de.put("industrieanlage", new Integer[]{308});
		termToThemeIds_de.put("landwirtschaftliche anlage", new Integer[]{309});
		termToThemeIds_de.put("aquakulturanlage", new Integer[]{309});
		termToThemeIds_de.put("verteilung der bev\u00f6lkerung", new Integer[]{310});
		termToThemeIds_de.put("demografie", new Integer[]{310});
		termToThemeIds_de.put("bewirtschaftungsgebiet", new Integer[]{311});
		termToThemeIds_de.put("geregeltes gebiet", new Integer[]{311});
		termToThemeIds_de.put("berichterstattungseinheit", new Integer[]{311});
		termToThemeIds_de.put("gebiet mit naturbedingten risiken", new Integer[]{312});
		termToThemeIds_de.put("atmosph\u00e4rische bedingung", new Integer[]{313});
		termToThemeIds_de.put("meteorologisch-geografische kennwert", new Integer[]{314});
		termToThemeIds_de.put("ozeanografisch-geografische kennwert", new Integer[]{315});
		termToThemeIds_de.put("meeresregion", new Integer[]{316});
		termToThemeIds_de.put("biogeografische region", new Integer[]{317});
		termToThemeIds_de.put("lebensraum", new Integer[]{318});
		termToThemeIds_de.put("biotop", new Integer[]{318});
		termToThemeIds_de.put("verteilung der arten", new Integer[]{319});
		termToThemeIds_de.put("energiequelle", new Integer[]{320});
		termToThemeIds_de.put("mineralischer bodenschatz", new Integer[]{321});
	}

	// english syslist <ThemeId, english ThemeName>
	static public LinkedHashMap<Integer, String> inspireThemes_en = new LinkedHashMap<Integer, String>(); 
	static {
		inspireThemes_en.put(101, "Coordinate reference systems");
		inspireThemes_en.put(102, "Geographical grid systems");
		inspireThemes_en.put(103, "Geographical names");
		inspireThemes_en.put(104, "Administrative units");
		inspireThemes_en.put(105, "Addresses");
		inspireThemes_en.put(106, "Cadastral parcels");
		inspireThemes_en.put(107, "Transport networks");
		inspireThemes_en.put(108, "Hydrography");
		inspireThemes_en.put(109, "Protected sites");
		inspireThemes_en.put(201, "Elevation");
		inspireThemes_en.put(202, "Land cover");
		inspireThemes_en.put(203, "Orthoimagery");
		inspireThemes_en.put(204, "Geology");
		inspireThemes_en.put(301, "Statistical units");
		inspireThemes_en.put(302, "Buildings");
		inspireThemes_en.put(303, "Soil");
		inspireThemes_en.put(304, "Land use");
		inspireThemes_en.put(305, "Human health and safety");
		inspireThemes_en.put(306, "Utility and governmental services");
		inspireThemes_en.put(307, "Environmental monitoring facilities");
		inspireThemes_en.put(308, "Production and industrial facilities");
		inspireThemes_en.put(309, "Agricultural and aquaculture facilities");
		inspireThemes_en.put(310, "Population distribution - demography");
		inspireThemes_en.put(311, "Area management/restriction/regulation zones and reporting units");
		inspireThemes_en.put(312, "Natural risk zones");
		inspireThemes_en.put(313, "Atmospheric conditions");
		inspireThemes_en.put(314, "Meteorological geographical features");
		inspireThemes_en.put(315, "Oceanographic geographical features");
		inspireThemes_en.put(316, "Sea regions");
		inspireThemes_en.put(317, "Bio-geographical regions");
		inspireThemes_en.put(318, "Habitats and biotopes");
		inspireThemes_en.put(319, "Species distribution");
		inspireThemes_en.put(320, "Energy resources");
		inspireThemes_en.put(321, "Mineral resources");
		inspireThemes_en.put(noInspireThemeId, "No INSPIRE Theme");
	}

	/** Determine Ids of INSPIRE Themes fitting to passed term.
	 * @param term the searchterm
	 * @param language the language of the term, pass null to use default language (de)
	 * @return ids of INSPIRE themes or empty set if no theme found.
	 * 		Then use const InspireThemesHelper.noInspireThemeId
	 */
	static public Set<Integer> getThemeIdsOfTerm(String term, String language) {
		Set<Integer> ids = new HashSet<Integer>();
		if (term == null || term.trim().length() == 0) {
			return ids;
		}
		if (language == null) {
			language = "de";
		}

		// get terms of themes in according language
		Set<String> themeTerms = null;
		if (language.equals("de")) {
			themeTerms = UtilsInspireThemes.termToThemeIds_de.keySet();
		}
		if (themeTerms == null) {
			return ids;
		}

		// analyze passed term. contains theme term ?
		for (String themeTerm : themeTerms) {
			if (term.toLowerCase().contains(themeTerm)) {
				// yes ! fetch according theme Ids
				Integer[] themeIds = UtilsInspireThemes.termToThemeIds_de.get(themeTerm);
				// add new themes (Set)
				for (Integer themeId : themeIds) {
					ids.add(themeId);
				}
			}
		}
		
		return ids;
	}
}
