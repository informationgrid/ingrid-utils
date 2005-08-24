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

import junit.framework.TestCase;

public class IngridDocumentTest extends TestCase {

    public void testGetValues() throws Exception {
        String aId = "aId";
        String content = "content";
        IngridDocument document = new IngridDocument("aId", "content");
        assertEquals(aId, document.get(IngridDocument.DOCUMENT_ID));
        assertEquals(content, document.get(IngridDocument.DOCUMENT_CONTENT));
        document.put("foo", "bar");
        assertEquals("bar", document.get("foo"));
    }

    public void testGetter() throws Exception {
        Integer id = new Integer(Integer.MAX_VALUE);
        Integer content = new Integer(Integer.MIN_VALUE);
        IngridDocument document = new IngridDocument(id, content);
        assertEquals(document.getId(), id);
        assertEquals(document.getContent(), content);
    }

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
            // TODO: handle exception
        }

    }

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
