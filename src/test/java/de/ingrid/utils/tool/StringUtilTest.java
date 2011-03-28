package de.ingrid.utils.tool;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

    public void testReplaceLineFeeds() {
    	String inString = "line1\nline2\rline3\n\r";
//    	System.out.println("testReplaceLineFeeds with String '" + inString + "'");
        assertEquals("line1line2line3", StringUtil.replaceLineFeeds(inString, ""));
        assertEquals("line1 line2 line3", StringUtil.replaceLineFeeds(inString, " "));
    }
}
