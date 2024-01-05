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
 * Copyright (c) 2006 wemove digital solutions. All rights reserved.
 */

package de.ingrid.utils.udk.iso19108;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of an ISO 19108 TM_PeriodDuration
 * 
 * Documentation from http://www.ncits.org/ref-docs/ISO_DIS_19108.pdf
 * 
 * TM_PeriodDuration uses the format specified by ISO 8601 for exchanging information about the duration of a
 * period. It allows duration to be expressed in terms of multiple units of time, specifically years, months, days, hours,
 * minutes, and seconds. Although individual values are optional, a value shall be provided for at least one unit.
 * 
 * Attributes:
 * a) Designator:CharacterString = P is a mandatory element which designates that the following characters represent the duration of a period
 * b) years [0..1]:CharacterString is a positive integer, followed by the character "Y" which indicates the number of years in the period
 * c) months [0..1]:CharacterString is a positive integer followed by the character "M" which indicates the number of months in the period
 * d) days [0..1]:CharacterString is a positive integer followed by the character "D" which indicates the number of days in the period
 * e) timeIndicator [0..1]:CharacterString = "T" shall be included whenever the sequence includes values for units of less than a day
 * f) hours [0..1]:CharacterString is a positive integer followed by the character "H" which indicates the number of hours in the period
 * g) minutes [0..1]:CharacterString is a positive integer followed by the character "H" which indicates the number of minutes in the period
 * h) seconds [0..1]:CharacterString is a positive integer followed by the character "S" which indicates the number of seconds in the period
 * 
 * The value for the rightmost unit may be expressed as a positive decimal fraction rather than as a positive integer.
 * 
 * EXAMPLE A duration of 5 days, 4 hours, and 30.7 minutes is represented as P5DT4H30.7M.
 * NOTE Although this format is defined in ISO 8601 for use with dates in the Gregorian calendar and times in UTC,
 * TM_PeriodDuration may be used as the data type for expressing length or distance whenever temporal positions are referenced
 * to a calendar that describes dates in terms of years, months and days and a clock that describes times in hours, minutes and
 * seconds.
 * 
 * @author ingo herwig <ingo@wemove.com>
 *
 */
public class TM_PeriodDuration {

	private static final String REGEXP = "P(([0-9\\.]+)Y)*(([0-9\\.]+)M)*(([0-9\\.]+)D)*T*(([0-9\\.]+)H)*(([0-9\\.]+)M)*(([0-9\\.]+)S)*";

	private static Pattern pattern;
	private static Matcher matcher;
	
	// number of known intervals
	private static final int NUM_INTERVALS = 6;
	public static final String UNDEFINED = "";
	// the interval values
	private String[] values = new String[NUM_INTERVALS];
	// indicates if an interval is defined
	private boolean[] isValueDefined = new boolean[NUM_INTERVALS];

	/**
	 * Parse a string representation into a TM_PeriodDuration instance
	 * @param value A string representation (e.g. "P9D")
	 * @return The corresponding TM_PeriodDuration instance
	 */
	public static TM_PeriodDuration parse(String value) {
		
		TM_PeriodDuration pd = null;
		
		// compile the regexp if not already done
		if (pattern == null)
			pattern = Pattern.compile(REGEXP);

		// create the matcher
		if (matcher == null)
			matcher = pattern.matcher(value);
		else
			matcher.reset(value);
		
		// get the match if any
		if (matcher.find()) {
			pd = new TM_PeriodDuration();

			// extract the interval values from the value
			// NOTE: the groups are due to the regexp
			for (int i=1; i<=NUM_INTERVALS; i++) {
				int group = i*2;
				int index = i-1;
				if (matcher.start(group) >= 0) {
					try {
						// set value if defined
						pd.values[index] = matcher.group(group);
						pd.isValueDefined[index] = true;
					} catch (Exception e) {
						// set value to empty if float conversion fails
						pd.values[index] = UNDEFINED;
						pd.isValueDefined[index] = false;
					}
				}
				else {
					// set value to empty if not defined
					pd.values[index] = UNDEFINED;
					pd.isValueDefined[index] = false;
				}
			}
		}
		return pd;
	}
	
	/**
	 * Check if a given interval is defined in the instance
	 * @param interval The interval to check
	 * @return True/False wether the interval is defined or not
	 */
	public boolean hasInterval(Interval interval) {
		return isValueDefined[interval.getValue()-1];
	}
	
	/**
	 * Get the value of a given interval.
	 * @param interval The interval to get the value for
	 * @return The value or NULL_VALUE if the interval is not defined
	 */
	public String getValue(Interval interval) {
		return values[interval.getValue()-1];
	}
	
	/**
	 * The known intervals
	 */
	public static class Interval {
		private int value = 0;
		private String name = null;
		
		public static final Interval YEARS = new Interval(1, "Jahre");
		public static final Interval MONTHS = new Interval(2, "Monate");
		public static final Interval DAYS = new Interval(3, "Tage");
		public static final Interval HOURS = new Interval(4, "Stunden");
		public static final Interval MINUTES = new Interval(5, "Minuten");
		public static final Interval SECONDS = new Interval(6, "Sekunden");

		/**
		 * Private constructor. We don't want anybody to define new ones.
		 * @param i The position in the interval sequence
		 * @param name The human readable name of the interval
		 */
		private Interval(int i, String name) {
			this.value = i;
			this.name = name;
		}
		
		/**
		 * Get the position in the interval sequence
		 * @return The position
		 */
		private int getValue() {
			return value;
		}

		/**
		 * Get the human readable name of the interval
		 * @return The name
		 */
		public String getName() {
			return name;
		}
	}
}
