/*
 * Copyright (c) 2006 wemove digital solutions. All rights reserved.
 */

/**
 * 
 */
package de.ingrid.utils.udk;

import de.ingrid.utils.udk.iso19108.TM_PeriodDuration;

/**
 * Parse an ISO 19108 TM_PeriodDuration and extract the interval (e.g. "P9D" -> "Tage") value.
 * If more than one interval is given, the first (= longest) interval will be extracted (e.g. "P1Y9D" -> "Jahre"). 
 * If no interval is given an empty string will be returned.
 *  
 * @author ingo herwig <ingo@wemove.com>
 * 
 */
public class TM_PeriodDurationToTimeInterval implements FieldParser {

	/**
	 * @see de.ingrid.utils.udk.FieldParser#parse(java.lang.String)
	 */
	public String parse(String value) {
		String result = null;
		
		try {
			TM_PeriodDuration pd = TM_PeriodDuration.parse(value);
			if (pd.hasInterval(TM_PeriodDuration.Interval.YEARS))
				result = TM_PeriodDuration.Interval.YEARS.getName();
			else if (pd.hasInterval(TM_PeriodDuration.Interval.MONTHS))
				result = TM_PeriodDuration.Interval.MONTHS.getName();
			else if (pd.hasInterval(TM_PeriodDuration.Interval.DAYS))
				result = TM_PeriodDuration.Interval.DAYS.getName();
			else if (pd.hasInterval(TM_PeriodDuration.Interval.HOURS))
				result = TM_PeriodDuration.Interval.HOURS.getName();
			else if (pd.hasInterval(TM_PeriodDuration.Interval.MINUTES))
				result = TM_PeriodDuration.Interval.MINUTES.getName();
			else if (pd.hasInterval(TM_PeriodDuration.Interval.SECONDS))
				result = TM_PeriodDuration.Interval.SECONDS.getName();
			else
				result = value;
		} catch (Exception e) {
			result = value;
		}
		return result;
	}
}
