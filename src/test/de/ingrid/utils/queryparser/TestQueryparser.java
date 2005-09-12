/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.queryparser;

import java.io.StringReader;

import junit.framework.TestCase;
import de.ingrid.utils.ClauseQuery;
import de.ingrid.utils.TermQuery;
import de.ingrid.utils.FieldQuery;
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

    /**
     * 
     * @throws Exception
     */
    public void testSimpleOr() throws Exception {
        String q = "hallo OR welt";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        testSimpleLogic(parser, QueryStringParserConstants.OR);
        q = "hallo || welt";
        parser = new QueryStringParser(new StringReader(q));
        testSimpleLogic(parser, QueryStringParserConstants.OR);
        
    }
    /**
     * 
     * @throws Exception
     */
    public void testSimpleAND() throws Exception {
        String q = "hallo && welt";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        testSimpleLogic(parser, QueryStringParserConstants.AND);
        q = "hallo AND welt";
        parser = new QueryStringParser(new StringReader(q));
        testSimpleLogic(parser, QueryStringParserConstants.AND);
    }
    
    public void testSimpleNot() throws Exception {
        String q = "hallo NOT welt";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        testSimpleLogic(parser, QueryStringParserConstants.NOT);
        q = "hallo !welt";
        parser = new QueryStringParser(new StringReader(q));
        testSimpleLogic(parser, QueryStringParserConstants.NOT);
        q = "hallo ! welt";
        parser = new QueryStringParser(new StringReader(q));
        testSimpleLogic(parser, QueryStringParserConstants.NOT);
    }
    
    private void testSimpleLogic(QueryStringParser parser, int logic) {
        Token token;
        for (int i = 0; i < 3; i++) {
            token = parser.getNextToken();
            if (i == 1) {
                assertEquals(logic, token.kind);
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
        testTerms(q.getTerms(), new String[]{"fische"}, new int[]{IngridQuery.AND});

        q = parse("fische frösche");
        testTerms(q.getTerms(), new String[]{"fische","frösche"}, new int[]{IngridQuery.AND, IngridQuery.AND});
        
        q = parse("fische frösche ort:Halle");
        testTerms(q.getTerms(), new String[]{"fische", "frösche"}, new int[]{IngridQuery.AND, IngridQuery.AND});
        testFields(q.getFields(), new String[]{"ort:Halle"});
        
        q = parse("fische frösche ort:Halle land:germany");
        testTerms(q.getTerms(), new String[]{"fische", "frösche"}, new int[]{IngridQuery.AND, IngridQuery.AND});
        testFields(q.getFields(), new String[]{"ort:Halle", "land:germany"});

        q = parse("fische OR frösche");
        testTerms(q.getTerms(), new String[]{"fische","frösche"}, new int[]{IngridQuery.AND, IngridQuery.OR});
        
        q = parse("fische AND frösche");
        testTerms(q.getTerms(), new String[]{"fische","frösche"}, new int[]{IngridQuery.AND, IngridQuery.AND});
        
        q = parse("(ort:Halle land:germany) fische frösche ");
        testTerms(q.getTerms(), new String[]{"fische", "frösche"}, new int[]{IngridQuery.AND, IngridQuery.AND});
        assertEquals(1, q.getClauses().length);
        testFields(q.getClauses()[0].getFields(), new String[]{"ort:Halle", "land:germany"});
    }
    
    private static void testTerms(TermQuery[] termQuery, String[] terms, int[] operations){
        assertEquals(terms.length, termQuery.length);
        for (int i = 0; i < termQuery.length; i++) {
            assertEquals(termQuery[i].getTerm(), terms[i]);
            assertEquals(termQuery[i].getOperation(), operations[i]);
        }
    }
    private static void testFields(FieldQuery[] fieldQuery, String[] fields){
        assertEquals(fields.length, fieldQuery.length);
        for (int i = 0; i < fieldQuery.length; i++) {
            assertTrue(fields[i].startsWith(fieldQuery[i].getFieldName().concat(":")));
            assertTrue(":".concat(fields[i]).endsWith(fieldQuery[i].getFieldValue()));
        }
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

    public void testDataType() throws Exception {
        IngridQuery query = QueryStringParser.parse("datatype:news wetter ort:Berlin");
        System.out.println(query.getDescription());
        assertEquals("news", query.getDataType());
    }
}
