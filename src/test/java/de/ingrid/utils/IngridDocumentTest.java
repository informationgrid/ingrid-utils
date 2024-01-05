/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
public class IngridDocumentTest {

    /**
     * @throws Exception
     */
    @Test
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
    @Test
    public void testGetList() throws Exception {
        String key=new String();
        IngridDocument document = new IngridDocument("aId", "content");
        document.addToList(key,"1");
        document.addToList(key,"2");
        List<Object> list=document.getArrayList(key);
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
    @Test
    public void testGetter() throws Exception {
        Integer id = Integer.valueOf(Integer.MAX_VALUE);
        Integer content = Integer.valueOf(Integer.MIN_VALUE);
        IngridDocument document = new IngridDocument(id, content);
        assertEquals(document.getId(), id);
        assertEquals(document.getContent(), content);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSerializbaleCheck() throws Exception {
        Integer id = Integer.valueOf(Integer.MAX_VALUE);
        Integer content = Integer.valueOf(Integer.MIN_VALUE);
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
    @Test
    public void testReadAndWrite() throws Exception {
        // write
        Integer id = Integer.valueOf(Integer.MAX_VALUE);
        Integer content = Integer.valueOf(Integer.MIN_VALUE);
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
