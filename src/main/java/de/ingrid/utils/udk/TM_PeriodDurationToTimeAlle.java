/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2016 wemove digital solutions GmbH
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
 * Copyright (c) 2006 wemove digital solutions. All rights reserved.
 */

/**
 * 
 */
package de.ingrid.utils.udk;

import de.ingrid.utils.udk.iso19108.TM_PeriodDuration;


/**
 * Parse an ISO 19108 TM_PeriodDuration and extract the duration (e.g. "P9D" -> "9") value. 
 * If more than one interval is given, the first (= longest) duration will be extracted (e.g. "P1Y9D" -> "1"). 
 * If no interval is given an empty string will be returned.
 * 
 * @author ingo herwig <ingo@wemove.com>
 * 
 */
public class TM_PeriodDurationToTimeAlle implements FieldParser {

	/**
	 * @see de.ingrid.utils.udk.FieldParser#parse(java.lang.String)
	 */
	public String parse(String value) {
		String result = null;
		
		try {
			TM_PeriodDuration pd = TM_PeriodDuration.parse(value);
			if (pd.hasInterval(TM_PeriodDuration.Interval.YEARS))
				result = pd.getValue(TM_PeriodDuration.Interval.YEARS);
			else if (pd.hasInterval(TM_PeriodDuration.Interval.MONTHS))
				result = pd.getValue(TM_PeriodDuration.Interval.MONTHS);
			else if (pd.hasInterval(TM_PeriodDuration.Interval.DAYS))
				result = pd.getValue(TM_PeriodDuration.Interval.DAYS);
			else if (pd.hasInterval(TM_PeriodDuration.Interval.HOURS))
				result = pd.getValue(TM_PeriodDuration.Interval.HOURS);
			else if (pd.hasInterval(TM_PeriodDuration.Interval.MINUTES))
				result = pd.getValue(TM_PeriodDuration.Interval.MINUTES);
			else if (pd.hasInterval(TM_PeriodDuration.Interval.SECONDS))
				result = pd.getValue(TM_PeriodDuration.Interval.SECONDS);
			else
				result = value;
		} catch (Exception e) {
			result = value;
		}
		return result;
	}
}
