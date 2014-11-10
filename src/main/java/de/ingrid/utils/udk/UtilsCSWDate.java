/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 wemove digital solutions GmbH
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
 * Copyright (c) 1997-2006 by wemove GmbH
 */
package de.ingrid.utils.udk;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.udk.UtilsDate;

/**
 * This class contains date conversion helper functions.
 * 
 * @author joachim@wemove.com
 */
public class UtilsCSWDate {

	
    private final static Log log = LogFactory.getLog(UtilsDate.class);
	
	public static boolean isCSWDate(String dateString) {
		return getDatePattern(dateString) != null;
	}

	public static String getDateWithoutTime(String dateString) {
		if (dateString != null && dateString.length() >= 8) {
			return dateString.substring(0, 8);
		}
		return dateString;
	}

	public static String getQueryDateStyle(String dateString) {
		
		String srcPattern = getDatePattern(dateString);
		
		if (srcPattern != null) {
			return UtilsDate.convertDateString(dateString, srcPattern, "yyyyMMdd");
		} else {
			log.warn("Unrecognizes date type: " + dateString);
			return dateString;
		}
	}
	
	public static String getDatePattern(String dateString) {
		String datePattern = null;
		if (dateString.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9]")) {
			datePattern = "yyyyMMdd";
		} else if (dateString.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
			datePattern = "yyyy-MM-dd";
		} else if (dateString.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9]T[0-2][0-9][0-5][0-9][0-5][0-9]")) {
			datePattern = "yyyyMMdd'T'HHmmss";
		} else if (dateString.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9]")) {
			datePattern = "yyyy-MM-dd'T'HH:mm:ss";
		} else if (dateString.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\.[0-9]+")) {
			datePattern = "yyyy-MM-dd'T'HH:mm:ss.sss";
		} else if (dateString.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\.[0-9]+Z")) {
			datePattern = "yyyy-MM-dd'T'HH:mm:ss.sssZ";
		}
		return datePattern;
	}
	
	public static String mapDateFromIso8601ToIndex(String dateIso8601) {
		String result = null;
		if (dateIso8601 != null && dateIso8601.length() > 0) {
			String srcPattern = UtilsCSWDate.getDatePattern(dateIso8601);
			if (srcPattern != null && srcPattern.length() > 0) {
				result = UtilsDate.convertDateString(dateIso8601, srcPattern, "yyyyMMddHHmmssSSS");
			} else {
				if (log.isDebugEnabled()) {
					log.debug("Error converting date '" + dateIso8601 + "'. Does it conform to the ISO 8601 format?");
				}
			}
		}
		return result;
	}
	
	public static String mapFromIgcToIso8601(String igcDate) {
        SimpleDateFormat df = new SimpleDateFormat();
        SimpleDateFormat cswFormat = new SimpleDateFormat();
        try {
            if (igcDate.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9][0-2][0-9][0-5][0-9][0-5][0-9]")) {
                df.applyPattern("yyyyMMddHHmmss");
                cswFormat.applyPattern("yyyy-MM-dd'T'HH:mm:ss");
                return cswFormat.format(df.parse(igcDate));
            } else if (igcDate.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9][0-2][0-9][0-5][0-9][0-5][0-9][0-9][0-9][0-9]")) {
                df.applyPattern("yyyyMMddHHmmssSSS");
                cswFormat.applyPattern("yyyy-MM-dd'T'HH:mm:ss");
                return cswFormat.format(df.parse(igcDate));
            } else if (igcDate.matches("[0-9][0-9][0-9][0-9]0000")) {
                df.applyPattern("yyyy");
                cswFormat.applyPattern("yyyy");
                return cswFormat.format(df.parse(igcDate.substring(0, 4)));
            } else if (igcDate.matches("[0-9][0-9][0-9][0-9][0-1][0-9]00")) {
                df.applyPattern("yyyyMM");
                cswFormat.applyPattern("yyyy-MM");
                return cswFormat.format(df.parse(igcDate.substring(0, 6)));
            } else if (igcDate.matches("[0-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9]")) {
                df.applyPattern("yyyyMMdd");
                cswFormat.applyPattern("yyyy-MM-dd");
                return cswFormat.format(df.parse(igcDate));
            }
        } catch (Exception e) {
            log.error("Parsing CSW date failed (" + igcDate + ").", e);
        }
        return null;
	    
	}
	
}
