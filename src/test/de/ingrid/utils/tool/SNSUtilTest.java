package de.ingrid.utils.tool;

import junit.framework.TestCase;

public class SNSUtilTest extends TestCase {

    /*
     * Test method for 'de.ingrid.utils.tool.SNSUtil.transformSpacialReference(String)'
     */
    public void testTransformSpacialReference() {
        assertEquals("1234567", SNSUtil.transformSpacialReference("ags:", "ags:1234567"));
        assertEquals("BUNDESLAND12", SNSUtil.transformSpacialReference("ags:", "BUNDESLAND12"));
        assertEquals("KREIS12345", SNSUtil.transformSpacialReference("ags:", "KREIS12345"));
        assertEquals("SCHUTZGEBIET170", SNSUtil.transformSpacialReference("ags:", "SCHUTZGEBIET170"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "rs:1234567890"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "rs:1234567890 ags:1234567"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "ags:1234567 rs:1234567890"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference("rs:", "ags:1234567 rs:1234567890 bla:12345678901"));
        assertEquals("1234567890", SNSUtil.transformSpacialReference(null, "rs:1234567890"));
        assertEquals("1234567", SNSUtil.transformSpacialReference(null, "ags:1234567 rs:1234567890"));
        
    }

}
