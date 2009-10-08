package de.ingrid.utils.query;

import junit.framework.TestCase;

public class FieldQueryTest extends TestCase {

	public void testCollisoion() throws Exception {
		FieldQuery fieldQuery1 = new FieldQuery(true, false, "partner", "bw");
		FieldQuery fieldQuery2 = new FieldQuery(true, false, "partner", "be");
		assertFalse(fieldQuery1.hashCode() == fieldQuery2.hashCode());

		fieldQuery1 = new FieldQuery(true, false, "partner", "be");
		fieldQuery2 = new FieldQuery(true, false, "partner", "be");
		assertTrue(fieldQuery1.hashCode() == fieldQuery2.hashCode());

	}
}
