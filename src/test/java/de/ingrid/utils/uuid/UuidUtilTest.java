/*-
 * **************************************************-
 * InGrid Utils
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
package de.ingrid.utils.uuid;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UuidUtilTest {

    @Test
    void uuidType3Test() {
        UUID foo = UuidUtil.uuidType3(UuidUtil.NAMESPACE_DNS, "foo");
        UUID bar = UuidUtil.uuidType3(UuidUtil.NAMESPACE_DNS, "bar");

        assertEquals("3f46ae03-c654-36b0-a55d-cd0aa042c9f2", foo.toString());
        assertEquals("b4a1f07b-64b1-3112-b69c-d89a0185c7c6", bar.toString());
    }
}

