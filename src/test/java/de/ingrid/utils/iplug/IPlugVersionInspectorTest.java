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
package de.ingrid.utils.iplug;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IPlugVersionInspectorTest {

    @Test
    public void testCompareVersion() throws ParseException {
        assertFalse(IPlugVersionInspector.compareVersion("1.1.0", "1.1.11"));
        assertTrue(IPlugVersionInspector.compareVersion("1.1.0", "1.0.9"));
        assertFalse(IPlugVersionInspector.compareVersion("1.1.0-SNAPSHOT", "1.2.0"));
        assertFalse(IPlugVersionInspector.compareVersion("1.2.0-SNAPSHOT", "1.2.0"));
        assertTrue(IPlugVersionInspector.compareVersion("1.2.0", "1.2.0-SNAPSHOT"));
        assertTrue(IPlugVersionInspector.compareVersion("1.2.0", "1.2.0"));
        assertTrue(IPlugVersionInspector.compareVersion("unknown", "1.2.0"));
    }

}
