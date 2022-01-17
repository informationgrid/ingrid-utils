/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2022 wemove digital solutions GmbH
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

import java.beans.PropertyEditor;

import junit.framework.TestCase;

public class DataTypeEditorTest extends TestCase {

    PropertyEditor fEditor = new DataTypeEditor();

    public void testDataType() throws Exception {
        fEditor.setAsText("foo.bar.visible:false.type:catalog");

        Object value = fEditor.getValue();
        DataType dataType = (DataType) value;
        assertEquals("foo", dataType.getName());
        assertEquals("bar", dataType.getDisplayName());
        assertTrue(dataType.contains("visible", "false"));
        assertTrue(dataType.contains("type", "catalog"));
        assertFalse(dataType.contains("type", "web"));
        assertEquals("foo.bar.visible:false.type:catalog", fEditor.getAsText());
    }

    public void testIncludedDataType() throws Exception {
        fEditor.setAsText("foo.bar.visible:false.type:catalog.include:foo2");

        Object value = fEditor.getValue();
        DataType dataType = (DataType) value;
        assertEquals("foo", dataType.getName());
        assertEquals("bar", dataType.getDisplayName());
        assertTrue(dataType.contains("visible", "false"));
        assertTrue(dataType.contains("type", "catalog"));
        assertFalse(dataType.contains("type", "web"));
        assertTrue(dataType.contains("include", "foo2"));
        assertEquals("foo.bar.visible:false.type:catalog.include:foo2", fEditor.getAsText());
    }
}
