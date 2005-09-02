/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.queryparser;

import java.io.StringReader;

import de.ingrid.utils.QueryParser;
import de.ingrid.utils.QueryParserTest;
import junit.framework.TestCase;

public class TestQueryparser extends TestCase {

    public void testSimpleTerms() throws Exception {
        String q = "hallo  welt";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        Token token;
        while ((token = parser.getNextToken()) != null) {
            if (token.kind == QueryStringParserConstants.EOF) {
                break;
            }
            assertEquals(QueryStringParserConstants.TERM, token.kind);

        }
    }

    public void testSimpleOr() throws Exception {
        String q = "hallo OR welt";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        Token token;
        for (int i = 0; i < 3; i++) {
            token = parser.getNextToken();
            if (i == 1) {
                assertEquals(QueryStringParserConstants.OR, token.kind);
            } else {
                assertEquals(QueryStringParserConstants.TERM, token.kind);
            }

        }
    }

    public void testFields() throws Exception {
        String q = "field:Value";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        Token token;
        for (int i = 0; i < 1; i++) {
            token = parser.getNextToken();
            assertEquals(QueryStringParserConstants.FIELD, token.kind);
        }
    }

    public void testBrace() throws Exception {
        String q = "fische (halle OR magdegurg AND  ( Elbe OR Neise) AND FISCH:KARPFEN)";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        parser.parse();
        // // Token token;
        // // for (int i = 0; i < 3; i++) {
        // // token = parser.Parse();
        // // if (i == 1) {
        // // assertEquals(QueryStringParserConstants.OR, token.kind);
        // // } else {
        // // assertEquals(QueryStringParserConstants.TERM, token.kind);
        // // }
        // //
    }
}
