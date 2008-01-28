package de.ingrid.utils.datatype;

import java.beans.PropertyEditor;

import junit.framework.TestCase;

public class DataTypeEditorTest extends TestCase {

    public void testEditor() throws Exception {
        
        PropertyEditor editor = new DataTypeEditor();
        editor.setAsText("foo.bar.false");
        Object value = editor.getValue();
        DataType dataType = (DataType) value;
        assertEquals("foo", dataType.getName());
        assertEquals("bar", dataType.getDisplayName());
        assertEquals(false, dataType.isVisible());
        assertEquals("foo.bar.false", editor.getAsText());
        
    }
}
