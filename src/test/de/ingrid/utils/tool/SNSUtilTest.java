package de.ingrid.utils.tool;

import junit.framework.TestCase;

public class SNSUtilTest extends TestCase {

    /*
     * Test method for 'de.ingrid.utils.tool.SNSUtil.transformSpacialReference(String)'
     */
    public void testTransformSpacialReference() {
        assertEquals("12345890", SNSUtil.transformSpacialReference("GEMEINDE1234567890"));
        assertEquals("12", SNSUtil.transformSpacialReference("BUNDESLAND12"));
        assertEquals("12345", SNSUtil.transformSpacialReference("KREIS12345"));
        assertEquals("SCHUTZGEBIET170", SNSUtil.transformSpacialReference("SCHUTZGEBIET170"));
    }

}
