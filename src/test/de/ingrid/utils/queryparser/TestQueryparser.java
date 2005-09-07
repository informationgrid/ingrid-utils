/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.queryparser;

import java.io.StringReader;

import junit.framework.TestCase;
import de.ingrid.utils.ClauseQuery;
import de.ingrid.utils.IngridQuery;

public class TestQueryparser extends TestCase {

    public void testQuery() throws Exception {
        IngridQuery query = new IngridQuery();
        query.addClause(new ClauseQuery(IngridQuery.AND));
        new IngridQuery(IngridQuery.TERM, IngridQuery.AND, "");
    }

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

    public void testQueries() throws Exception {
        IngridQuery q = parse("fische");
        assertEquals(1, q.getTerms().length);
        assertTrue("fische".equals(q.getTerms()[0].toString()));
        q = parse("fische frösche");
        assertEquals(2, q.getTerms().length);

        q = parse("fische frösche ort:Halle");
        assertEquals(2, q.getTerms().length);
        assertEquals(1, q.getFields().length);
        q = parse("fische frösche ort:Halle land:germany");
        assertEquals(2, q.getTerms().length);
        assertEquals(2, q.getFields().length);

        q = parse("fische OR frösche");
        assertEquals(2, q.getTerms().length);
        assertEquals(IngridQuery.OR, q.getTerms()[1].getOperation());

        q = parse("(ort:Halle land:germany) fische frösche ");
        System.out.println(q.getDescription());
        assertEquals(2, q.getTerms().length);
        assertEquals(1, q.getClauses().length);

    }

    private IngridQuery parse(String q) throws ParseException {
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        IngridQuery query = parser.parse();
        assertNotNull(query);

        return query;
    }

    public void testStaticQueryParsing() throws Exception {
        IngridQuery query = QueryStringParser.parse("a query");
        assertNotNull(query);
    }
}
