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
package de.ingrid.utils.datatype;

import junit.framework.TestCase;

public class DataTypeTest extends TestCase {

    public void testMetadata() throws Exception {
        DataType dataType = new DataType();
        dataType.addMetadata("k1", "v1");
        assertEquals(true, dataType.contains("k1", "v1"));
        assertEquals(true, dataType.get("k1").contains("v1"));

        // test multiple values for one key
        dataType.addMetadata("k1", "v2");
        assertEquals(true, dataType.contains("k1", "v1"));
        assertEquals(true, dataType.contains("k1", "v2"));
        assertEquals(2, dataType.get("k1").size());
        assertEquals(true, dataType.get("k1").contains("v1"));
        assertEquals(true, dataType.get("k1").contains("v2"));
    }
}
