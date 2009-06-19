/**
 * 
 */
package de.ingrid.utils.udk;

import junit.framework.TestCase;

/**
 * @author joachim
 *
 */
public class UtilsStringTest extends TestCase {

	/**
	 * Test method for {@link de.ingrid.utils.udk.UtilsString#transformNumberStrToIGCNumber(java.lang.String)}.
	 */
	public void testTransformNumberStrToIGCNumber() {
		assertEquals("2.2", UtilsString.transformNumberStrToIGCNumber("2,2"));
		assertEquals("67.4", UtilsString.transformNumberStrToIGCNumber("67,4"));
		assertEquals("67.4", UtilsString.transformNumberStrToIGCNumber("67.4"));
		assertEquals("6743546566.444", UtilsString.transformNumberStrToIGCNumber("6743546566.444"));
		assertEquals("6743546566", UtilsString.transformNumberStrToIGCNumber("6743546566"));
		assertEquals("1000.00", UtilsString.transformNumberStrToIGCNumber("1,000.00"));
		assertEquals("1000.00", UtilsString.transformNumberStrToIGCNumber("1.000,00"));
		assertEquals("1000000.00", UtilsString.transformNumberStrToIGCNumber("1.000.000,00"));
		assertEquals("1000000.00", UtilsString.transformNumberStrToIGCNumber("1,000,000.00"));
		assertEquals("10000000.00", UtilsString.transformNumberStrToIGCNumber("10,000,000.00"));
		assertEquals("1000", UtilsString.transformNumberStrToIGCNumber("1.000"));
		assertEquals("1000000", UtilsString.transformNumberStrToIGCNumber("1.000.000"));
		assertEquals("10000000", UtilsString.transformNumberStrToIGCNumber("10.000.000"));
		assertEquals("abccvcv,sd.", UtilsString.transformNumberStrToIGCNumber("abccvcv,sd."));
	}

}
