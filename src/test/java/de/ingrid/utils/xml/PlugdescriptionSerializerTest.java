package de.ingrid.utils.xml;

import java.io.File;

import junit.framework.TestCase;
import de.ingrid.utils.PlugDescription;

public class PlugdescriptionSerializerTest extends TestCase {

    private File _target = new File(System.getProperty("java.io.tmpdir"), "" + System.currentTimeMillis());

    @Override
    protected void setUp() throws Exception {
        assertTrue(_target.mkdirs());
    }

    @Override
    protected void tearDown() throws Exception {
        assertTrue(new File(_target, "pd.xml").delete());
        assertTrue(_target.delete());
    }

    public void testGetDeSerializedFrom() throws Exception {
        PlugdescriptionSerializer plugdescriptionSerializer = new PlugdescriptionSerializer();

        plugdescriptionSerializer.serialize(new PlugDescription(), new File(_target, "pd.xml"));

        PlugDescription plugDescription = plugdescriptionSerializer.deSerialize(new File(_target, "pd.xml"));
        assertEquals(new File(_target, "pd.xml").getAbsolutePath(), plugDescription.getDesrializedFromFolder().getAbsolutePath());
    }
}
