/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2020 wemove digital solutions GmbH
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

public class UtilsUDKCodeListsTest extends TestCase {

    public void testCodeList505ToUDK() {
        /* mapping of UDK addresstypes to CSW address types
        
        UDK | CSW (codelist 505)| Name
        0 | 7 | Auskunft
        1 | 3 | Datenhalter
        2 | 2 | Datenverantwortung
        3 | 1 | Anbieter
        4 | 4 | Benutzer
        5 | 5 | Vertrieb
        6 | 6 | Herkunft
        7 | 8 | Datenerfassung
        8 | 9 | Auswertung
        9 | 10 | Herausgeber
        999 | keine Entsprechung, mapping auf codeListValue | Sonstige Angaben
         */
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("7"), "0");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("3"), "1");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("2"), "2");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("1"), "3");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("4"), "4");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("5"), "5");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("6"), "6");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("8"), "7");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("9"), "8");
        assertEquals(UtilsUDKCodeLists.codeList505ToUDK("10"), "9");
    }

    public void testUdkToCodeList505() {
        /* mapping of UDK addresstypes to CSW address types
        
        UDK | CSW (codelist 505)| Name
        0 | 7 | Auskunft
        1 | 3 | Datenhalter
        2 | 2 | Datenverantwortung
        3 | 1 | Anbieter
        4 | 4 | Benutzer
        5 | 5 | Vertrieb
        6 | 6 | Herkunft
        7 | 8 | Datenerfassung
        8 | 9 | Auswertung
        9 | 10 | Herausgeber
        999 | keine Entsprechung, mapping auf codeListValue | Sonstige Angaben
         */
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("0"), "7");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("1"), "3");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("2"), "2");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("3"), "1");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("4"), "4");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("5"), "5");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("6"), "6");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("7"), "8");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("8"), "9");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("9"), "10");
        assertEquals(UtilsUDKCodeLists.udkToCodeList505("999"), "999");
    }

}
