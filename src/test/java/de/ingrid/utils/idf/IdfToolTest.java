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
package de.ingrid.utils.idf;

import junit.framework.TestCase;
import de.ingrid.utils.dsc.Record;

public class IdfToolTest extends TestCase {

    public void testCompressIdfRecord() {
        Record r = new Record();
        String str = "Und ich duese, duese, duese, duese im Sauseschritt...";
        r.put("data", str);
        r = IdfTool.compressIdfRecord(r);
        assertEquals((String)r.get("compressed"), "true");
        r = IdfTool.uncompressIdfRecord(r);
        assertEquals((String)r.get("data"), str);
    }

}
