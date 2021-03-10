/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
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
package de.ingrid.utils.tool;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

    public void testReplaceLineFeeds() {
    	String inString = "line1\nline2\rline3\n\r";
//    	System.out.println("testReplaceLineFeeds with String '" + inString + "'");
        assertEquals("line1line2line3", StringUtil.replaceLineFeeds(inString, ""));
        assertEquals("line1 line2 line3", StringUtil.replaceLineFeeds(inString, " "));
    }
}
