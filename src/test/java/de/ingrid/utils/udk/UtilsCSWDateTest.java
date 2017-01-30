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
/**
 * 
 */
package de.ingrid.utils.udk;

import junit.framework.TestCase;

/**
 * @author joachim
 *
 */
public class UtilsCSWDateTest extends TestCase {

	public final void testIsCSWDate() {
		assertEquals(true, UtilsCSWDate.isCSWDate("20061012"));
		assertEquals(true, UtilsCSWDate.isCSWDate("2001-01-15T20:07:48.11"));
		assertEquals(true, UtilsCSWDate.isCSWDate("2001-01-15T20:07:48.11Z"));
	}
	
	
	public final void testGetDBDateStyle() {
		assertEquals("20061012", UtilsCSWDate.getQueryDateStyle("2006-10-12"));
		assertEquals("20061012", UtilsCSWDate.getQueryDateStyle("20061012T121247"));
	}
	
    public final void testMapDateFromIso8601ToIndex() {
        assertEquals("20170113170148830", UtilsCSWDate.mapDateFromIso8601ToIndex("2017-01-13T17:01:48.83+01:00"));
        assertEquals("20020115210748110", UtilsCSWDate.mapDateFromIso8601ToIndex("2002-01-15T20:07:48.11Z"));
        assertEquals("20010115200748110", UtilsCSWDate.mapDateFromIso8601ToIndex("2001-01-15T20:07:48.11"));
        assertEquals("20061012000000000", UtilsCSWDate.mapDateFromIso8601ToIndex("20061012"));
        assertEquals("20060101000000000", UtilsCSWDate.mapDateFromIso8601ToIndex("2006"));
        assertEquals("20030115200748000", UtilsCSWDate.mapDateFromIso8601ToIndex("20030115T200748"));
    }
	
    public final void testMapFromIgcToIso8601() {
        // NOTICE: Handle problem of summertime before summertime was introduced !
        // see https://dev.informationgrid.eu/redmine/issues/439

        // Stored Date from frontend is "wrong" if date is in summer before summertime was introduced !!!
        // e.g. if "1.7.1968" is picked then  "30.6. 23:00 CET" is stored instead of "1.7. 00:00" !!!
        // Table: PICKED in Frontend -> Delivered Date -> Stored Date String -> ok ?
        // "01.07.1968" -> "Sun Jun 30 23:00:00 CET 1968" -> 19680630230000000 -> !!! WRONG !!!
        // "01.02.1968" -> "Thu Feb 01 00:00:00 CET 1968" -> 19680201000000000 -> ok
        // "01.02.2005" -> "Tue Feb 01 00:00:00 CET 2005" (Winter) -> 20050201000000000 -> ok
        // "01.07.2005" -> "Fri Jul 01 00:00:00 CEST 2005" (CEST=Summertime) -> 20050701000000000 -> ok
        // So we have to add one hour to older dates where summertime is not taken into account !

        // Ohne Sommerzeit (1968 = vor Einführung der Sommerzeit !)
        // Tests mit letzter Tag des Monats 23:00 Uhr
        // Datum in Sommer muss eine Stunde dazu bekommen, da geliefertes Datum von Frontend
        // die Winterzeit war, also eine Stunde abgezogen wurde !
        // d.h. Tag wechselt in Sommerzeit !
        assertEquals("1968-01-31T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680131230000000"));
        assertEquals("1968-02-29T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680229230000000"));
        // here date changes to next day due to 1 hour added (fix wintertime date from frontend !)
        assertEquals("1968-04-01T00:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680331230000000"));
        assertEquals("1968-05-01T00:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680430230000000"));
        assertEquals("1968-06-01T00:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680531230000000"));
        assertEquals("1968-07-01T00:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680630230000000"));
        assertEquals("1968-08-01T00:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680731230000000"));
        assertEquals("1968-09-01T00:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680831230000000"));
        assertEquals("1968-10-01T00:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19680930230000000"));
        // here wintertime again, data from frontend is correct
        assertEquals("1968-10-31T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19681031230000000"));
        assertEquals("1968-11-30T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19681130230000000"));
        assertEquals("1968-12-31T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("19681231230000000"));

        // Mit existierender Sommerzeit (2000)
        // Tests mit erster Tag des Monats 23:00
        // Datum 1:1 übernehmen
        assertEquals("2000-01-01T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20000101230000000"));
        assertEquals("2000-02-01T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20000201230000000"));
        assertEquals("2000-03-01T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20000301230000000"));
        assertEquals("2000-04-01T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20000401230000000"));
        assertEquals("2000-05-01T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20000501230000000"));
        assertEquals("2000-06-01T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20000601230000000"));
        assertEquals("2000-07-01T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20000701230000000"));
        assertEquals("2000-08-01T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20000801230000000"));
        assertEquals("2000-09-01T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20000901230000000"));
        assertEquals("2000-10-01T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20001001230000000"));
        assertEquals("2000-11-01T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20001101230000000"));
        assertEquals("2000-12-01T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20001201230000000"));

        // Mit existierender Sommerzeit (2010)
        // Tests mit letzter Tag des Monats 23:00 Uhr
        // Datum 1:1 übernehmen
        assertEquals("2010-01-31T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20100131230000000"));
        assertEquals("2010-02-28T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20100228230000000"));
        assertEquals("2010-03-31T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20100331230000000"));
        assertEquals("2010-04-30T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20100430230000000"));
        assertEquals("2010-05-31T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20100531230000000"));
        assertEquals("2010-06-30T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20100630230000000"));
        assertEquals("2010-07-31T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20100731230000000"));
        assertEquals("2010-08-31T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20100831230000000"));
        assertEquals("2010-09-30T23:00:00.000+0200", UtilsCSWDate.mapFromIgcToIso8601("20100930230000000"));
        assertEquals("2010-10-31T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20101031230000000"));
        assertEquals("2010-11-30T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20101130230000000"));
        assertEquals("2010-12-31T23:00:00.000+0100", UtilsCSWDate.mapFromIgcToIso8601("20101231230000000"));
        
        assertEquals("2017-01-23", UtilsCSWDate.mapFromIgcToIso8601("20170123"));
        assertEquals("2016-03-12T13:10:45", UtilsCSWDate.mapFromIgcToIso8601("20160312131045"));
        assertEquals("2016-03-12T13:10:45.143+0100", UtilsCSWDate.mapFromIgcToIso8601("20160312131045143"));
        
    }

    public final void testFixIgcDateString() {
        // NOTICE: Handle problem of summertime before summertime was introduced !
        // see https://dev.informationgrid.eu/redmine/issues/439
        // and above testMapFromIgcToIso8601

        // Ohne Sommerzeit (1968 = vor Einführung der Sommerzeit !)
        // Tests mit letzter Tag des Monats 23:00 Uhr
        // Datum in Sommer muss eine Stunde dazu bekommen, da geliefertes Datum von Frontend
        // die Winterzeit war, also eine Stunde abgezogen wurde !
        // d.h. Tag wechselt in Sommerzeit !
        assertEquals("19680131230000000", UtilsCSWDate.fixIgcDateString("19680131230000000"));
        assertEquals("19680229230000000", UtilsCSWDate.fixIgcDateString("19680229230000000"));
        // here date changes to next day due to 1 hour added (fix wintertime date from frontend !)
        assertEquals("19680401000000000", UtilsCSWDate.fixIgcDateString("19680331230000000"));
        assertEquals("19680501000000000", UtilsCSWDate.fixIgcDateString("19680430230000000"));
        assertEquals("19680601000000000", UtilsCSWDate.fixIgcDateString("19680531230000000"));
        assertEquals("19680701000000000", UtilsCSWDate.fixIgcDateString("19680630230000000"));
        assertEquals("19680801000000000", UtilsCSWDate.fixIgcDateString("19680731230000000"));
        assertEquals("19680901000000000", UtilsCSWDate.fixIgcDateString("19680831230000000"));
        assertEquals("19681001000000000", UtilsCSWDate.fixIgcDateString("19680930230000000"));
        // here wintertime again, data from frontend is correct
        assertEquals("19681031230000000", UtilsCSWDate.fixIgcDateString("19681031230000000"));
        assertEquals("19681130230000000", UtilsCSWDate.fixIgcDateString("19681130230000000"));
        assertEquals("19681231230000000", UtilsCSWDate.fixIgcDateString("19681231230000000"));

        // Ohne Sommerzeit (1950 = vor Einführung der Sommerzeit !)
        // Tests mit letzter Tag des Monats 23:00 Uhr
        // Datum in Sommer muss eine Stunde dazu bekommen, da geliefertes Datum von Frontend
        // die Winterzeit war, also eine Stunde abgezogen wurde !
        // d.h. Tag wechselt in Sommerzeit !
        assertEquals("19500131230000000", UtilsCSWDate.fixIgcDateString("19500131230000000"));
        assertEquals("19500228230000000", UtilsCSWDate.fixIgcDateString("19500228230000000"));
        // here date changes to next day due to 1 hour added (fix wintertime date from frontend !)
        assertEquals("19500401000000000", UtilsCSWDate.fixIgcDateString("19500331230000000"));
        assertEquals("19500501000000000", UtilsCSWDate.fixIgcDateString("19500430230000000"));
        assertEquals("19500601000000000", UtilsCSWDate.fixIgcDateString("19500531230000000"));
        assertEquals("19500701000000000", UtilsCSWDate.fixIgcDateString("19500630230000000"));
        assertEquals("19500801000000000", UtilsCSWDate.fixIgcDateString("19500731230000000"));
        assertEquals("19500901000000000", UtilsCSWDate.fixIgcDateString("19500831230000000"));
        assertEquals("19501001000000000", UtilsCSWDate.fixIgcDateString("19500930230000000"));
        // here wintertime again, data from frontend is correct
        assertEquals("19501031230000000", UtilsCSWDate.fixIgcDateString("19501031230000000"));
        assertEquals("19501130230000000", UtilsCSWDate.fixIgcDateString("19501130230000000"));
        assertEquals("19501231230000000", UtilsCSWDate.fixIgcDateString("19501231230000000"));

        // Mit existierender Sommerzeit (2000)
        // Tests mit erster Tag des Monats 23:00
        // Datum 1:1 übernehmen
        assertEquals("20000101230000000", UtilsCSWDate.fixIgcDateString("20000101230000000"));
        assertEquals("20000201230000000", UtilsCSWDate.fixIgcDateString("20000201230000000"));
        assertEquals("20000301230000000", UtilsCSWDate.fixIgcDateString("20000301230000000"));
        assertEquals("20000401230000000", UtilsCSWDate.fixIgcDateString("20000401230000000"));
        assertEquals("20000501230000000", UtilsCSWDate.fixIgcDateString("20000501230000000"));
        assertEquals("20000601230000000", UtilsCSWDate.fixIgcDateString("20000601230000000"));
        assertEquals("20000701230000000", UtilsCSWDate.fixIgcDateString("20000701230000000"));
        assertEquals("20000801230000000", UtilsCSWDate.fixIgcDateString("20000801230000000"));
        assertEquals("20000901230000000", UtilsCSWDate.fixIgcDateString("20000901230000000"));
        assertEquals("20001001230000000", UtilsCSWDate.fixIgcDateString("20001001230000000"));
        assertEquals("20001101230000000", UtilsCSWDate.fixIgcDateString("20001101230000000"));
        assertEquals("20001201230000000", UtilsCSWDate.fixIgcDateString("20001201230000000"));

        // Mit existierender Sommerzeit (2010)
        // Tests mit letzter Tag des Monats 23:00 Uhr
        // Datum 1:1 übernehmen
        assertEquals("20100131230000000", UtilsCSWDate.fixIgcDateString("20100131230000000"));
        assertEquals("20100228230000000", UtilsCSWDate.fixIgcDateString("20100228230000000"));
        assertEquals("20100331230000000", UtilsCSWDate.fixIgcDateString("20100331230000000"));
        assertEquals("20100430230000000", UtilsCSWDate.fixIgcDateString("20100430230000000"));
        assertEquals("20100531230000000", UtilsCSWDate.fixIgcDateString("20100531230000000"));
        assertEquals("20100630230000000", UtilsCSWDate.fixIgcDateString("20100630230000000"));
        assertEquals("20100731230000000", UtilsCSWDate.fixIgcDateString("20100731230000000"));
        assertEquals("20100831230000000", UtilsCSWDate.fixIgcDateString("20100831230000000"));
        assertEquals("20100930230000000", UtilsCSWDate.fixIgcDateString("20100930230000000"));
        assertEquals("20101031230000000", UtilsCSWDate.fixIgcDateString("20101031230000000"));
        assertEquals("20101130230000000", UtilsCSWDate.fixIgcDateString("20101130230000000"));
        assertEquals("20101231230000000", UtilsCSWDate.fixIgcDateString("20101231230000000"));
    
    }
}
