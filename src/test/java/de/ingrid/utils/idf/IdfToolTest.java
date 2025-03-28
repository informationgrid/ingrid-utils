/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
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
package de.ingrid.utils.idf;

import de.ingrid.utils.dsc.Record;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdfToolTest {

    @Test
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
