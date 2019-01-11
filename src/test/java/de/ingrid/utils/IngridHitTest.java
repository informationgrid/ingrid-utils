/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2019 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils;

import java.security.MessageDigest;
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

		ingridHit1.setDocumentId("foo");
		ingridHit2.setDocumentId("bar");
		assertFalse(ingridHit1.equals(ingridHit2));

		ingridHit1.setDocumentId("foo");
		ingridHit2.setDocumentId("foo");
		assertTrue(ingridHit1.equals(ingridHit2));
	}

	public void testHashcode() throws Exception {
		IngridHit ingridHit1 = new IngridHit();
		ingridHit1.setScore(1.0f);
		IngridHit ingridHit2 = new IngridHit();
		ingridHit2.setScore(2.0f);
		assertFalse(ingridHit1.hashCode() == ingridHit2.hashCode());

		ingridHit1.setDocumentId("foo");
		ingridHit2.setDocumentId("bar");
		assertFalse(ingridHit1.hashCode() == ingridHit2.hashCode());

		ingridHit1.setDocumentId("foo");
		ingridHit2.setDocumentId("foo");
		assertEquals(ingridHit1.hashCode(), ingridHit2.hashCode());
	}
	
	public void testHashcodeDoc() throws Exception {
        IngridHit ingridHit1 = new IngridHit();
        IngridHit ingridHit2 = new IngridHit();
        
        String id1 = "AVAfEoX2iQXAcup3Hmwg";
        String id2 = "AVAfEoX2iQXAcup3HmxH";
        
        ingridHit1.setDocumentId( id1 );
        ingridHit2.setDocumentId( id2 );
        
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(ingridHit1.getDocumentId().getBytes());
        String encryptedId1 = new String(messageDigest.digest());
        MessageDigest messageDigest2 = MessageDigest.getInstance("SHA-256");
        messageDigest2.update(ingridHit2.getDocumentId().getBytes());
        String encryptedId2 = new String(messageDigest.digest());
        
        // the normal hashCode-function has a collision
        assertTrue( id1.hashCode() == id2.hashCode() );

        // but the new function shouldn't have that!
        assertFalse( encryptedId1.equals( encryptedId2 ) );
        assertFalse( encryptedId1.hashCode() == encryptedId2.hashCode() );
    }

	public void testContains() throws Exception {
		IngridHit ingridHit1 = new IngridHit();
		ingridHit1.setScore(1.0f);
		IngridHit ingridHit2 = new IngridHit();
		ingridHit2.setScore(2.0f);
		Set<IngridHit> set = new LinkedHashSet<IngridHit>();
		set.add(ingridHit1);
		set.add(ingridHit2);
		assertEquals(2, set.size());
		assertTrue(set.contains(ingridHit1));
		assertTrue(set.contains(ingridHit2));

		ingridHit1.setDocumentId("foo");
		ingridHit2.setDocumentId("bar");
		set.clear();
		set.add(ingridHit1);
		set.add(ingridHit2);
		assertEquals(2, set.size());
		assertTrue(set.contains(ingridHit1));
		assertTrue(set.contains(ingridHit2));

		ingridHit1.setDocumentId("foo");
		ingridHit2.setDocumentId("foo");
		set.clear();
		assertTrue(set.add(ingridHit1));
		assertFalse(set.add(ingridHit2));
		assertEquals(1, set.size());
		assertTrue(set.contains(ingridHit1));

	}

	

}
