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
package de.ingrid.utils.xml;

import java.io.File;

import de.ingrid.utils.PlugDescription;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlugdescriptionSerializerTest {

    private File _target = new File(System.getProperty("java.io.tmpdir"), "" + System.currentTimeMillis());

    @BeforeEach
    public void setUp() throws Exception {
        assertTrue(_target.mkdirs());
    }

    @AfterEach
    public void tearDown() throws Exception {
        assertTrue(new File(_target, "pd.xml").delete());
        assertTrue(_target.delete());
    }

    @Test
    public void testGetDeSerializedFrom() throws Exception {
        PlugdescriptionSerializer plugdescriptionSerializer = new PlugdescriptionSerializer();

        plugdescriptionSerializer.serialize(new PlugDescription(), new File(_target, "pd.xml"));

        PlugDescription plugDescription = plugdescriptionSerializer.deSerialize(new File(_target, "pd.xml"));
        assertEquals(new File(_target, "pd.xml").getAbsolutePath(), plugDescription.getDesrializedFromFolder().getAbsolutePath());
    }
}
