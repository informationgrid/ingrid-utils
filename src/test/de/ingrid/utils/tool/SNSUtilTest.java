package de.ingrid.utils.tool;

import junit.framework.TestCase;

public class SNSUtilTest extends TestCase {

    /*
     * Test method for 'de.ingrid.utils.tool.SNSUtil.transformSpacialReference(String)'
     */
    public void testTransformSpacialReference() {
        assertEquals("12345690", SNSUtil.transformSpacialReference("GEMEINDE1234567890"));
        assertEquals("12000000", SNSUtil.transformSpacialReference("LAND12"));
        assertEquals("SCHUTZGEBIET170", SNSUtil.transformSpacialReference("SCHUTZGEBIET170"));
    }

}
