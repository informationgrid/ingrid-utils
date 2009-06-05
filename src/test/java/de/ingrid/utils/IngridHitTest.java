package de.ingrid.utils;

import java.util.LinkedHashSet;
import java.util.Set;

import junit.framework.TestCase;

public class IngridHitTest extends TestCase {

	public void testEquals() throws Exception {
		IngridHit ingridHit1 = new IngridHit();
		ingridHit1.setScore(1.0f);
		IngridHit ingridHit2 = new IngridHit();
		ingridHit2.setScore(2.0f);
		assertFalse(ingridHit1.equals(ingridHit2));

		ingridHit1.setHitId("foo");
		ingridHit2.setHitId("bar");
		assertFalse(ingridHit1.equals(ingridHit2));

		ingridHit1.setHitId("foo");
		ingridHit2.setHitId("foo");
		assertTrue(ingridHit1.equals(ingridHit2));
	}

	public void testHashcode() throws Exception {
		IngridHit ingridHit1 = new IngridHit();
		ingridHit1.setScore(1.0f);
		IngridHit ingridHit2 = new IngridHit();
		ingridHit2.setScore(2.0f);
		assertFalse(ingridHit1.hashCode() == ingridHit2.hashCode());

		ingridHit1.setHitId("foo");
		ingridHit2.setHitId("bar");
		assertFalse(ingridHit1.hashCode() == ingridHit2.hashCode());

		ingridHit1.setHitId("foo");
		ingridHit2.setHitId("foo");
		assertEquals(ingridHit1.hashCode(), ingridHit2.hashCode());
	}

	public void testContains() throws Exception {
		IngridHit ingridHit1 = new IngridHit();
		ingridHit1.setScore(1.0f);
		IngridHit ingridHit2 = new IngridHit();
		ingridHit2.setScore(2.0f);
		Set set = new LinkedHashSet();
		set.add(ingridHit1);
		set.add(ingridHit2);
		assertEquals(2, set.size());
		assertTrue(set.contains(ingridHit1));
		assertTrue(set.contains(ingridHit2));

		ingridHit1.setHitId("foo");
		ingridHit2.setHitId("bar");
		set.clear();
		set.add(ingridHit1);
		set.add(ingridHit2);
		assertEquals(2, set.size());
		assertTrue(set.contains(ingridHit1));
		assertTrue(set.contains(ingridHit2));

		ingridHit1.setHitId("foo");
		ingridHit2.setHitId("foo");
		set.clear();
		assertTrue(set.add(ingridHit1));
		assertFalse(set.add(ingridHit2));
		assertEquals(1, set.size());
		assertTrue(set.contains(ingridHit1));

	}

	

}
