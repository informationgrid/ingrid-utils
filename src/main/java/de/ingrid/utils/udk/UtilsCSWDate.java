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
/*
 * Copyright (c) 1997-2006 by wemove GmbH
 */
package de.ingrid.utils.udk;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import jakarta.xml.bind.DatatypeConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class contains date conversion helper functions.
 *
 * @author joachim@wemove.com
 */
public class UtilsCSWDate {


    private final static Log log = LogFactory.getLog(UtilsDate.class);

    /** Type of Pattern for date formatter */
    private enum PatternType {
        IGC,
        CSW;
    }
	public static boolean isCSWDate(String dateString) {
		// try to detect non standard date pattern that may exist in ISO files
	    if (getDatePattern(dateString) != null) {
		    return true;
		}


        try {
            // Note that W3C XML Schema 1.0 validation does not allow for the year field to have a value of zero.
            Calendar c = DatatypeConverter.parseDateTime( dateString );
            Instant instant = c.toInstant();

            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, c.getTimeZone().toZoneId());
            int year = zonedDateTime.getYear();

            // check if the year of the parsed date fits the source datestring
            // we need to make this check, because DatatypeConverter.parseDateTime also
            // accepts source strings like '20061012000000000' and parses this to
            // '275323773-06-28T19:08:16'
            // Format Number to be 4 long and add negative sign if needed (e.g.:   0100  -0430 0025 etc.)
            String  expectedYearString =  (year <= 0) ? String.format("-%04d", Math.abs(year-1)): String.format("%04d", Math.abs(year));
            return dateString.startsWith(expectedYearString);
        } catch (Exception e) {
            return false;
        }
	}

	public static String getDateWithoutTime(String dateString) {
		if (dateString != null && dateString.length() >= 8) {
			return dateString.substring(0, 8);
		}
		return dateString;
	}

	public static String getQueryDateStyle(String dateString) {

		String destString = mapDateFromIso8601ToIndex(dateString);

		if (destString != null) {
			return getDateWithoutTime(destString);
		} else {
			log.warn("Unrecognizes date type: " + dateString);
			return dateString;
		}
	}

	/**
	 * Detect non standard date pattern that might exists in ISO files.
	 *
	 * @param dateString
	 * @return
	 */
	public static String getDatePattern(String dateString) {
		String datePattern = null;
		if (dateString.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9]")) {
			datePattern = "yyyyMMdd";
		} else if (dateString.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
			datePattern = "yyyy-MM-dd";
		} else if (dateString.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9]T[0-2][0-9][0-5][0-9][0-5][0-9]")) {
			datePattern = "yyyyMMdd'T'HHmmss";
		}
		return datePattern;
	}

    /**
     * Transform a given date string into the internal used DateTime
     * representation 'yyyyMMddHHmmssSSS'. The date string should be formatted
     * in ISO 8601 Format. Some exceptions are allowed because some pattern were
     * observed to exist in ISO data files and should be converted.
     *
     * The local standard time is used, if no time zone is supplied.
     *
     * @param dateIso8601
     * @return
     */
	public static String mapDateFromIso8601ToIndex(String dateIso8601) {
		String result = null;
		if (dateIso8601 != null && dateIso8601.length() > 0) {
	        // try to detect non standard date pattern that may exist in ISO files
			String srcPattern = UtilsCSWDate.getDatePattern(dateIso8601);
			if (srcPattern != null && srcPattern.length() > 0) {
				result = UtilsDate.convertDateString(dateIso8601, srcPattern, "yyyyMMddHHmmssSSS");
			} else {
	            try {
	                Calendar cal = DatatypeConverter.parseDateTime(dateIso8601);
	                SimpleDateFormat portalFormat = new SimpleDateFormat("yyyy");
	                portalFormat.applyPattern("yyyyMMddHHmmssSSS");
	                result = portalFormat.format(cal.getTime());
	            } catch (Exception e) {
				if (log.isDebugEnabled()) {
                    log.debug("Error converting date '" + dateIso8601 + "'. Does it conform to the ISO 8601 format?");
				}
			}
		}
		}
		return result;
	}

	/**
	 * Transform date strings of possible internal representations into ISO 8601 compliant formats.
	 *
	 * @param inIgcDateString
	 * @return
	 */
	public static String mapFromIgcToIso8601(String inIgcDateString) {
        SimpleDateFormat df = new SimpleDateFormat();
        SimpleDateFormat cswFormat = new SimpleDateFormat();
        Date parsedDate = null;

        try {
            String igcDate = fixIgcDateString(inIgcDateString);

            String igcPattern = getPatternFromIgcDateString(igcDate, PatternType.IGC);
            if (igcPattern != null) {
                df.applyPattern(igcPattern);
                parsedDate = df.parse(igcDate);
            }

            if (parsedDate != null) {
                String cswPattern = getPatternFromIgcDateString(igcDate, PatternType.CSW);
                cswFormat.applyPattern(cswPattern);
                return cswFormat.format(parsedDate);
            }
        } catch (Exception e) {
            log.error("mapFromIgcToIso8601 failed (" + inIgcDateString + ").", e);
        }

        return null;
	}

    /** Handle problem of summertime before summertime was introduced !
     * see https://dev.informationgrid.eu/redmine/issues/439
     * @param inIgcDateString date string as stored in database
     * @return fixed date string as stored in catalog
     */
    public static String fixIgcDateString(String inIgcDateString) {
        SimpleDateFormat df = new SimpleDateFormat();
        Date parsedDate = null;

        try {
            String igcPattern = getPatternFromIgcDateString(inIgcDateString, PatternType.IGC);

            if (igcPattern != null) {
                df.applyPattern(igcPattern);
                parsedDate = df.parse(inIgcDateString);
            }

            if (parsedDate != null) {
                // NOTICE: Handle problem of summertime before summertime was introduced !
                // see https://dev.informationgrid.eu/redmine/issues/439

                // Stored Date from frontend is "wrong" if date is in summer before summertime was introduced !!!
                // e.g. if "1.7.1968" is picked then  "30.6. 23:00 CET" is stored instead of "1.7. 00:00" !!!
                // PICKED -> Delivered Date -> Stored Date String -> ok ?
                // "01.07.1968" -> "Sun Jun 30 23:00:00 CET 1968" -> 19680630230000000 -> !!! WRONG !!!
                // "01.02.1968" -> "Thu Feb 01 00:00:00 CET 1968" -> 19680201000000000 -> ok
                // "01.02.2005" -> "Tue Feb 01 00:00:00 CET 2005" (Winter) -> 20050201000000000 -> ok
                // "01.07.2005" -> "Fri Jul 01 00:00:00 CEST 2005" (CEST=Summertime) -> 20050701000000000 -> ok
                // So we have to add one hour to older dates where summertime is not taken into account !

                // create own time zone with summertime (MEZ)
                // CET timezone does IGNORE older dates !
                SimpleTimeZone myMEZ = new SimpleTimeZone( +1*60*60*1000, "myCET" );
                myMEZ.setStartRule( Calendar.MARCH, -1, Calendar.SUNDAY, 2*60*60*1000 );
                myMEZ.setEndRule( Calendar.OCTOBER, -1, Calendar.SUNDAY, 2*60*60*1000 );

                // create date via calendar with our timezone and check offset of timezone
                Calendar calendar = Calendar.getInstance(myMEZ);
                calendar.setTimeInMillis(parsedDate.getTime());
                String tzoneOffsetString = new SimpleDateFormat("Z").format(calendar.getTime());

                // if date is in summertime and offset is not 2 hours then we have an old date where date was delivered -1 hour !
                if (myMEZ.inDaylightTime(calendar.getTime()) && !tzoneOffsetString.equals("+0200")) {
                    int myHour = calendar.get( Calendar.HOUR_OF_DAY );
                    calendar.set( Calendar.HOUR_OF_DAY, myHour+1 );
                }

                return df.format(calendar.getTime());
            }
        } catch (Exception e) {
            log.error("fixIgcDateString failed (" + inIgcDateString + ").", e);
        }

        return null;
    }

    private static String getPatternFromIgcDateString(String igcDateString, PatternType pType) {
        try {

            if (igcDateString == null) {
                log.warn("IGC Datestring is null");
                return null;
            } else if (igcDateString.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9][0-2][0-9][0-5][0-9][0-5][0-9]")) {
                if (pType == PatternType.IGC) {
                    return "yyyyMMddHHmmss";
                }
                if (pType == PatternType.CSW) {
                    return "yyyy-MM-dd'T'HH:mm:ssXXX";
                }
            } else if (igcDateString.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9][0-2][0-9][0-5][0-9][0-5][0-9][0-9][0-9][0-9]")) {
                if (pType == PatternType.IGC) {
                    return "yyyyMMddHHmmssSSS";
                }
                if (pType == PatternType.CSW) {
                    return "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
                }
            } else if (igcDateString.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9]")) {
                if (pType == PatternType.IGC) {
                    return "yyyyMMdd";
                }
                if (pType == PatternType.CSW) {
                    return "yyyy-MM-dd";
                }
            } else if (igcDateString.matches("[0-9][0-9][0-9][0-9]")) {
                // e.g. only year set in t011_obj_literature.publish_year but mapped to gmd:editionDate
                if (pType == PatternType.IGC) {
                    // used to transform igc date, e.g. 1995, into java date !
                    return "yyyy";
                }
                if (pType == PatternType.CSW) {
                    // used to transform java date from above to ISO XML
                    return "yyyy-MM-dd";
                }
            }
        } catch (Exception e) {
            log.error("getPatternFromIgcDateString failed (" + igcDateString + ", " + pType + ").", e);
        }

        log.warn("Could NOT determine pattern from IGC date string '" + igcDateString + "' we return null !");

        return null;
    }

}
