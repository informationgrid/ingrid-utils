/*
 * Copyright (c) 1997-2007 by wemove GmbH
 */
package de.ingrid.utils.udk;

import java.io.InputStream;
import java.util.HashMap;

import de.ingrid.utils.xml.XMLSerializer;

/**
 * TODO Describe your created type (class, etc.) here.
 *
 * @author joachim@wemove.com
 */
public class UtilsUDKCodeLists {

    private static final HashMap codeLists;

    static {
        try {
            // Create the SessionFactory
            InputStream resourceAsStream = UtilsUDKCodeLists.class.getResourceAsStream("udk_codelists_serialized.xml");
            XMLSerializer serializer = new XMLSerializer();
            codeLists = (HashMap) serializer.deSerialize(resourceAsStream);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static String getCodeListEntryName(Long codeListId, Long domainId, Long langId) {
        return (String)((HashMap)((HashMap)codeLists.get(codeListId)).get(domainId)).get(langId);
    }

    
    
}
