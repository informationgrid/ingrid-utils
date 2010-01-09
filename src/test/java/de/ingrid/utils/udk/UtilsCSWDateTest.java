/**
 * 
 */
package de.ingrid.utils.udk;

import junit.framework.TestCase;

/**
 * @author joachim
 *
 */
public class UtilsCSWDateTest extends TestCase {

	public final void testIsCSWDate() {
		assertEquals(true, UtilsCSWDate.isCSWDate("20061012"));
		assertEquals(true, UtilsCSWDate.isCSWDate("2001-01-15T20:07:48.11"));
		assertEquals(true, UtilsCSWDate.isCSWDate("2001-01-15T20:07:48.11Z"));
	}
	
	
	public final void testGetDBDateStyle() {
		assertEquals("20061012", UtilsCSWDate.getQueryDateStyle("2006-10-12"));
		assertEquals("20061012", UtilsCSWDate.getQueryDateStyle("20061012T121247"));
	}
	
}
