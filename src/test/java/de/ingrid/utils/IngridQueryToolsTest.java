/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2020 wemove digital solutions GmbH
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

import java.util.Iterator;
import java.util.Vector;

import de.ingrid.utils.IngridQueryTools;
import de.ingrid.utils.query.ClauseQuery;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.query.RangeQuery;
import de.ingrid.utils.query.TermQuery;
import de.ingrid.utils.query.WildCardFieldQuery;
import de.ingrid.utils.queryparser.QueryStringParser;
import junit.framework.TestCase;

/***/
public class IngridQueryToolsTest extends TestCase {

    IngridQueryTools tools = null;

    /***/
    protected void setUp() throws Exception {
        tools = new IngridQueryTools();
    }

    /***/
    public void testHasMethods() throws Exception {
        IngridQuery query = QueryStringParser.parse("fisch AND t01:test OR (hund OR katze)");
        assertTrue(tools.hasTerms(query));
        assertTrue(tools.hasFieldQueries(query));
        assertTrue(tools.hasSubClauses(query));

        query = QueryStringParser.parse("fisch");
        assertFalse(tools.hasFieldQueries(query));
        assertFalse(tools.hasSubClauses(query));

        query = QueryStringParser.parse("c:1");
        assertFalse(tools.hasTerms(query));
        assertTrue(tools.hasFieldQueries(query));

        query = QueryStringParser.parse("(t:f AND t:4) OR (b:a AND b:a OR (b:c) OR ((a:i) AND (wasser)))");
        assertTrue(tools.hasTerms(query));

        query = QueryStringParser.parse("(a AND b) OR (c OR d OR f AND (a:1) OR d AND (a AND a:2))");
        assertTrue(tools.hasFieldQueries(query));
        assertTrue(tools.hasTerms(query));

        query = QueryStringParser.parse("(t:f* OR t:4*) OR (b:a* OR b:a* OR (b:c*) OR ((a:i*) AND (wasser))))");
        assertTrue(tools.hasWildCards(query));
        assertTrue(tools.hasTerms(query));

        query = QueryStringParser
                .parse("(t:[20 TO 30] OR t:[40 TO 50]) OR (b:[2000 TO 2006] OR b:[1939 TO 1945] AND (wasser))))");
        assertTrue(tools.hasRanges(query));
        assertTrue(tools.hasTerms(query));

    }

    /***/
    public void testRemoveClauses() throws Exception {
        IngridQuery query = QueryStringParser.parse("c AND a OR (b NOT (weg)) or (a NOT (weg NOT (wegweg)))");
        // System.out.println(query.toString());
        tools.removeClauses(query, true, true);
        // System.out.println(query2.toString());
    }

    /***/
    public void testGetTerms() throws Exception {
        IngridQuery query = QueryStringParser.parse("(a AND b) OR (c OR d OR f AND (a:1) OR c AND (a AND a:2))");

        Vector<TermQuery> v = tools.getTermsAsVector(query, false, false);
        Iterator<TermQuery> i = v.iterator();
        assertEquals("c", i.next().getTerm());
        assertEquals("d", i.next().getTerm());
        assertEquals("f", i.next().getTerm());

        v = tools.getTermsAsVector(query, false, true);
        i = v.iterator();
        assertEquals("a", i.next().getTerm());
        assertEquals("b", i.next().getTerm());
        assertEquals("a", i.next().getTerm());
    }

    /**
     * @throws Exception
     */
    public void testGetFields() throws Exception {
        IngridQuery query = QueryStringParser.parse("(t:f OR t:4) OR (b:a OR b:a OR (b:c) OR ((a:i) AND (wasser))))");

        Vector<FieldQuery> v = this.tools.getFieldsAsVector(query, false, true);
        Iterator<FieldQuery> i = v.iterator();
        assertEquals("a", ((FieldQuery) i.next()).getFieldName());
        assertFalse(i.hasNext());

        query = QueryStringParser.parse("(t:f OR t:4) OR (b:a OR b:a OR (b:c) NOT ((a:i) AND (wasser))))");
        v = this.tools.getFieldsAsVector(query, false, false);
        i = v.iterator();
        assertEquals("t", i.next().getFieldName());
        assertEquals("t", i.next().getFieldName());
        assertEquals("b", i.next().getFieldName());
        assertEquals("b", i.next().getFieldName());
        assertEquals("b", i.next().getFieldName());
        assertFalse(i.hasNext());
    }

    /**
     * @throws Exception */
    public void testGetWildCards() throws Exception {

        IngridQuery query = QueryStringParser
                .parse("(t:f* OR t:4*) OR (b:a* OR b:a* OR (b:c*) OR ((a:i*) AND (wasser*))))");

        Vector<IngridQuery> v = this.tools.getWildCardsAsVector(query);
        Iterator<IngridQuery> i = v.iterator();
        assertEquals(6, v.size());
        assertEquals("t", ((WildCardFieldQuery) i.next()).getFieldName());
        assertEquals("t", ((WildCardFieldQuery) i.next()).getFieldName());
        assertEquals("a", ((WildCardFieldQuery) i.next()).getFieldName());
        assertEquals("b", ((WildCardFieldQuery) i.next()).getFieldName());
        assertEquals("b", ((WildCardFieldQuery) i.next()).getFieldName());
        assertEquals("b", ((WildCardFieldQuery) i.next()).getFieldName());
        assertFalse(i.hasNext());
    }

    /***/
    public void testGetRanges() throws Exception {

        IngridQuery query = QueryStringParser
                .parse("(t:[20 TO 30] OR t:[40 TO 50]) OR (b:[2000 TO 2006] OR b:[1939 TO 1945] AND (wasser))))");

        Vector<RangeQuery> v = tools.getRangesAsVector(query);
        Iterator<RangeQuery> i = v.iterator();
        assertEquals("t", i.next().getRangeName());
        assertEquals("t", i.next().getRangeName());
        assertEquals("b", i.next().getRangeName());
        assertEquals("b", i.next().getRangeName());

    }

    /***/
    public void testGetClauses() throws Exception {
        IngridQuery query = QueryStringParser.parse("A AND (A AND B) AND ((B AND C AND (C AND D)))");
        Vector<ClauseQuery> v = tools.getClausesAsVector(query, false, true);
        assertEquals(3, v.size()); // (( ... )) is now handled as ( ...)
        v = tools.getClausesAsVector(query);
        assertEquals(3, v.size());

        query = QueryStringParser.parse("A OR (A AND B) AND ((B AND C OR (C AND D)))");
        v = tools.getClausesAsVector(query, false, true);
        assertEquals(1, v.size());

        query = QueryStringParser.parse("A OR (A OR B) OR ((B OR C OR (C OR D)))");
        v = tools.getClausesAsVector(query, false, false);
        assertEquals(2, v.size());

        query = QueryStringParser.parse("(A OR (A AND B) AND ((B AND C OR (C OR D))))");
        v = tools.getClausesAsVector(query, false, true);
        assertEquals(2, v.size());
        v = tools.getClausesAsVector(query);
        assertEquals(4, v.size());

        query = QueryStringParser.parse("A OR A AND B AND B AND C OR C OR D");
        v = tools.getClausesAsVector(query, false, true);
        assertEquals(0, v.size());
        v = tools.getClausesAsVector(query);
        assertEquals(0, v.size());
    }

    /***/
    public void testGetByName() throws Exception {
        IngridQuery query = QueryStringParser
                .parse("(a AND b OR (t1:gesucht AND (e OR t2:gesucht)) AND c) OR (b) AND (a OR b OR t2:gesucht)");
        Vector<FieldQuery> v = tools.getFieldByKey(query, "t1");
        assertEquals(1, v.size());
        v = tools.getFieldByKey(query, "t2");
        assertEquals(2, v.size());
        v = tools.getFieldByKey(query, "t0");
        assertEquals(0, v.size());
    }

}
