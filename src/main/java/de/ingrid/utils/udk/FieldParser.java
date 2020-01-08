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
 * Copyright (c) 2006 wemove digital solutions. All rights reserved.
 */

/**
 * 
 */
package de.ingrid.utils.udk;

/**
 * Interface for value parsers used to convert values
 * from CSW to UDK or vice versa.
 *  
 * @author ingo herwig <ingo@wemove.com>
 *
 */
public interface FieldParser {
	
	/**
	 * Parse a value and return the converted value. 
	 * @param value The value to parse
	 * @return The converted value
	 */
	public String parse(String value); 
}
