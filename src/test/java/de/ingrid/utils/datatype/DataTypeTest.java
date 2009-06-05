package de.ingrid.utils.datatype;

import junit.framework.TestCase;

public class DataTypeTest extends TestCase {

    public void testMetadata() throws Exception {
        DataType dataType = new DataType();
        dataType.addMetadata("k1", "v1");
        assertEquals(true, dataType.contains("k1", "v1"));
        assertEquals(true, dataType.get("k1").contains("v1"));

        // test multiple values for one key
        dataType.addMetadata("k1", "v2");
        assertEquals(true, dataType.contains("k1", "v1"));
        assertEquals(true, dataType.contains("k1", "v2"));
        assertEquals(2, dataType.get("k1").size());
        assertEquals(true, dataType.get("k1").contains("v1"));
        assertEquals(true, dataType.get("k1").contains("v2"));
    }
}
