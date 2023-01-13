/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SNSUtilTest {

    /*
     * Test method for 'de.ingrid.utils.tool.SNSUtil.transformSpacialReference(String)'
     */
    @Test
    public void testTransformSpacialReference() {
        assertEquals("1234567", SNSUtil.transformSpacialReference("ags:", "ags:1234567"));
        assertEquals("BUNDESLAND12", SNSUtil.transformSpacialReference("ags:", "BUNDESLAND12"));
        assertEquals("KREIS12345", SNSUtil.transformSpacialReference("ags:", "KREIS12345"));
        assertEquals("SCHUTZGEBIET170", SNSUtil.transformSpacialReference("ags:", "SCHUTZGEBIET170"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "rs:1234567890"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "rs:1234567890 ags:1234567"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "ags:1234567 rs:1234567890"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "ags:1234567 rs:1234567890 bla:12345678901"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference(null, "rs:1234567890"));
        assertEquals("1234567", SNSUtil.transformSpacialReference(null, "ags:1234567 rs:1234567890"));
        
    }

}
