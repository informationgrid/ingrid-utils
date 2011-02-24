package de.ingrid.utils.idf;

import junit.framework.TestCase;
import de.ingrid.utils.dsc.Record;

public class IdfToolTest extends TestCase {

    public void testCompressIdfRecord() {
        Record r = new Record();
        String str = "Und ich duese, duese, duese, duese im Sauseschritt...";
        r.put("data", str);
        r = IdfTool.compressIdfRecord(r);
        assertEquals((String)r.get("compressed"), "true");
        r = IdfTool.uncompressIdfRecord(r);
        assertEquals((String)r.get("data"), str);
    }

}
