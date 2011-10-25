/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.queryparser;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.List;

import junit.framework.TestCase;
import de.ingrid.utils.IngridDocument;
import de.ingrid.utils.query.ClauseQuery;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.query.TermQuery;

/**
 * Test cases for {@link QueryStringParser} created on 21.07.2005
 * <p>
 * 
 * @author hs
 */

public class QueryStringParserTest extends TestCase {

    /**
     * 
     * @throws Exception
     */
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

    public void testSimpleAndOr() throws Exception {
        String q = "ameise AND fische OR wasser";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        Token token = parser.getNextToken();
        assertEquals(QueryStringParserConstants.TERM, token.kind);
        token = parser.getNextToken();
        assertEquals(QueryStringParserConstants.AND, token.kind);
        token = parser.getNextToken();
        assertEquals(QueryStringParserConstants.TERM, token.kind);
        token = parser.getNextToken();
        assertEquals(QueryStringParserConstants.OR, token.kind);
        token = parser.getNextToken();
        assertEquals(QueryStringParserConstants.TERM, token.kind);
        IngridQuery query = null;
        try {
            query = QueryStringParser.parse(q);
        } catch (ParseException e) {
            e.printStackTrace();
            fail();
        }
        TermQuery[] terms = query.getTerms();
        assertTrue(terms[0].isRequred());
        assertTrue(terms[1].isRequred());
        assertFalse(terms[2].isRequred());
    }
    
    public void testComplexClauses() throws Exception {
        String q = "ameise AND (fische OR wasser) OR (luft AND erde OR feuer)";
        IngridQuery query = QueryStringParser.parse(q);
        IngridQuery[] allClauses = query.getAllClauses();
        assertEquals(3, allClauses.length);
        
        assertTrue(allClauses[0].isRequred());
        TermQuery[] terms = allClauses[0].getTerms();
        assertEquals(2, terms.length);
        assertEquals("fische", terms[0].getTerm());
        assertEquals("wasser", terms[1].getTerm());
        assertFalse(terms[0].isRequred());
        assertFalse(terms[1].isRequred());
        
        assertFalse(allClauses[1].isRequred());
        terms = allClauses[1].getTerms();
        assertEquals(3, terms.length);
        assertEquals("luft", terms[0].getTerm());
        assertEquals("erde", terms[1].getTerm());
        assertEquals("feuer", terms[2].getTerm());
        assertTrue(terms[0].isRequred());
        assertTrue(terms[1].isRequred());
        assertFalse(terms[2].isRequred());
        
        terms = allClauses[2].getTerms();
        assertEquals(1, terms.length);
        assertEquals("ameise", terms[0].getTerm());
        assertTrue(terms[0].isRequred());

        q = "ameise OR (fische OR wasser) AND (luft OR erde AND feuer)";
        query = QueryStringParser.parse(q);
        allClauses = query.getAllClauses();
        assertEquals(3, allClauses.length);
        
        assertTrue(allClauses[0].isRequred());
        TermQuery[] terms2 = allClauses[0].getTerms();
        assertEquals(2, terms2.length);
        assertEquals("fische", terms2[0].getTerm());
        assertEquals("wasser", terms2[1].getTerm());
        assertFalse(terms2[0].isRequred());
        assertFalse(terms2[1].isRequred());
        
        assertTrue(allClauses[1].isRequred());
        terms = allClauses[1].getTerms();
        assertEquals(3, terms.length);
        assertEquals("luft", terms[0].getTerm());
        assertEquals("erde", terms[1].getTerm());
        assertEquals("feuer", terms[2].getTerm());
        assertFalse(terms[0].isRequred());
        assertTrue(terms[1].isRequred());
        assertTrue(terms[2].isRequred());
        
        terms = allClauses[2].getTerms();
        assertEquals(1, terms.length);
        assertEquals("ameise", terms[0].getTerm());
        assertFalse(terms[0].isRequred());
    }
    
    /**
     * 
     * @throws Exception
     */
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

    /**
     * 
     * @throws Exception
     */
    public void testFields() throws Exception {
        String q = "field:Value";
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        Token token;
        for (int i = 0; i < 1; i++) {
            token = parser.getNextToken();
            assertEquals(QueryStringParserConstants.FIELD, token.kind);
        }
    }

    /**
     * @throws Exception
     */
    public void testPartnerFields() throws Exception {
        IngridQuery query = QueryStringParser.parse("query partner:bw partner:he");
        assertEquals(2, query.getPositivePartner().length);
        // query = QueryStringParser.parse("query (partner:bw partner:he)");
        // assertEquals(2, query.getPositivePartner().length);

        query = QueryStringParser.parse("query");
        query.addField(new FieldQuery(true, false, "partner", "bw"));
        query.addField(new FieldQuery(true, false, "partner", "he"));
        assertEquals(2, query.getPositivePartner().length);
    }

    /**
     * 
     * @throws Exception
     */
    public void testQueries() throws Exception {
        IngridQuery q = parse("fische");
        testTerms(q.getTerms(), new String[] { "fische" }, new boolean[][] { { true, false } });

        q = parse("fische froesche");
        testTerms(q.getTerms(), new String[] { "fische", "froesche" }, new boolean[][] { { true, false },
                { true, false } });

        q = parse("fische froesche ort:Halle");
        testTerms(q.getTerms(), new String[] { "fische", "froesche" }, new boolean[][] { { true, false },
                { true, false } });
        testFields(q.getFields(), new String[] { "ort:Halle" });

        q = parse("fische froesche ort:Halle land:germany");
        testTerms(q.getTerms(), new String[] { "fische", "froesche" }, new boolean[][] { { true, false },
                { true, false } });
        testFields(q.getFields(), new String[] { "ort:Halle", "land:germany" });

        q = parse("fische OR froesche");
        testTerms(q.getTerms(), new String[] { "fische", "froesche" }, new boolean[][] { { false, false },
                { false, false } });

        q = parse("fische AND froesche");
        testTerms(q.getTerms(), new String[] { "fische", "froesche" }, new boolean[][] { { true, false },
                { true, false } });

        q = parse("(ort:Halle land:germany) fische froesche ");
        testTerms(q.getTerms(), new String[] { "fische", "froesche" }, new boolean[][] { { true, false },
                { true, false } });
        assertEquals(1, q.getClauses().length);
        testFields(q.getClauses()[0].getFields(), new String[] { "ort:Halle", "land:germany" });
    }

    private static void testTerms(TermQuery[] termQuery, String[] terms, boolean[][] booleans) {
        assertEquals(terms.length, termQuery.length);
        for (int i = 0; i < termQuery.length; i++) {
            assertEquals(termQuery[i].getTerm(), terms[i]);
            assertEquals(termQuery[i].isRequred(), booleans[i][0]);
            assertEquals(termQuery[i].isProhibited(), booleans[i][1]);
        }
    }

    private static void testFields(FieldQuery[] fieldQuery, String[] fields) {
        assertEquals(fields.length, fieldQuery.length);
        for (int i = 0; i < fieldQuery.length; i++) {
            assertTrue(fields[i].startsWith(fieldQuery[i].getFieldName()));
            assertTrue(":".concat(fields[i]).endsWith(fieldQuery[i].getFieldValue()));
        }
    }

    /**
     * 
     * @param q
     * @return The parsed {@link IngridQuery}
     * @throws ParseException
     */
    private IngridQuery parse(String q) throws ParseException {
        QueryStringParser parser = new QueryStringParser(new StringReader(q));
        IngridQuery query = parser.parse();
        assertNotNull(query);

        return query;
    }

    /**
     * 
     * @throws Exception
     */
    public void testStaticQueryParsing() throws Exception {
        IngridQuery query = QueryStringParser.parse("a query");
        assertNotNull(query);
    }

    /**
     * 
     * @throws Exception
     */
    public void testDataType() throws Exception {
        IngridQuery query = QueryStringParser.parse("datatype:news wetter ort:Berlin");
        assertEquals("news", query.getPositiveDataTypes()[0]);
    }

    /**
     * @throws Exception
     */
    public void testMoreQueries() throws Exception {
        IngridQuery query = QueryStringParser.parse("ort:Halle OR ort:Darmstadt");
        assertEquals(2, query.getFields().length);
        query = QueryStringParser.parse("ort:Halle AND t0:1990");
        assertEquals(2, query.getFields().length);

        query = QueryStringParser.parse("fische ort:halle NOT (saale OR Hufeisensee)");
        assertEquals(1, query.getTerms().length);
        assertEquals(1, query.getFields().length);
        assertEquals(1, query.getClauses().length);
    }

    /**
     * @throws Exception
     */
    public void testWildCardFieldQueries() throws Exception {
        IngridQuery query = QueryStringParser.parse("ort:Ha*lle  ort:Darmst?dt");
        assertEquals(2, query.getWildCardFieldQueries().length);
         query = QueryStringParser.parse("ort:Hal*  ort:Darmstad?");
        assertEquals(2, query.getWildCardFieldQueries().length);
    }
    
    /**
     * @throws Exception
     */
    public void testWildCardTermQueries() throws Exception {
        IngridQuery query = QueryStringParser.parse("wa*sser");
        assertEquals(1, query.getWildCardTermQueries().length);
        query = QueryStringParser.parse("wa?sser");
        assertEquals(1, query.getWildCardTermQueries().length);
    }
    
    
    /**
     * @throws Exception
     */
    public void testFuzzyTerms() throws Exception {
        IngridQuery query=QueryStringParser.parse("query");
        assertEquals(0, query.getFuzzyTermQueries().length);
        assertEquals(1, query.getTerms().length);
         query=QueryStringParser.parse("query~");
        assertEquals(1, query.getFuzzyTermQueries().length);
        assertEquals(0, query.getTerms().length);
    }
    
    /**
     * @throws Exception
     */
    public void testFuzzyFields() throws Exception {
        IngridQuery query=QueryStringParser.parse("f:field");
        assertEquals(0, query.getFuzzyFieldQueries().length);
        assertEquals(1, query.getFields().length);
         query=QueryStringParser.parse("f:field~");
        assertEquals(1, query.getFuzzyFieldQueries().length);
        assertEquals(0, query.getTerms().length);
    }

    /**
     * @throws Exception
     */
    public void testCoordinateQueries() throws Exception {

        IngridQuery query = QueryStringParser.parse("x1:31.0");

        assertEquals("Query contains no fields.", 1, query.getFields().length);
        assertEquals("The expected field 'x1:31.0' does not exist.", "x1:31.0", query.getFields()[0].getContent()
                .toString());

        query = QueryStringParser.parse("x1:-31.0");

        assertEquals("Query contains no fields.", 1, query.getFields().length);
        assertEquals("The expected field 'x1:-31.0' does not exist.", "x1:-31.0", query.getFields()[0].getContent()
                .toString());
    }

    /**
     * @throws Exception
     */
    public void testRanked() throws Exception {
        assertTrue(QueryStringParser.parse("bla ranking:score").isScoreRanked());
        assertTrue(QueryStringParser.parse("bla ranking:date").isDateRanked());
        assertTrue(QueryStringParser.parse("bla ranking:off").isNotRanked());
    }

    /**
     * @throws Exception
     */
    public void testPhrase() throws Exception {
        String qSt = "\"halle saale\"";
        IngridQuery query = QueryStringParser.parse(qSt);
        assertEquals(1, query.getTerms().length);
        qSt = "ort:\"halle saale\"";
        query = QueryStringParser.parse(qSt);
        assertEquals(1, query.getFields().length);
    }

    /**
     * @throws Exception
     */
    public void testRangeQuery() throws Exception {
        String qSt = "date:[12 TO 23]";
        IngridQuery query = QueryStringParser.parse(qSt);
        assertEquals(1, query.getRangeQueries().length);

        qSt = "foo:[1 TO 2]";
        query = QueryStringParser.parse(qSt);
        assertEquals(1, query.getRangeQueries().length);
    }

    /**
     * @throws Exception
     */
    public void testMoreRangeQueries() throws Exception {
        String qSt = "time:[9 TO 5] AND ( date:[12 TO 23] OR date:[25 TO 30] )";
        IngridQuery query = QueryStringParser.parse(qSt);
        assertEquals(1, query.getClauses().length);
    }

    /**
     * @throws Exception
     */
    public void testBooleanFieldQueries() throws Exception {
        String q = "aa field:value";
        IngridQuery query = QueryStringParser.parse(q);
        assertEquals(1, query.getFields().length);

        q = "aa -field:value";
        query = QueryStringParser.parse(q);
        assertEquals(1, query.getFields().length);
    }

    /**
     * @throws Exception
     */
    public void testOr() throws Exception {
        IngridQuery query = QueryStringParser.parse("wasser OR erde");
        assertEquals(query.getTerms().length, 2);
        assertEquals(query.getTerms()[0].isRequred(), false);
        assertEquals(query.getTerms()[1].isRequred(), false);

        query = new IngridQuery();
        query.addTerm(new TermQuery(true, false, "wasser"));
        ClauseQuery cq = new ClauseQuery(true, false);
        cq.addField(new FieldQuery(false, false, "type", "value1"));
        cq.addField(new FieldQuery(false, false, "type", "value2"));
        query.addClause(cq);
    }

    /**
     * @throws Exception
     */
    public void testOrWithClause() throws Exception {
        IngridQuery query = QueryStringParser.parse("(wasser OR feuer) OR erde");
        for (int i = 0; i < query.getTerms().length; i++) {
            assertFalse(query.getTerms()[i].isRequred());
        }
        for (int i = 0; i < query.getClauses().length; i++) {
            assertFalse(query.getClauses()[i].isRequred());
        }
    }

    /**
     * @throws Exception
     */
    public void testOrWithSingleClause() throws Exception {
        IngridQuery query = QueryStringParser.parse("a OR (-b)");
        assertFalse(query.getTerms()[0].isRequred());
        assertFalse(query.getTerms()[0].isProhibited());
        assertFalse(query.getTerms()[1].isRequred());
        assertTrue(query.getTerms()[1].isProhibited());
    }

    /**
     * @throws Exception
     */
    public void testNOT() throws Exception {
        IngridQuery query = QueryStringParser.parse("b NOT b");
        assertTrue(query.getTerms()[0].isRequred());
        assertFalse(query.getTerms()[0].isProhibited());
        assertTrue(query.getTerms()[1].isRequred());
        assertTrue(query.getTerms()[1].isProhibited());
    }

    /**
     * @throws Exception
     */
    public void testParserVsApi() throws Exception {
        String s = "wasser +datatype:topics +(topic:gentechnik topic:abfall) ";
        IngridQuery q1 = QueryStringParser.parse(s);
        IngridQuery q2 = new IngridQuery();
        q2.addTerm(new TermQuery(true, false, "wasser"));
        q2.addField(new FieldQuery(true, false, "datatype", "topics"));
        ClauseQuery cq = new ClauseQuery(true, false);
        cq.addField(new FieldQuery(false, false, "topic", "gentechnik"));
        cq.addField(new FieldQuery(false, false, "topic", "abfall"));
        q2.addClause(cq);
        assertEquals(q1.toString(), q2.toString());

    }

    /**
     * @throws Exception
     */
    public void testStarngeClauses() throws Exception {
        IngridQuery query = QueryStringParser.parse("( foo )");
        assertEquals(1, query.getTerms().length);
    }

    /**
     * @throws Exception
     */
    public void testTermsFieldsWITH_QUOTING() throws Exception {
        String q = "\"hallo  welt\" feld:\"/dsc_other:df\"";
        
        IngridQuery iq = QueryStringParser.parse(q);
        TermQuery[] t = iq.getTerms();
        assertEquals(1,t.length);
        for (int i = 0; i < t.length; i++) {
            assertEquals("hallo  welt", t[i].getTerm());
        }
        
        FieldQuery[] f = iq.getFields();
        assertEquals(1, f.length);
        for (int i = 0; i < f.length; i++) {
            assertEquals("/dsc_other:df", f[i].getFieldValue());
        }
    }
    
    public void testParseProviderName() throws Exception {
        IngridQuery query = QueryStringParser.parse("http provider:ni_lk-row");
        assertEquals("ni_lk-row", query.getPositiveProvider()[0]);
    }
    
    /**
     * @throws Exception
     */
    public void testTermsFieldsAndTermsWithSlash() throws Exception {
        String q = "hallo/welt feld:\"/kug-group:kug-iplug-sns\"";
        
        IngridQuery iq = QueryStringParser.parse(q);
        TermQuery[] t = iq.getTerms();
        assertEquals(1,t.length);
        for (int i = 0; i < t.length; i++) {
            assertEquals("hallo welt", t[i].getTerm());
        }
        
        FieldQuery[] f = iq.getFields();
        assertEquals(1, f.length);
        for (int i = 0; i < f.length; i++) {
            assertEquals("/kug-group:kug-iplug-sns", f[i].getFieldValue());
        }
    }
    
    public void testUTF8Terms() throws Exception {
    	String q = "Начало";
        try {
            QueryStringParser parser = new QueryStringParser(new ByteArrayInputStream(q.getBytes("UTF-8")), "UTF-8");
            Token token;
	        while ((token = parser.getNextToken()) != null) {
	            if (token.kind == QueryStringParserConstants.EOF) {
	                break;
	            }
	            assertEquals(QueryStringParserConstants.TERM, token.kind);
	        }
        } catch (Exception e ) {
        	fail("No UTF-8 support in QueryParser!");
        }
        try {
	        IngridQuery query = QueryStringParser.parse(q);
	        TermQuery[] terms = query.getTerms();
			assertTrue(terms[0].getTerm().equals(q));
        } catch (Exception e ) {
        	fail("No UTF-8 support in QueryParser!");
        }
    }
    
    public void testTermsFieldsAndTermsWithUrl() throws Exception {
        String q = "\"hallo/welt\" feld:\"http://www.wemove.com/index.html\"";
        
        IngridQuery iq = QueryStringParser.parse(q);
        TermQuery[] t = iq.getTerms();
        assertEquals(1,t.length);
        for (int i = 0; i < t.length; i++) {
            assertEquals("hallo welt", t[i].getTerm());
        }
        
        FieldQuery[] f = iq.getFields();
        assertEquals(1, f.length);
        for (int i = 0; i < f.length; i++) {
            assertEquals("http://www.wemove.com/index.html", f[i].getFieldValue());
        }
    }

    @SuppressWarnings("unchecked")
    public void testFacetFields() throws Exception {
        String q = "wasser #:#[{\"key\":\"value\"}]#";
        
        IngridQuery iq = QueryStringParser.parse(q);
        assertEquals("value", ((List<IngridDocument>)iq.get("FACETS")).get(0).get("key"));

        try {
            iq = QueryStringParser.parse("#:[{\"key\":\"value\"}]");
            fail("Facets must be quoted!");
        } catch (Throwable e) { }

        try {
            iq = QueryStringParser.parse("#:[{\"key\":\"#value\"}]");
            fail("'#' are not allowed!");
        } catch (Throwable e) { }
        
        iq = QueryStringParser.parse("http #:#[{\"id\":\"partner\", \"classes\":[{\"id\":\"bund\"}]}]#");
        assertEquals("partner", ((List<IngridDocument>)iq.get("FACETS")).get(0).get("id"));
        assertEquals("bund", ((List<IngridDocument>)((List<IngridDocument>)iq.get("FACETS")).get(0).get("classes")).get(0).get("id"));
        
        
    }
    
    
}
