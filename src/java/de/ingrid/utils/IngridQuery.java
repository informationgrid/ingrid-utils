/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.util.ArrayList;

/**
 * Container for Ingrid Query
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class IngridQuery extends IngridDocument {

  
    public static final int NOT = -1;

    public static final int AND = 0;

    public static final int OR = 1;

    public static final int TERM = 2;

    public static final int FIELD = 3;

    public static final int CLAUSE = 4;

    private ArrayList fTerms = new ArrayList();

    private ArrayList fFields = new ArrayList();

    private ArrayList fClauses = new ArrayList();

    private int fRequired;

    private int fType;


    /**
     * Default constructor
     */
    public IngridQuery() {
        super(new Long(System.currentTimeMillis()), null);
    }

    /**
     * Constructor for subclasses
     * @param type
     * @param required
     * @param query
     */
    public IngridQuery(int type, int required, String query) {
        super(new Long(System.currentTimeMillis()), query);
        this.fType = type;
        this.fRequired = required;
    }

    
    /**
     * @return the query type
     */
    public int getType() {
        return this.fType;
    }
    /**
     * @return the boolean operation type of this query
     */
    public int getOperation() {
        return this.fRequired;
    }

    /**
     * Adds a field query
     * @param field
     */
    public void addField(FieldQuery field) {
        this.fFields.add(field);
    }

    /**
     * @return array of field queries 
     */
    public FieldQuery[] getFields() {
        return (FieldQuery[]) this.fFields.toArray(new FieldQuery[this.fFields.size()]);
    }

    /**
     * Adds a term Query
     * @param term
     */
    public void addTerm(TermQuery term) {
        this.fTerms.add(term);
    }

    /**
     * @return array of term queries
     */
    public TermQuery[] getTerms() {
        return (TermQuery[]) this.fTerms.toArray(new TermQuery[this.fTerms.size()]);

    }

    /**
     * adds a clause query
     * @param clauseQuery
     */
    public void addClause(ClauseQuery clauseQuery) {
        this.fClauses.add(clauseQuery);
    }

    /**
     * @return array of clause queries
     */
    public ClauseQuery[] getClauses() {
        return (ClauseQuery[]) this.fClauses.toArray(new ClauseQuery[this.fClauses.size()]);

    }
    /**
     * @return a description of this query object
     */
    public String getDescription() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("(");
        buffer.append(" terms: ");
        appendToString(buffer, getTerms());
        buffer.append(" fields: ");
        appendToString(buffer, getFields());
        buffer.append(" clauses: ");
        IngridQuery[] clauses = getClauses();
        for (int i = 0; i < clauses.length; i++) {
            buffer.append(clauses[i].getDescription());

        }
        buffer.append(")");
        return buffer.toString();

    }
    /**
     * @param buffer
     * @param terms
     */
    private void appendToString(StringBuffer buffer, IngridQuery[] terms) {
        for (int i = 0; i < terms.length; i++) {
            buffer.append(terms[i]);
            buffer.append(" ");
        }
    }
   
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return (String) getContent();
    }
}
