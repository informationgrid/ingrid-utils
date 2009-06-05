/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import junit.framework.TestCase;

/**
 * Test for {@link de.ingrid.utils.IngridDocument}
 * 
 * <p/>created on 30.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class IngridDocumentTest extends TestCase {

    /**
     * @throws Exception
     */
    public void testGetValues() throws Exception {
        String aId = "aId";
        String content = "content";
        IngridDocument document = new IngridDocument("aId", "content");
        assertEquals(aId, document.get(IngridDocument.DOCUMENT_ID));
        assertEquals(content, document.get(IngridDocument.DOCUMENT_CONTENT));
        document.put("foo", "bar");
        assertEquals("bar", document.get("foo"));
    }
    
    /**
     * @throws Exception
     */
    public void testGetList() throws Exception {
        String key=new String();
        IngridDocument document = new IngridDocument("aId", "content");
        document.addToList(key,"1");
        document.addToList(key,"2");
        List list=document.getArrayList(key);
        assertTrue(list.contains("1"));
        assertTrue(list.contains("2"));
        
        //remove
        assertTrue(document.removeFromList(key,"1"));
        list=document.getArrayList(key);
        assertFalse(list.contains("1"));
        assertTrue(list.contains("2"));
        
        //removed all
        assertTrue(document.removeFromList(key,"2"));
        assertFalse(document.removeFromList(key,"2"));
        assertNull(list=document.getArrayList(key));
    }

    /**
     * @throws Exception
     */
    public void testGetter() throws Exception {
        Integer id = new Integer(Integer.MAX_VALUE);
        Integer content = new Integer(Integer.MIN_VALUE);
        IngridDocument document = new IngridDocument(id, content);
        assertEquals(document.getId(), id);
        assertEquals(document.getContent(), content);
    }

    /**
     * @throws Exception
     */
    public void testSerializbaleCheck() throws Exception {
        Integer id = new Integer(Integer.MAX_VALUE);
        Integer content = new Integer(Integer.MIN_VALUE);
        IngridDocument document = new IngridDocument(id, content);
        try {
            document.put(new Object(), null);
            fail("key must be serializbale");
        } catch (Exception e) {
            // right!
        }

        try {
            document.put("", new Object());
            fail("value must be serializable");
        } catch (Exception e) {
            //
        }

    }

    /**
     * @throws Exception
     */
    public void testReadAndWrite() throws Exception {
        // write
        Integer id = new Integer(Integer.MAX_VALUE);
        Integer content = new Integer(Integer.MIN_VALUE);
        IngridDocument writeDocument = new IngridDocument(id, content);
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayStream);
        writeDocument.put("foo", null);
        writeDocument.writeExternal(objectOutputStream);

        // read
        IngridDocument readedDocument = new IngridDocument();
        ByteArrayInputStream inStream = new ByteArrayInputStream(byteArrayStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        readedDocument.readExternal(objectInputStream);
        // test
        assertEquals(readedDocument.getId(), id);
        assertEquals(readedDocument.getContent(), content);
        assertNull(readedDocument.get("foo"));
    }
}
