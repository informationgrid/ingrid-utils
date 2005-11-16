/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;

import java.util.ArrayList;

import de.ingrid.utils.IngridDocument;

/**
 * Container for Ingrid Queries
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class IngridQuery extends IngridDocument {

    /***/
    public static final int NOT = -1;

    /***/
    public static final int AND = 0;

    /***/
    public static final int OR = 1;

    /***/
    public static final int TERM = 2;

    /***/
    public static final int FIELD = 3;

    /***/
    public static final int CLAUSE = 4;

    private static final String TYPE = "type";

    private static final String REQUIRED = "required";

    private static final String FIELD_KEY = "field";

    private static final String TERM_KEY = "term";

    private static final String CLAUSE_KEY = "clause";

    private static final String DATA_TYPE = "dataType";

    /**
     * Default constructor
     */
    public IngridQuery() {
        super(new Long(System.currentTimeMillis()), null);
    }

    /**
     * Constructor for subclasses
     * 
     * @param type
     * @param required
     * @param query
     */
    public IngridQuery(int type, int required, String query) {
        super(new Long(System.currentTimeMillis()), query);
        putInt(TYPE, type);
        putInt(REQUIRED, required);
    }

    /**
     * @return the query type
     */
    public int getType() {
        return getInt(TYPE);
    }

    /**
     * @return the boolean operation type of this query
     */
    public int getOperation() {
        return getInt(REQUIRED);
    }

    /**
     * Adds a field query
     * 
     * @param field
     */
    public void addField(FieldQuery field) {
        addToList(FIELD_KEY, field);
    }

    /**
     * @return array of field queries
     */
    public FieldQuery[] getFields() {
        ArrayList arrayList = getArrayList(FIELD_KEY);
        if (arrayList == null) {
            return new FieldQuery[0];
        }
        return (FieldQuery[]) arrayList.toArray(new FieldQuery[arrayList.size()]);
    }

    /**
     * Adds a term Query
     * 
     * @param term
     */
    public void addTerm(TermQuery term) {
        addToList(TERM_KEY, term);
    }

    /**
     * @return array of term queries
     */
    public TermQuery[] getTerms() {
        ArrayList arrayList = getArrayList(TERM_KEY);
        if (arrayList == null) {
            return new TermQuery[0];
        }
        return (TermQuery[]) arrayList.toArray(new TermQuery[arrayList.size()]);

    }

    /**
     * adds a clause query
     * 
     * @param clauseQuery
     */
    public void addClause(ClauseQuery clauseQuery) {
        addToList(CLAUSE_KEY, clauseQuery);
    }

    /**
     * @return array of clause queries
     */
    public ClauseQuery[] getClauses() {
        ArrayList arrayList = getArrayList(CLAUSE_KEY);
        if (arrayList == null) {
            return new ClauseQuery[0];
        }
        return (ClauseQuery[]) arrayList.toArray(new ClauseQuery[arrayList.size()]);

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
            buffer.append(terms[i].getContent());
            buffer.append(" ");
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getDescription();
    }

    /**
     * @return the data type of this query
     */
    public String getDataType() {
        return (String) get(DATA_TYPE);
    }

    /**
     * Sets the data type
     * 
     * @param dataType
     */
    public void setDataType(String dataType) {
        put(DATA_TYPE, dataType);
    }

}
