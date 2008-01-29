package de.ingrid.utils.datatype;

import java.beans.PropertyEditor;

import junit.framework.TestCase;

public class DataTypeEditorTest extends TestCase {

    public void testEditor() throws Exception {

        PropertyEditor editor = new DataTypeEditor();
        editor.setAsText("foo.bar.visible:false.type:catalog");
        Object value = editor.getValue();
        DataType dataType = (DataType) value;
        assertEquals("foo", dataType.getName());
        assertEquals("bar", dataType.getDisplayName());
        assertTrue(dataType.contains("visible", "false"));
        assertTrue(dataType.contains("type", "catalog"));
        assertFalse(dataType.contains("type", "web"));
        assertEquals("foo.bar.visible:false.type:catalog", editor.getAsText());

    }
}
