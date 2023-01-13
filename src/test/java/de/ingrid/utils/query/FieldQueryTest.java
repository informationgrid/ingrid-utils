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
package de.ingrid.utils.query;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FieldQueryTest {

    @Test
    public void testCollisoion() throws Exception {
		FieldQuery fieldQuery1 = new FieldQuery(true, false, "partner", "bw");
		FieldQuery fieldQuery2 = new FieldQuery(true, false, "partner", "be");
        assertNotNull(fieldQuery2.hashCode());

		fieldQuery1 = new FieldQuery(true, false, "partner", "be");
		fieldQuery2 = new FieldQuery(true, false, "partner", "be");
        assertEquals(fieldQuery1.hashCode(), fieldQuery2.hashCode());

	}
}
