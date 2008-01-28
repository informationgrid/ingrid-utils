package de.ingrid.utils.datatype;

import junit.framework.TestCase;

public class DataTypeProviderTest extends TestCase{

    public void testLoadDatatypes() throws Exception {
       IDataTypeProvider datatypeProvider = new DataTypeProvider(new DataTypeEditor());
       DataType[] dataTypes = datatypeProvider.getDataTypes();
       assertNotNull(dataTypes);
       assertEquals(1, dataTypes.length);
       DataType dataType = dataTypes[0];
       assertEquals("foo", dataType.getName());
       assertEquals("bar", dataType.getDisplayName());
       assertTrue(dataType.isVisible());
    }
}
