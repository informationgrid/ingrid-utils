package de.ingrid.utils.tool;

import junit.framework.TestCase;

public class SNSUtilTest extends TestCase {

    /*
     * Test method for 'de.ingrid.utils.tool.SNSUtil.transformSpacialReference(String)'
     */
    public void testTransformSpacialReference() {
        assertEquals("1234567", SNSUtil.transformSpacialReference("ags:1234567"));
        assertEquals("BUNDESLAND12", SNSUtil.transformSpacialReference("BUNDESLAND12"));
        assertEquals("KREIS12345", SNSUtil.transformSpacialReference("KREIS12345"));
        assertEquals("SCHUTZGEBIET170", SNSUtil.transformSpacialReference("SCHUTZGEBIET170"));
    }

}
