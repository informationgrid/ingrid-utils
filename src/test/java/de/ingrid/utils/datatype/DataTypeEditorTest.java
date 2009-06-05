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
