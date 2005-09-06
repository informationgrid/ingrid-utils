/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

/**
 * A Container for the query
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class IngridQuery extends IngridDocument {

    // public static final String Terms = "QueryToken";
    //
    // public static final String OR_QUERIES = "OrQueries";
    //
    // public static final String SUB_QUERIES = "SubQueries";

    public static final int NOT = -1;

    public static final int AND = 0;

    public static final int OR = 1;

    public static final int TERM = 2;

    public static final int FIELD = 3;

    public static final int CLAUSE = 4;

    private ArrayList fTerms = new ArrayList();

    private ArrayList fFields = new ArrayList();

    private ArrayList fClauses = new ArrayList();

    private int fOperation;

    private int fType;

    /**
     * Constructor
     * 
     * @param id
     * @param query
     */
    public IngridQuery(long id, final String query) {
        super(new Long(id), query);
    }

    public IngridQuery() {
        super(new Long(System.currentTimeMillis()), "");
    }

    public IngridQuery(int type, int booleanOperation, String query) {
        super(new Long(System.currentTimeMillis()), query);
        this.fType = type;
        this.fOperation = booleanOperation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return (String) getContent();
    }

    public void addField(IngridQuery field) {
        fFields.add(field);
    }

    public IngridQuery[] getFields() {
        return (IngridQuery[]) fFields.toArray(new IngridQuery[this.fFields.size()]);
    }

    public void addTerm(IngridQuery term) {
        fTerms.add(term);
    }

    public IngridQuery[] getTerms() {
        return (IngridQuery[]) fTerms.toArray(new IngridQuery[this.fTerms.size()]);

    }

    public void addClause(IngridQuery term) {
        fClauses.add(term);
    }

    public IngridQuery[] getClauses() {
        return (IngridQuery[]) fClauses.toArray(new IngridQuery[this.fClauses.size()]);

    }
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
    private void appendToString(StringBuffer buffer, IngridQuery[] terms) {
        for (int i = 0; i < terms.length; i++) {
            buffer.append(terms[i]);
            buffer.append(" ");
        }
    }
    public int getType() {
        return fType;
    }
    public int getOperation() {
        return fOperation;
    }
}
