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
/**
 * 
 */
package de.ingrid.utils.udk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author joachim
 *
 */
public class UtilsStringTest {

    /**
     * Test method for {@link de.ingrid.utils.udk.UtilsString#transformNumberStrToIGCNumber(java.lang.String)}.
     */
    @Test
    public void testTransformNumberStrToIGCNumber() {
		assertEquals("2.2", UtilsString.transformNumberStrToIGCNumber("2,2"));
		assertEquals("67.4", UtilsString.transformNumberStrToIGCNumber("67,4"));
		assertEquals("67.4", UtilsString.transformNumberStrToIGCNumber("67.4"));
		assertEquals("6743546566.444", UtilsString.transformNumberStrToIGCNumber("6743546566.444"));
		assertEquals("6743546566", UtilsString.transformNumberStrToIGCNumber("6743546566"));
		assertEquals("1000.00", UtilsString.transformNumberStrToIGCNumber("1,000.00"));
		assertEquals("1000.00", UtilsString.transformNumberStrToIGCNumber("1.000,00"));
		assertEquals("1000000.00", UtilsString.transformNumberStrToIGCNumber("1.000.000,00"));
		assertEquals("1000000.00", UtilsString.transformNumberStrToIGCNumber("1,000,000.00"));
		assertEquals("10000000.00", UtilsString.transformNumberStrToIGCNumber("10,000,000.00"));
		assertEquals("1000", UtilsString.transformNumberStrToIGCNumber("1.000"));
		assertEquals("1000000", UtilsString.transformNumberStrToIGCNumber("1.000.000"));
		assertEquals("10000000", UtilsString.transformNumberStrToIGCNumber("10.000.000"));
		assertEquals("abccvcv,sd.", UtilsString.transformNumberStrToIGCNumber("abccvcv,sd."));
	}

}
