package de.ingrid.utils;

import java.util.Iterator;
import java.util.Vector;

import de.ingrid.utils.IngridQueryTools;
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
        
        query = QueryStringParser.parse("(t:[20 TO 30] OR t:[40 TO 50]) OR (b:[2000 TO 2006] OR b:[1939 TO 1945] AND (wasser))))");
        assertTrue(tools.hasRanges(query));
        assertTrue(tools.hasTerms(query));
        
    }
    
    /***/
    public void testRemoveClauses() throws Exception {
        IngridQuery query = QueryStringParser.parse("c AND a OR (b NOT (weg)) or (a NOT (weg NOT (wegweg)))");
        //System.out.println(query.toString());
        IngridQuery query2 = tools.removeClauses(query, true, true);
        //System.out.println(query2.toString());
    }
    
    /***/
    public void testGetTerms() throws Exception {
        IngridQuery query = QueryStringParser.parse("(a AND b) OR (c OR d OR f AND (a:1) OR c AND (a AND a:2))");

        Vector v = tools.getTermsAsVector(query, false, false);
        Iterator i = v.iterator();
        assertEquals("c", ((TermQuery) i.next()).getTerm());
        assertEquals("d", ((TermQuery) i.next()).getTerm());
        assertEquals("f", ((TermQuery) i.next()).getTerm());       
        
        v = tools.getTermsAsVector(query, false, true);
        i = v.iterator();
        assertEquals("a", ((TermQuery) i.next()).getTerm());
        assertEquals("b", ((TermQuery) i.next()).getTerm());
        assertEquals("a", ((TermQuery) i.next()).getTerm());
    }
    
    /***/
    public void testGetFields() throws Exception {
        IngridQuery query = QueryStringParser.parse("(t:f OR t:4) OR (b:a OR b:a OR (b:c) OR ((a:i) AND (wasser))))");
        
        Vector v = tools.getFieldsAsVector(query, false, true);
        Iterator i = v.iterator();
        assertEquals("t", ((FieldQuery) i.next()).getFieldName());
        assertEquals("b", ((FieldQuery) i.next()).getFieldName());     
        
        v = tools.getFieldsAsVector(query, false, true);
        i = v.iterator();
        assertEquals("t", ((FieldQuery) i.next()).getFieldName());
        assertEquals("b", ((FieldQuery) i.next()).getFieldName());
        assertEquals("a", ((FieldQuery) i.next()).getFieldName());
        assertEquals("b", ((FieldQuery) i.next()).getFieldName());
        
        query = QueryStringParser.parse("(t:f OR t:4) OR (b:a OR b:a OR (b:c) NOT ((a:i) AND (wasser))))");
        v = tools.getFieldsAsVector(query, false, false);
        i = v.iterator();
        assertEquals("t", ((FieldQuery) i.next()).getFieldName());
        assertEquals("b", ((FieldQuery) i.next()).getFieldName());
    }
    
    
    /***/
    public void testGetWildCards() throws Exception {
    	
        IngridQuery query = QueryStringParser.parse("(t:f* OR t:4*) OR (b:a* OR b:a* OR (b:c*) OR ((a:i*) AND (wasser*))))");
        
        Vector v = tools.getWildCardsAsVector(query);
        Iterator i = v.iterator();
        assertEquals("t", ((WildCardFieldQuery) i.next()).getFieldName());
        assertEquals("t", ((WildCardFieldQuery) i.next()).getFieldName()); 
        assertEquals("b", ((WildCardFieldQuery) i.next()).getFieldName()); 
        assertEquals("b", ((WildCardFieldQuery) i.next()).getFieldName()); 
        assertEquals("a", ((WildCardFieldQuery) i.next()).getFieldName()); 
       
         
        
    }
    
    
    /***/
    public void testGetRanges() throws Exception {
    	
        IngridQuery query = QueryStringParser.parse("(t:[20 TO 30] OR t:[40 TO 50]) OR (b:[2000 TO 2006] OR b:[1939 TO 1945] AND (wasser))))");
        
        Vector v = tools.getRangesAsVector(query);
        Iterator i = v.iterator();
        assertEquals("t", ((RangeQuery) i.next()).getRangeName());
        assertEquals("t", ((RangeQuery) i.next()).getRangeName());
        assertEquals("b", ((RangeQuery) i.next()).getRangeName());
        assertEquals("b", ((RangeQuery) i.next()).getRangeName());
       
       
    } 
    
    
    /***/
    public void testGetClauses() throws Exception {
        IngridQuery query = QueryStringParser.parse("A AND (A AND B) AND ((B AND C AND (C AND D)))");
        Vector v = tools.getClausesAsVector(query, false, true);
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
        IngridQuery query = QueryStringParser.parse("(a AND b OR (t1:gesucht AND (e OR t2:gesucht)) AND c) OR (b) AND (a OR b OR t2:gesucht)");
        Vector v = tools.getFieldByKey(query, "t1");
        assertEquals(1, v.size());
        v = tools.getFieldByKey(query, "t2");
        assertEquals(2, v.size());
        v = tools.getFieldByKey(query, "t0");
        assertEquals(0, v.size());
    }
    
}
