package de.ingrid.utils.datatype;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import de.ingrid.utils.TestUtils;

public class DataTypeProviderTest extends TestCase {

    public void testLoadDatatypes() throws Exception {
        File providerFile = new File("target/tests/datatype-test.provider");
        TestUtils.writeFile(providerFile, Arrays.asList(new String[] { "foo.bar.hidden:true" }));

        IDataTypeProvider datatypeProvider = new DataTypeProvider(providerFile, new DataTypeEditor());
        DataType[] dataTypes = datatypeProvider.getDataTypes();
        assertNotNull(dataTypes);
        assertEquals(1, dataTypes.length);
        DataType dataType = dataTypes[0];
        assertEquals("foo", dataType.getName());
        assertEquals("bar", dataType.getDisplayName());
        assertTrue(dataType.contains("hidden", "true"));
    }

    public void testIncludedDatatype() throws Exception {
        File providerFile = new File("target/tests/datatype-test.provider");
        List lines = new ArrayList();
        lines.add("foo1.foo1Display.include:foo2");
        lines.add("foo2.foo2Display.hidden:true");
        TestUtils.writeFile(providerFile, lines);

        IDataTypeProvider datatypeProvider = new DataTypeProvider(providerFile, new DataTypeEditor());
        DataType[] dataTypes = datatypeProvider.getDataTypes();
        assertEquals(2, dataTypes.length);
        assertEquals("foo1", dataTypes[0].getName());
        assertEquals("foo2", dataTypes[1].getName());

        assertEquals(1, datatypeProvider.getIncludedDataTypes(dataTypes[0]).length);
        assertEquals(1, datatypeProvider.getIncludedDataTypes(dataTypes[0].getName()).length);
        assertEquals(dataTypes[1], datatypeProvider.getIncludedDataTypes(dataTypes[0])[0]);
        assertEquals(0, datatypeProvider.getIncludedDataTypes(dataTypes[1]).length);
    }

    public void testIncluded2Datatypes() throws Exception {
        File providerFile = new File("target/tests/datatype-test.provider");
        List lines = new ArrayList();
        lines.add("foo1.foo1Display.include:foo2.include:foo3");
        lines.add("foo2.foo2Display.hidden:true");
        lines.add("foo3.foo3Display.hidden:true");
        TestUtils.writeFile(providerFile, lines);

        IDataTypeProvider datatypeProvider = new DataTypeProvider(providerFile, new DataTypeEditor());
        DataType[] dataTypes = datatypeProvider.getDataTypes();
        assertEquals(3, dataTypes.length);
        assertEquals("foo1", dataTypes[0].getName());
        assertEquals("foo2", dataTypes[1].getName());
        assertEquals("foo3", dataTypes[2].getName());

        assertEquals(2, datatypeProvider.getIncludedDataTypes(dataTypes[0]).length);
        assertEquals(dataTypes[1], datatypeProvider.getIncludedDataTypes(dataTypes[0])[0]);
        assertEquals(dataTypes[2], datatypeProvider.getIncludedDataTypes(dataTypes[0])[1]);
        assertEquals(0, datatypeProvider.getIncludedDataTypes(dataTypes[1]).length);
    }

}