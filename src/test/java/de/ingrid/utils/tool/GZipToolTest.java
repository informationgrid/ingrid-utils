package de.ingrid.utils.tool;

import java.io.IOException;

import junit.framework.TestCase;

public class GZipToolTest extends TestCase {

    public void testGZIP() throws IOException {
        
        String str = "Und ich duese, duese, duese, duese im Sauseschritt...";
        String compressedStr = GZipTool.gzip(str);
        assertEquals(str, GZipTool.ungzip(compressedStr));
    }
}
