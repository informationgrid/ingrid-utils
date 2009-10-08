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
		countryCodelist_de.put(new Integer(4), "Afghanistan");
		countryCodelist_de.put(new Integer(818), "\u00c4gypten");
		countryCodelist_de.put(new Integer(248), "\u00c5land");
		countryCodelist_de.put(new Integer(8), "Albanien");
		countryCodelist_de.put(new Integer(12), "Algerien");
		countryCodelist_de.put(new Integer(850), "Amerikanische Jungferninseln");
		countryCodelist_de.put(new Integer(16), "Amerikanisch-Samoa");
		countryCodelist_de.put(new Integer(20), "Andorra");
		countryCodelist_de.put(new Integer(24), "Angola");
		countryCodelist_de.put(new Integer(660), "Anguilla");
		countryCodelist_de.put(new Integer(10), "Antarktis");
		countryCodelist_de.put(new Integer(28), "Antigua und Barbuda");
		countryCodelist_de.put(new Integer(226), "\u00c4quatorialguinea");
		countryCodelist_de.put(new Integer(32), "Argentinien");
		countryCodelist_de.put(new Integer(51), "Armenien");
		countryCodelist_de.put(new Integer(533), "Aruba");
		countryCodelist_de.put(new Integer(31), "Aserbaidschan");
		countryCodelist_de.put(new Integer(231), "\u00c4thiopien");
		countryCodelist_de.put(new Integer(36), "Australien");
		countryCodelist_de.put(new Integer(44), "Bahamas");
		countryCodelist_de.put(new Integer(48), "Bahrain");
		countryCodelist_de.put(new Integer(50), "Bangladesch");
		countryCodelist_de.put(new Integer(52), "Barbados");
		countryCodelist_de.put(new Integer(112), "Belarus (Wei\u00dfrussland)");
		countryCodelist_de.put(new Integer(56), "Belgien");
		countryCodelist_de.put(new Integer(84), "Belize");
		countryCodelist_de.put(new Integer(204), "Benin");
		countryCodelist_de.put(new Integer(60), "Bermuda");
		countryCodelist_de.put(new Integer(64), "Bhutan");
		countryCodelist_de.put(new Integer(68), "Bolivien");
		countryCodelist_de.put(new Integer(70), "Bosnien und Herzegowina");
		countryCodelist_de.put(new Integer(72), "Botswana");
		countryCodelist_de.put(new Integer(74), "Bouvetinsel");
		countryCodelist_de.put(new Integer(76), "Brasilien");
		countryCodelist_de.put(new Integer(92), "Britische Jungferninseln");
		countryCodelist_de.put(new Integer(86), "Britisches Territorium im Indischen Ozean");
		countryCodelist_de.put(new Integer(96), "Brunei Darussalam");
		countryCodelist_de.put(new Integer(100), "Bulgarien");
		countryCodelist_de.put(new Integer(854), "Burkina Faso");
		countryCodelist_de.put(new Integer(108), "Burundi");
		countryCodelist_de.put(new Integer(152), "Chile");
		countryCodelist_de.put(new Integer(156), "China, Volksrepublik");
		countryCodelist_de.put(new Integer(184), "Cookinseln");
		countryCodelist_de.put(new Integer(188), "Costa Rica");
		countryCodelist_de.put(new Integer(384), "C\u00f4te d'Ivoire (Elfenbeink\u00fcste)");
		countryCodelist_de.put(new Integer(208), "D\u00e4nemark");
		countryCodelist_de.put(new Integer(276), "Deutschland");
		countryCodelist_de.put(new Integer(212), "Dominica");
		countryCodelist_de.put(new Integer(214), "Dominikanische Republik");
		countryCodelist_de.put(new Integer(262), "Dschibuti");
		countryCodelist_de.put(new Integer(218), "Ecuador");
		countryCodelist_de.put(new Integer(222), "El Salvador");
		countryCodelist_de.put(new Integer(232), "Eritrea");
		countryCodelist_de.put(new Integer(233), "Estland");
		countryCodelist_de.put(new Integer(238), "Falklandinseln");
		countryCodelist_de.put(new Integer(234), "F\u00e4r\u00f6er");
		countryCodelist_de.put(new Integer(242), "Fidschi");
		countryCodelist_de.put(new Integer(246), "Finnland");
		countryCodelist_de.put(new Integer(250), "Frankreich");
		countryCodelist_de.put(new Integer(260), "Franz\u00f6sische S\u00fcd- und Antarktisgebiete");
		countryCodelist_de.put(new Integer(254), "Franz\u00f6sisch-Guayana");
		countryCodelist_de.put(new Integer(258), "Franz\u00f6sisch-Polynesien");
		countryCodelist_de.put(new Integer(266), "Gabun");
		countryCodelist_de.put(new Integer(270), "Gambia");
		countryCodelist_de.put(new Integer(268), "Georgien");
		countryCodelist_de.put(new Integer(288), "Ghana");
		countryCodelist_de.put(new Integer(292), "Gibraltar");
		countryCodelist_de.put(new Integer(308), "Grenada");
		countryCodelist_de.put(new Integer(300), "Griechenland");
		countryCodelist_de.put(new Integer(304), "Gr\u00f6nland");
		countryCodelist_de.put(new Integer(312), "Guadeloupe");
		countryCodelist_de.put(new Integer(316), "Guam");
		countryCodelist_de.put(new Integer(320), "Guatemala");
		countryCodelist_de.put(new Integer(831), "Guernsey (Kanalinsel)");
		countryCodelist_de.put(new Integer(324), "Guinea");
		countryCodelist_de.put(new Integer(624), "Guinea-Bissau");
		countryCodelist_de.put(new Integer(328), "Guyana");
		countryCodelist_de.put(new Integer(332), "Haiti");
		countryCodelist_de.put(new Integer(334), "Heard- und McDonald-Inseln");
		countryCodelist_de.put(new Integer(340), "Honduras");
		countryCodelist_de.put(new Integer(344), "Hongkong");
		countryCodelist_de.put(new Integer(356), "Indien");
		countryCodelist_de.put(new Integer(360), "Indonesien");
		countryCodelist_de.put(new Integer(833), "Insel Man");
		countryCodelist_de.put(new Integer(368), "Irak");
		countryCodelist_de.put(new Integer(364), "Iran, Islamische Republik");
		countryCodelist_de.put(new Integer(372), "Irland");
		countryCodelist_de.put(new Integer(352), "Island");
		countryCodelist_de.put(new Integer(376), "Israel");
		countryCodelist_de.put(new Integer(380), "Italien");
		countryCodelist_de.put(new Integer(388), "Jamaika");
		countryCodelist_de.put(new Integer(392), "Japan");
		countryCodelist_de.put(new Integer(887), "Jemen");
		countryCodelist_de.put(new Integer(832), "Jersey (Kanalinsel)");
		countryCodelist_de.put(new Integer(400), "Jordanien");
		countryCodelist_de.put(new Integer(136), "Kaimaninseln");
		countryCodelist_de.put(new Integer(116), "Kambodscha");
		countryCodelist_de.put(new Integer(120), "Kamerun");
		countryCodelist_de.put(new Integer(124), "Kanada");
		countryCodelist_de.put(new Integer(132), "Kap Verde");
		countryCodelist_de.put(new Integer(398), "Kasachstan");
		countryCodelist_de.put(new Integer(634), "Katar");
		countryCodelist_de.put(new Integer(404), "Kenia");
		countryCodelist_de.put(new Integer(417), "Kirgisistan");
		countryCodelist_de.put(new Integer(296), "Kiribati");
		countryCodelist_de.put(new Integer(166), "Kokosinseln");
		countryCodelist_de.put(new Integer(170), "Kolumbien");
		countryCodelist_de.put(new Integer(174), "Komoren");
		countryCodelist_de.put(new Integer(180), "Kongo, Demokratische Republik (ehem. Zaire)");
		countryCodelist_de.put(new Integer(408), "Korea, Demokratische Volksrepublik (Nordkorea)");
		countryCodelist_de.put(new Integer(410), "Korea, Republik (S\u00fcdkorea)");
		countryCodelist_de.put(new Integer(191), "Kroatien");
		countryCodelist_de.put(new Integer(192), "Kuba");
		countryCodelist_de.put(new Integer(414), "Kuwait");
		countryCodelist_de.put(new Integer(418), "Laos, Demokratische Volksrepublik");
		countryCodelist_de.put(new Integer(426), "Lesotho");
		countryCodelist_de.put(new Integer(428), "Lettland");
		countryCodelist_de.put(new Integer(422), "Libanon");
		countryCodelist_de.put(new Integer(430), "Liberia");
		countryCodelist_de.put(new Integer(434), "Libysch-Arabische Dschamahirija (Libyen)");
		countryCodelist_de.put(new Integer(438), "Liechtenstein");
		countryCodelist_de.put(new Integer(440), "Litauen");
		countryCodelist_de.put(new Integer(442), "Luxemburg");
		countryCodelist_de.put(new Integer(446), "Macao");
		countryCodelist_de.put(new Integer(450), "Madagaskar");
		countryCodelist_de.put(new Integer(454), "Malawi");
		countryCodelist_de.put(new Integer(458), "Malaysia");
		countryCodelist_de.put(new Integer(462), "Malediven");
		countryCodelist_de.put(new Integer(466), "Mali");
		countryCodelist_de.put(new Integer(470), "Malta");
		countryCodelist_de.put(new Integer(504), "Marokko");
		countryCodelist_de.put(new Integer(584), "Marshallinseln");
		countryCodelist_de.put(new Integer(474), "Martinique");
		countryCodelist_de.put(new Integer(478), "Mauretanien");
		countryCodelist_de.put(new Integer(480), "Mauritius");
		countryCodelist_de.put(new Integer(175), "Mayotte");
		countryCodelist_de.put(new Integer(807), "Mazedonien, ehem. jugoslawische Republik");
		countryCodelist_de.put(new Integer(484), "Mexiko");
		countryCodelist_de.put(new Integer(583), "Mikronesien");
		countryCodelist_de.put(new Integer(498), "Moldawien (Republik Moldau)");
		countryCodelist_de.put(new Integer(492), "Monaco");
		countryCodelist_de.put(new Integer(496), "Mongolei");
		countryCodelist_de.put(new Integer(499), "Montenegro");
		countryCodelist_de.put(new Integer(500), "Montserrat");
		countryCodelist_de.put(new Integer(508), "Mosambik");
		countryCodelist_de.put(new Integer(104), "Myanmar (Burma)");
		countryCodelist_de.put(new Integer(516), "Namibia");
		countryCodelist_de.put(new Integer(520), "Nauru");
		countryCodelist_de.put(new Integer(524), "Nepal");
		countryCodelist_de.put(new Integer(540), "Neukaledonien");
		countryCodelist_de.put(new Integer(554), "Neuseeland");
		countryCodelist_de.put(new Integer(558), "Nicaragua");
		countryCodelist_de.put(new Integer(528), "Niederlande");
		countryCodelist_de.put(new Integer(530), "Niederl\u00e4ndische Antillen");
		countryCodelist_de.put(new Integer(562), "Niger");
		countryCodelist_de.put(new Integer(566), "Nigeria");
		countryCodelist_de.put(new Integer(570), "Niue");
		countryCodelist_de.put(new Integer(580), "N\u00f6rdliche Marianen");
		countryCodelist_de.put(new Integer(574), "Norfolkinsel");
		countryCodelist_de.put(new Integer(578), "Norwegen");
		countryCodelist_de.put(new Integer(512), "Oman");
		countryCodelist_de.put(new Integer(40),	 "\u00d6sterreich");
		countryCodelist_de.put(new Integer(626), "Osttimor (Timor-Leste)");
		countryCodelist_de.put(new Integer(586), "Pakistan");
		countryCodelist_de.put(new Integer(275), "Pal\u00e4stinensische Autonomiegebiete");
		countryCodelist_de.put(new Integer(585), "Palau");
		countryCodelist_de.put(new Integer(591), "Panama");
		countryCodelist_de.put(new Integer(598), "Papua-Neuguinea");
		countryCodelist_de.put(new Integer(600), "Paraguay");
		countryCodelist_de.put(new Integer(604), "Peru");
		countryCodelist_de.put(new Integer(608), "Philippinen");
		countryCodelist_de.put(new Integer(612), "Pitcairninseln");
		countryCodelist_de.put(new Integer(616), "Polen");
		countryCodelist_de.put(new Integer(620), "Portugal");
		countryCodelist_de.put(new Integer(630), "Puerto Rico");
		countryCodelist_de.put(new Integer(158), "Republik China (Taiwan)");
		countryCodelist_de.put(new Integer(178), "Republik Kongo");
		countryCodelist_de.put(new Integer(638), "R\u00e9union");
		countryCodelist_de.put(new Integer(646), "Ruanda");
		countryCodelist_de.put(new Integer(642), "Rum\u00e4nien");
		countryCodelist_de.put(new Integer(643), "Russische F\u00f6deration");
		countryCodelist_de.put(new Integer(652), "Saint-Barth\u00e9lemy");
		countryCodelist_de.put(new Integer(663), "Saint-Martin (franz. Teil)");
		countryCodelist_de.put(new Integer(90),  "Salomonen");
		countryCodelist_de.put(new Integer(894), "Sambia");
		countryCodelist_de.put(new Integer(882), "Samoa");
		countryCodelist_de.put(new Integer(674), "San Marino");
		countryCodelist_de.put(new Integer(678), "S\u00e3o Tom\u00e9 und Pr\u00edncipe");
		countryCodelist_de.put(new Integer(682), "Saudi-Arabien");
		countryCodelist_de.put(new Integer(752), "Schweden");
		countryCodelist_de.put(new Integer(756), "Schweiz (Confoederatio Helvetica)");
		countryCodelist_de.put(new Integer(686), "Senegal");
		countryCodelist_de.put(new Integer(688), "Serbien");
		countryCodelist_de.put(new Integer(690), "Seychellen");
		countryCodelist_de.put(new Integer(694), "Sierra Leone");
		countryCodelist_de.put(new Integer(716), "Simbabwe");
		countryCodelist_de.put(new Integer(702), "Singapur");
		countryCodelist_de.put(new Integer(703), "Slowakei");
		countryCodelist_de.put(new Integer(705), "Slowenien");
		countryCodelist_de.put(new Integer(706), "Somalia");
		countryCodelist_de.put(new Integer(724), "Spanien");
		countryCodelist_de.put(new Integer(144), "Sri Lanka");
		countryCodelist_de.put(new Integer(654), "St. Helena");
		countryCodelist_de.put(new Integer(659), "St. Kitts und Nevis");
		countryCodelist_de.put(new Integer(662), "St. Lucia");
		countryCodelist_de.put(new Integer(666), "St. Pierre und MiquelonSaint-Pierre und Miquelon");
		countryCodelist_de.put(new Integer(670), "St. Vincent und die Grenadinen");
		countryCodelist_de.put(new Integer(710), "S\u00fcdafrika");
		countryCodelist_de.put(new Integer(736), "Sudan");
		countryCodelist_de.put(new Integer(239), "S\u00fcdgeorgien und die S\u00fcdlichen Sandwichinseln");
		countryCodelist_de.put(new Integer(740), "Suriname");
		countryCodelist_de.put(new Integer(744), "Svalbard und Jan Mayen");
		countryCodelist_de.put(new Integer(748), "Swasiland");
		countryCodelist_de.put(new Integer(760), "Syrien, Arabische Republik");
		countryCodelist_de.put(new Integer(762), "Tadschikistan");
		countryCodelist_de.put(new Integer(834), "Tansania, Vereinigte Republik");
		countryCodelist_de.put(new Integer(764), "Thailand");
		countryCodelist_de.put(new Integer(768), "Togo");
		countryCodelist_de.put(new Integer(772), "Tokelau");
		countryCodelist_de.put(new Integer(776), "Tonga");
		countryCodelist_de.put(new Integer(780), "Trinidad und Tobago");
		countryCodelist_de.put(new Integer(148), "Tschad");
		countryCodelist_de.put(new Integer(203), "Tschechische Republik");
		countryCodelist_de.put(new Integer(788), "Tunesien");
		countryCodelist_de.put(new Integer(792), "T\u00fcrkei");
		countryCodelist_de.put(new Integer(795), "Turkmenistan");
		countryCodelist_de.put(new Integer(796), "Turks- und Caicosinseln");
		countryCodelist_de.put(new Integer(798), "Tuvalu");
		countryCodelist_de.put(new Integer(800), "Uganda");
		countryCodelist_de.put(new Integer(804), "Ukraine");
		countryCodelist_de.put(new Integer(348), "Ungarn");
		countryCodelist_de.put(new Integer(581), "United States Minor Outlying Islands");
		countryCodelist_de.put(new Integer(858), "Uruguay");
		countryCodelist_de.put(new Integer(860), "Usbekistan");
		countryCodelist_de.put(new Integer(548), "Vanuatu");
		countryCodelist_de.put(new Integer(336), "Vatikanstadt");
		countryCodelist_de.put(new Integer(862), "Venezuela");
		countryCodelist_de.put(new Integer(784), "Vereinigte Arabische Emirate");
		countryCodelist_de.put(new Integer(840), "Vereinigte Staaten von Amerika");
		countryCodelist_de.put(new Integer(826), "Vereinigtes K\u00f6nigreich Gro\u00dfbritannien und Nordirland");
		countryCodelist_de.put(new Integer(704), "Vietnam");
		countryCodelist_de.put(new Integer(876), "Wallis und Futuna");
		countryCodelist_de.put(new Integer(162), "Weihnachtsinsel");
		countryCodelist_de.put(new Integer(732), "Westsahara");
		countryCodelist_de.put(new Integer(140), "Zentralafrikanische Republik");
		countryCodelist_de.put(new Integer(196), "Zypern");													
	}

	/** MAP: syslist with english names of countries <ISO country code, english name of country> */
	static public HashMap<Integer, String> countryCodelist_en = new LinkedHashMap<Integer, String>();
	static {
		countryCodelist_en.put(new Integer("4"), "Afghanistan");
		countryCodelist_en.put(new Integer("818"), "Egypt");
		countryCodelist_en.put(new Integer("248"), "\u00c5land Islands");
		countryCodelist_en.put(new Integer("8"), "Albania");
		countryCodelist_en.put(new Integer("12"), "Algeria");
		countryCodelist_en.put(new Integer("850"), "Virgin Islands, U.S.");
		countryCodelist_en.put(new Integer("16"), "American Samoa");
		countryCodelist_en.put(new Integer("20"), "Andorra");
		countryCodelist_en.put(new Integer("24"), "Angola");
		countryCodelist_en.put(new Integer("660"), "Anguilla");
		countryCodelist_en.put(new Integer("10"), "Antarctica");
		countryCodelist_en.put(new Integer("28"), "Antigua and Barbuda");
		countryCodelist_en.put(new Integer("226"), "Equatorial Guinea");
		countryCodelist_en.put(new Integer("32"), "Argentina");
		countryCodelist_en.put(new Integer("51"), "Armenia");
		countryCodelist_en.put(new Integer("533"), "Aruba");
		countryCodelist_en.put(new Integer("31"), "Azerbaijan");
		countryCodelist_en.put(new Integer("231"), "Ethiopia");
		countryCodelist_en.put(new Integer("36"), "Australia");
		countryCodelist_en.put(new Integer("44"), "Bahamas");
		countryCodelist_en.put(new Integer("48"), "Bahrain");
		countryCodelist_en.put(new Integer("50"), "Bangladesh");
		countryCodelist_en.put(new Integer("52"), "Barbados");
		countryCodelist_en.put(new Integer("112"), "Belarus");
		countryCodelist_en.put(new Integer("056"), "Belgium");
		countryCodelist_en.put(new Integer("070"), "Bosnia and Herzegovina");
		countryCodelist_en.put(new Integer("100"), "Bulgaria");
		countryCodelist_en.put(new Integer("854"), "Burkina Faso");
		countryCodelist_en.put(new Integer("108"), "Burundi");
		countryCodelist_en.put(new Integer("152"), "Chile");
		countryCodelist_en.put(new Integer("156"), "China");
		countryCodelist_en.put(new Integer("184"), "Cook Islands");
		countryCodelist_en.put(new Integer("188"), "Costa Rica");
		countryCodelist_en.put(new Integer("384"), "C\u00f4te d'Ivoire");
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
		countryCodelist_en.put(new Integer("630"), "Puerto Rico");
		countryCodelist_en.put(new Integer("158"), "Taiwan, Province of China");
		countryCodelist_en.put(new Integer("178"), "Congo");
		countryCodelist_en.put(new Integer("638"), "R\u00e9union");
		countryCodelist_en.put(new Integer("646"), "Rwanda");
		countryCodelist_en.put(new Integer("642"), "Romania");
		countryCodelist_en.put(new Integer("643"), "Russian Federation");
		countryCodelist_en.put(new Integer("652"), "Saint Barth\u00e9lemy");
		countryCodelist_en.put(new Integer("663"), "Saint Martin (French part)");
		countryCodelist_en.put(new Integer("90"), "Solomon Islands");
		countryCodelist_en.put(new Integer("894"), "Zambia");
		countryCodelist_en.put(new Integer("882"), "Samoa");
		countryCodelist_en.put(new Integer("674"), "San Marino");
		countryCodelist_en.put(new Integer("678"), "Sao Tome and Principe");
		countryCodelist_en.put(new Integer("682"), "Saudi Arabia");
		countryCodelist_en.put(new Integer("752"), "Sweden");
		countryCodelist_en.put(new Integer("756"), "Switzerland");
		countryCodelist_en.put(new Integer("686"), "Senegal");
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

	/** Determine country name from ISO ALPHA 2 country code.
	 * @param countryShortcut2 ISO code of country
	 * @param languageShortcut in which language should the name be returned, e.g. "de" for german name
	 * @return country name of ISO country code or null if not found !
	 */
	static public String getNameFromShortcut2(String countryShortcut2, String languageShortcut) {
		return UtilsCountryCodelist.getNameFromCode(UtilsCountryCodelist.getCodeFromShortcut2(countryShortcut2), languageShortcut);
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
