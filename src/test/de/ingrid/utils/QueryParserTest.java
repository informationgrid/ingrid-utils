/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import junit.framework.TestCase;

public class QueryParserTest extends TestCase {

    public void testParseSimpleQuer() throws Exception {
        String string = "Fische Saale";
        IngridQuey quey = QueryParser.parseQuery(string);
        assertNotNull(quey);
        assertEquals(string, quey.toString());
        assertEquals(2, quey.getToken().length);
    }

    public void testBrackets() throws Exception {
        String string1 = "Fische Halle (Saale OR Hufeisensee)";
        IngridQuey quey1 = QueryParser.parseQuery(string1);
        assertEquals(2, quey1.getSubQueries().length);
        assertEquals("Fische Halle", quey1.getSubQueries()[0].getContent());
        
        String string2 = "Fische Halle AND    (Saale OR Hufeisensee)";
        IngridQuey quey2 = QueryParser.parseQuery(string2);
        assertEquals(2, quey2.getSubQueries().length);

         String string3 = "Fische OR Halle AND (Saale OR Hufeisensee)";
         IngridQuey quey3 = QueryParser.parseQuery(string3);
         assertEquals(2, quey3.getSubQueries().length);
    }
}
