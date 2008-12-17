package de.ingrid.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.query.ClauseQuery;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.query.RangeQuery;
import de.ingrid.utils.query.TermQuery;
import de.ingrid.utils.query.WildCardFieldQuery;
import de.ingrid.utils.query.WildCardTermQuery;

/**
 * This class is used for checking incomming <code>IngridQuery</code>s. Furthermore
 * there are some methods for manipulation Querys.
 * 
 * @author nbeck
 */
public class IngridQueryTools {

    /**
     * The logging object
     */
    private static Log log = LogFactory.getLog(de.ingrid.utils.IngridQueryTools.class);
    
    /**
     * Checks whether <code>termQuery</code>s exists in the <code>query</code>. 
     * If <code>subClause</code>s are found, the search will be continued 
     * recursivly in all clauses. If at least one term was found, <code>true</code>
     * will be returned. In every other case the result will be <code>false</code>. 
     * 
     * @param query Query to check
     * @return <code>true</code> if at least one <code>termQuery</code> was found
     */
    public boolean hasTerms(final IngridQuery query) {
        boolean found = false;
        for (int i = 0; i < query.getClauses().length; i++) {
            found |= hasTerms(query.getClauses()[i]);
            if (found) break;
        }       
        return found || query.getTerms().length > 0;
    }
    
    /**
     * Checks whether <code>fieldQuery</code>s exists in the <code>query</code>. 
     * If <code>subClause</code>s are found, the search will be continued 
     * recursivly in all clauses. If at least one field was found, <code>true</code>
     * will be returned. In every other case the result will be <code>false</code>. 
     * 
     * @param query Query to check
     * @return <code>true</code> if at least one <code>fieldQuery</code> was found
     */
    public boolean hasFieldQueries(final IngridQuery query) {
        boolean found = false;
        for (int i = 0; i < query.getClauses().length; i++) {
            found |= hasFieldQueries(query.getClauses()[i]);
            if (found) break;
        }       
        return found || query.getFields().length > 0;
    }
    
    
    /**
     * Checks whether wildCardQueries exists in the <code>query</code>. 
     * If <code>subClause</code>s are found, the search will be continued 
     * recursivly in all clauses. If at least one wildcard was found, <code>true</code>
     * will be returned. In every other case the result will be <code>false</code>. 
     * 
     * @param query Query to check
     * @return <code>true</code> if at least one wildCardQuery was found
     */
    public boolean hasWildCards(final IngridQuery query) {
    	return hasWildCardTerms(query) || hasWildCardFields(query);
    }
    
    
    /**
     * Checks whether <code>WildCardTermQuery</code>s exists in the <code>query</code>. 
     * If <code>subClause</code>s are found, the search will be continued 
     * recursivly in all clauses. If at least one wildCardTermQuery was found, <code>true</code>
     * will be returned. In every other case the result will be <code>false</code>. 
     * 
     * @param query Query to check
     * @return <code>true</code> if at least one <code>WildCardTermQuery</code> was found
     */
    public boolean hasWildCardTerms(final IngridQuery query) {
        boolean found = false;
        for (int i = 0; i < query.getClauses().length; i++) {
            found |= hasWildCardTerms(query.getClauses()[i]);
            if (found) break;
        }       
        return found || query.getWildCardTermQueries().length > 0;
    }
    
    /**
     * Checks whether <code>WildCardFieldQuery</code>s exists in the <code>query</code>. 
     * If <code>subClause</code>s are found, the search will be continued 
     * recursivly in all clauses. If at least one wildCardFieldQuery was found, <code>true</code>
     * will be returned. In every other case the result will be <code>false</code>. 
     * 
     * @param query Query to check
     * @return <code>true</code> if at least one <code>WildCardFieldQuery</code> was found
     */
    public boolean hasWildCardFields(final IngridQuery query) {
        boolean found = false;
        for (int i = 0; i < query.getClauses().length; i++) {
            found |= hasWildCardFields(query.getClauses()[i]);
            if (found) break;
        }       
        return found || query.getWildCardFieldQueries().length > 0;
    }
    
    
    
    /**
     * Checks whether <code>rangeQuery</code>s exists in the <code>query</code>. 
     * If <code>subClause</code>s are found, the search will be continued 
     * recursivly in all clauses. If at least one range was found, <code>true</code>
     * will be returned. In every other case the result will be <code>false</code>. 
     * 
     * @param query Query to check
     * @return <code>true</code> if at least one <code>rangeQuery</code> was found
     */
    public boolean hasRanges(final IngridQuery query) {
        boolean found = false;
        for (int i = 0; i < query.getClauses().length; i++) {
            found |= hasRanges(query.getClauses()[i]);
            if (found) break;
        }       
        return found || query.getRangeQueries().length > 0;
    }
    
    
    
    
    /**
     * Checks whether <code>aubClauses</code>s exists in the <code>query</code>. If,
     * <code>true</code> will be returned. In every other case the result will
     * be <code>false</code>.
     * 
     * @param query Query to check
     * @return <code>true</code> if at least one <code>subClause</code> was found
     */
    public boolean hasSubClauses(final IngridQuery query) {
        return query.getClauses().length > 0;
    }
    
    /**
     * Returns the <code>FieldQuery</code>(s) specified by the <code>key</code>. 
     * If the key was not found or in case of an other error, a empty 
     * <code>Vector</code> or <code>null</code> will be returned.
     *  
     * @param query The query object to search in
     * @param key The key to search for
     * @return <code>Vector</code> with specified <code>FieldQuery</code>(s)
     */
    public Vector getFieldByKey(final IngridQuery query, final String key) {
        Vector v = new Vector();
        for (int i = 0; i < query.getClauses().length; i++) {
            Vector res = getFieldByKey(query.getClauses()[i], key);
            if (res != null) {
                v.addAll(res);
            }
        }
        FieldQuery[] fields = query.getFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getFieldName().equalsIgnoreCase(key)) {
                v.add(fields[i]);
            }
        }
        return v;
    }
    
    /**
     * Returns the <code>FieldQuery</code>(s) specified by the <code>key</code> 
     * array. If the keys was not found or in case of an other error, a empty 
     * <code>Vector</code> or <code>null</code> will be returned.
     *  
     * @param query The query object to search in
     * @param key[] The keys to search for
     * @return <code>Vector</code> with specified <code>FieldQuery</code>(s)
     */
    public Vector getFieldsByKeys(final IngridQuery query, final String[] key) {
    	Vector v = new Vector();
        for (int i = 0; i < query.getClauses().length; i++) {
            Vector res = getFieldsByKeys(query.getClauses()[i], key);
            if (res != null) {
                v.addAll(res);
            }
        }
        String current = "";
        FieldQuery[] fields = query.getFields();
        for (int i = 0; i < fields.length; i++) {
            current = fields[i].getFieldName();
            for (int j = 0; j < key.length; j++) {
	        	if (current.equalsIgnoreCase(key[j])) {
	                v.add(fields[i]);
	            }
            }
        }
        return v;
    }
    
    /**
     * Returns a <code>String</code> array including all field values
     * specified by the <code>key</code>. If the key was not found or 
     * in case of an other error, a <code>String[]</code> array of 
     * lenght 1 will be returned. <code>String[0]</code> will contain
     * the placeholder value in <code>ph</code>.
     *  
     * @param query The query object to search in
     * @param key The key to search for
     * @param ph Placeholder as default value, if no matching fields where found
     * @return <code>String[]</code> with field values
     */
    public String[] getFieldValueByKey(final IngridQuery query, final String key, final String ph) {
        Vector v = getFieldByKey(query, key);
        if (v.size() == 0) {
        	String[] s = new String[1];
        	s[0] = ph;
        	return s;
        }
        String[] s = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            s[i] = ((FieldQuery) v.get(i)).getFieldValue();
        }
        return s;
    }
    
    /**
     * Returns a <code>String</code> array including all field values
     * specified by the <code>key</code>. If the key was not found or 
     * in case of an other error, a empty <code>String[]</code> will
     * be returned.
     *  
     * @param query The query object to search in
     * @param key The key to search for
     * @return <code>String[]</code> with field values
     */
    public String[] getFieldValueByKey(final IngridQuery query, final String key) {
        Vector v = getFieldByKey(query, key);
        String[] s = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            s[i] = ((FieldQuery) v.get(i)).getFieldValue();
        }
        return s;
    }
    
    /**
     * Returns a <code>String</code> array including all field values
     * specified by the <code>key</code> array. If the key was not found  
     * or in case of an other error, an empty <code>String[]</code> will
     * be returned. The result is unsorted - values will be listed as
     * found in the query.
     *  
     * @param query The query object to search in
     * @param key[] The keys to search for
     * @return <code>String[]</code> with field values
     */
    public String[] getFieldValuesByKeys(final IngridQuery query, final String[] key) {
        Vector v = getFieldsByKeys(query, key);
        String[] s = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            s[i] = ((FieldQuery) v.get(i)).getFieldValue();
        }
        return s;
    }
    
    /**
     * Returns a <code>Hashtable</code> with all fields out of the
     * <code>query</code>. If there are no Fields in the Query, an
     * empty <code>Hashtable</code> will be returned. 
     * 
     * @param query The Query to extract the fields of
     * @return All found fields as <code>Hashtable</code>
     */
    public Hashtable getFieldsAsHashtable(final IngridQuery query) {
    	Hashtable ht = new Hashtable();
    	Vector fields = getFieldsAsVector(query);
    	FieldQuery fq = null;
    	for (Enumeration e = fields.elements(); e.hasMoreElements(); ) {
    		fq = (FieldQuery)e.nextElement();
    		ht.put(fq.getFieldName(), fq.getFieldValue());
    	}
    	return ht;
    }
    
    /**
     * Returns a <code>Vector</code> including all <code>TermQuery</code>s that match
     * the given conditions, described by <code>prohibited</code> and <code>required</code>.
     * 
     * @param query The query to get the terms from
     * @param prohibited Condition one
     * @param required Condition two
     * @return <code>Vector</code> with extracted <code>TermQuery</code>
     */
    public Vector getTermsAsVector(final IngridQuery query, final boolean prohibited, final boolean required) {
        Vector extracted = new Vector();
        
        // look for recurivly found terms to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getTermsAsVector(query.getClauses()[i], prohibited, required));
        }
        
        // add terms of the current clause
        TermQuery[] terms = query.getTerms();
        for (int i = 0; i < terms.length; i++) {
            if ((terms[i].isProhibited() == prohibited) && (terms[i].isRequred() == required)) {
                extracted.add(terms[i]);
            }
        }        
        return extracted;
    }
    
    /**
     * Returns a <code>String[]</code> including all terms that match
     * the given conditions, described by <code>prohibited</code> and
     * <code>required</code>.
     * 
     * @param query The query to get the terms from
     * @param prohibited Condition one
     * @param required Condition two
     * @return <code>String[]</code> with extracted <code>TermQuery</code>
     */
    public String[] getTerms(final IngridQuery query, final boolean prohibited, final boolean required) {
        Vector v = getTermsAsVector(query, prohibited, required);
        String[] s = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            s[i] = ((TermQuery) v.get(i)).getTerm();
        }
        return s;
    }
    
    /**
     * Returns a <code>Vector</code> including all <code>TermQuery</code>s
     * including sub clauses.
     * 
     * @param query The query to get the terms from
     * @return <code>Vector</code> with extracted <code>TermQuery</code>
     */
    public Vector getTermsAsVector(final IngridQuery query) {
        Vector extracted = new Vector();
        
        // look for recurivly found terms to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getTermsAsVector(query.getClauses()[i]));
        }
        
        // add terms of the current clause
        TermQuery[] terms = query.getTerms();
        for (int i = 0; i < terms.length; i++) {
            extracted.add(terms[i]);
        }        
        return extracted; 
    }
    
    /**
     * Returns a <code>String[]</code> including all terms
     * including sub clauses.
     * 
     * @param query The query to get the terms from
     * @return <code>String[]</code> with extracted <code>TermQuery</code>
     */
    public String[] getTerms(final IngridQuery query) {
        Vector v = getTermsAsVector(query);
        String[] s = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            s[i] = ((TermQuery) v.get(i)).getTerm();
        }
        return s;
    }
    
    /**
     * Returns a <code>Vector</code> including all <code>FieldQuery</code>s that match
     * the given conditions, described by <code>prohibited</code> and <code>required</code>.
     * 
     * @param query The query to get the fields from
     * @param prohibited Condition one
     * @param required Condition two
     * @return <code>Vector</code> with extracted <code>FiledQuery</code>
     */
    public Vector getFieldsAsVector(final IngridQuery query, final boolean prohibited, final boolean required) {
        Vector extracted = new Vector();
        
        // look for recurivly found fields to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getFieldsAsVector(query.getClauses()[i], prohibited, required));
        }
        
        // add fields of the current clause
        FieldQuery[] fields = query.getFields();
        for (int i = 0; i < fields.length; i++) {
            if ((fields[i].isProhibited() == prohibited) && (fields[i].isRequred() == required)) {
                extracted.add(fields[i]);
            }
        }        
        return extracted;
    }   
    
    /**
     * Returns a <code>Vector</code> including all <code>FieldQuery</code>s
     * including sub clauses.
     * 
     * @param query The query to get the fields from
     * @return <code>Vector</code> with extracted <code>FiledQuery</code>
     */
    public Vector getFieldsAsVector(final IngridQuery query) {
        Vector extracted = new Vector();
        
        // look for recurivly found fields to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getFieldsAsVector(query.getClauses()[i]));
        }
        
        // add fields of the current clause
        FieldQuery[] fields = query.getFields();
        for (int i = 0; i < fields.length; i++) {
            extracted.add(fields[i]);
        }        
        return extracted;
    }   
    
    
    /**
     * Returns a <code>Vector</code> including all WildCardQueries
     * including sub clauses.
     * 
     * @param query The query to get the wildcardqueries from
     * @return <code>Vector</code> with extracted WildCardQuery
     */
    public Vector getWildCardsAsVector(final IngridQuery query) {
     
    	Vector extracted = new Vector();
        
    	extracted.addAll(getWildCardTermsAsVector(query));
    	extracted.addAll(getWildCardFieldsAsVector(query));
     
        return extracted; 
    }
    
    
    /**
     * Returns a <code>Vector</code> including all <code>WildCardFieldQuery</code>s
     * including sub clauses.
     * 
     * @param query The query to get the wildcardfieldqueries from
     * @return <code>Vector</code> with extracted <code>WildCardFieldQuery</code>
     */
    public Vector getWildCardFieldsAsVector(final IngridQuery query) {
        Vector extracted = new Vector();
        
        // look for recurivly found terms to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getWildCardFieldsAsVector(query.getClauses()[i]));
        }
        
        // add terms of the current clause
        WildCardFieldQuery[] wildcardfields = query.getWildCardFieldQueries();
        for (int i = 0; i < wildcardfields.length; i++) {
            extracted.add(wildcardfields[i]);
        }        
        return extracted; 
    }
    
    
    /**
     * Returns a <code>Vector</code> including all <code>WildCardTermQuery</code>s
     * including sub clauses.
     * 
     * @param query The query to get the wildcardtermqueries from
     * @return <code>Vector</code> with extracted <code>WildCardTermQuery</code>
     */
    public Vector getWildCardTermsAsVector(final IngridQuery query) {
        Vector extracted = new Vector();
        
        // look for recurivly found terms to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getWildCardTermsAsVector(query.getClauses()[i]));
        }
        
        // add terms of the current clause
        WildCardTermQuery[] wildcardterms = query.getWildCardTermQueries();
        for (int i = 0; i < wildcardterms.length; i++) {
            extracted.add(wildcardterms[i]);
        }        
        return extracted; 
    }
    
    
    
    /**
     * Returns a <code>Vector</code> including all <code>RangeQuery</code>s
     * including sub clauses.
     * 
     * @param query The query to get the rangequeries from
     * @return <code>Vector</code> with extracted <code>RangeQuery</code>
     */
    public Vector getRangesAsVector(final IngridQuery query) {
        Vector extracted = new Vector();
        
        // look for recurivly found terms to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getRangesAsVector(query.getClauses()[i]));
        }
        
        // add terms of the current clause
        RangeQuery[] ranges = query.getRangeQueries();
        for (int i = 0; i < ranges.length; i++) {
            extracted.add(ranges[i]);
        }        
        return extracted; 
    }
    
    
    /**
     * Returns a <code>Vector</code> including all <code>Clause</code>s that match
     * the given conditions, described by <code>prohibited</code> and <code>required</code>.
     * 
     * @param query The query to get the clauses from
     * @param prohibited Condition one
     * @param required Condition two
     * @return <code>Vector</code> with extracted <code>ClauseQuery</code>
     */
    public Vector getClausesAsVector(final IngridQuery query, final boolean prohibited, final boolean required) {
    	Vector extracted = new Vector();
        
        // look for recurivly found clauses to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getClausesAsVector(query.getClauses()[i], prohibited, required));
        }
        
        // add clauses of the current clause
        ClauseQuery[] clauses = query.getClauses();
        for (int i = 0; i < clauses.length; i++) {
            if ((clauses[i].isProhibited() == prohibited) && (clauses[i].isRequred() == required)) {
                extracted.add(clauses[i]);
                
            }
        }        
        return extracted;
    }
    
    /**
     * Returns a <code>Vector</code> including all <code>ClauseQuery</code>s
     * including sub clauses.
     * 
     * @param query The query to get the clauses from
     * @return <code>Vector</code> with extracted <code>ClauseQuery</code>
     */
    public Vector getClausesAsVector(final IngridQuery query) {
        Vector extracted = new Vector();
        
        // looking for recurivly found clauses to add to the result
        for (int i = 0; i < query.getClauses().length; i++) {
            extracted.addAll(getClausesAsVector(query.getClauses()[i]));
        }
        
        // add fields of the current clause
        ClauseQuery[] clauses = query.getClauses();
        for (int i = 0; i < clauses.length; i++) {
            extracted.add(clauses[i]);
        }        
        return extracted;
    } 
    
    /**
     * Removes all terms, fields or full sub clauses, that match to both conditions
     * passed by <code>prohibited</code> and <code>required</code>. the cleaned
     * up <code>IngridQuery</code> will be returned. 
     * 
     * @param query The query to clean up
     * @param prohibited Condition one
     * @param required Condition two
     * @return A cleaned new <code>IngridQuery</code>
     */
    public IngridQuery removeClauses(final IngridQuery query, final boolean prohibited, final boolean required) {   
        for (int i = 0; i < query.getClauses().length; i++) {
            ClauseQuery[] clauses = query.getClauses();
            // TODO: Condition correct?
            if ((clauses[i].isProhibited() == prohibited) && (clauses[i].isRequred() == required)) {
                log.debug("Removing clause: " + clauses[i].toString());
                query.removeClause(query.getClauses()[i]);
            } else {
                removeClauses(query.getClauses()[i], prohibited, required);
            } 
        }        
        return query;
    }
    
    /**
     * Returns a <code>Vector</code> including only <code>ClauseQuery</code>s
     * without sub clauses.
     * 
     * @param query The query to get the clauses from
     * @return <code>Vector</code> with extracted <code>ClauseQuery</code>
     */
    public Vector getLevelClauses(final IngridQuery query) {
        Vector extracted = new Vector();
        
        // add fields of the current clause
        ClauseQuery[] clauses = query.getClauses();
        for (int i = 0; i < clauses.length; i++) {
            extracted.add(clauses[i]);
        }        
        return extracted;
    } 
    
}



