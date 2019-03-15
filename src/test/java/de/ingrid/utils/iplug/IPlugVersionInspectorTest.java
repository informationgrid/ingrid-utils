/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2019 wemove digital solutions GmbH
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
package de.ingrid.utils.iplug;

import org.json.simple.parser.ParseException;

import junit.framework.TestCase;

public class IPlugVersionInspectorTest extends TestCase {

    public void testCompareVersion() throws ParseException {
        assertFalse(IPlugVersionInspector.compareVersion("1.1.0", "1.1.11"));
        assertTrue(IPlugVersionInspector.compareVersion("1.1.0", "1.0.9"));
        assertFalse(IPlugVersionInspector.compareVersion("1.1.0-SNAPSHOT", "1.2.0"));
        assertFalse(IPlugVersionInspector.compareVersion("1.2.0-SNAPSHOT", "1.2.0"));
        assertTrue(IPlugVersionInspector.compareVersion("1.2.0", "1.2.0-SNAPSHOT"));
        assertTrue(IPlugVersionInspector.compareVersion("1.2.0", "1.2.0"));
        assertFalse(IPlugVersionInspector.compareVersion("unknown", "1.2.0"));
    }

}
