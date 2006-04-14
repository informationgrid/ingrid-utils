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

    private static final long serialVersionUID = 9L;

    /***/
    public static final int TERM = 2;

    /***/
    public static final int FIELD = 3;

    /***/
    public static final int CLAUSE = 4;

    /***/
    public static final int RANGE = 5;
    
    /***/
    public static final int WILDCARD = 6;

    private static final String TYPE = "type";

    private static final String REQUIRED = "required";

    private static final String FIELD_KEY = "field";

    private static final String TERM_KEY = "term";

    private static final String RANGE_KEY = "range";
    
    private static final String WILDCARD_KEY = "wildcard";

    private static final String CLAUSE_KEY = "clause";

    public static final String DATA_TYPE = "datatype";

    private static final String PROHIBITED = "prohibited";

    public static final String SCORE_RANKED = "score";

    public static final String RANKED = "ranking";

    public static final String DATE_RANKED = "date";

    public static final String NOT_RANKED = "off";

    private static final String GROUPED = "grouped";

    public static final String GROUPED_OFF = "grouped_off";

    public static final String GROUPED_BY_PLUGID = "grouped_by_plugId";

    public static final String GROUPED_BY_ORGANISATION = "grouped_by_organisation";

    public static final String GROUPED_BY_PARTNER = "grouped_by_partner";

    private static final String PROVIDER = "provider";

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
    public IngridQuery(boolean required, boolean prohibited, int type, String query) {
        super(new Long(System.currentTimeMillis()), query);
        putBoolean(REQUIRED, required);
        putBoolean(PROHIBITED, prohibited);
        putInt(TYPE, type);

    }

    public boolean isRequred() {
        return getBoolean(REQUIRED);
    }

    public boolean isProhibited() {
        return getBoolean(PROHIBITED);
    }

    /**
     * @return the query type
     */
    public int getType() {
        return getInt(TYPE);
    }

    /**
     * Adds a field query
     * 
     * @param field
     */
    public void addField(FieldQuery field) {
        if (field.getFieldName().equals(DATA_TYPE)) {
            addToList(DATA_TYPE, field);
        } else if (field.getFieldName().equals(RANKED)) {
            put(RANKED, field.getFieldValue().toLowerCase());
        } else if (field.getFieldName().equals(GROUPED)) {
            put(GROUPED, field.getFieldValue().toLowerCase());
        } else if (field.getFieldName().equals(PROVIDER)) {
            addToList(PROVIDER, field);
        } else {
            addToList(FIELD_KEY, field);
        }
    }

    /**
     * removes a fieldquery from the query
     * 
     * @param fieldQuery
     */
    public void removeField(FieldQuery fieldQuery) {
        ArrayList arrayList = getArrayList(FIELD_KEY);
        if (arrayList != null) {
            arrayList.remove(fieldQuery);
        }
    }

    /**
     * removes a data type from the query
     * 
     * @param fieldQuery
     */
    public void removeDataType(String dataType) {
        ArrayList arrayList = getArrayList(DATA_TYPE);
        if (arrayList != null) {
            int count = arrayList.size();
            for (int i = 0; i < count; i++) {
                FieldQuery fieldQuery = (FieldQuery) arrayList.get(i);
                if (fieldQuery.getFieldValue().toLowerCase().equals(dataType)) {
                    arrayList.remove(fieldQuery);
                    return;
                }
            }

        }
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
     * @param query
     */
    public void addRangeQuery(RangeQuery query){
        addToList(RANGE_KEY, query);
    }

    /**
     * @param query
     */
    public void addWildCardQuery(WildCardQuery query) {
      addToList(WILDCARD_KEY, query);
    }
    
    /**
     * @return an array of range queries
     */
    public RangeQuery[] getRangeQueries() {
        ArrayList arrayList = getArrayList(RANGE_KEY);
        if (arrayList == null) {
            return new RangeQuery[0];
        }
        return (RangeQuery[]) arrayList.toArray(new RangeQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public WildCardQuery[] getWildCardQueries() {
        ArrayList arrayList = getArrayList(WILDCARD_KEY);
        if (arrayList == null) {
            return new WildCardQuery[0];
        }
        return (WildCardQuery[]) arrayList.toArray(new WildCardQuery[arrayList.size()]);
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
     * Removes a <code>ClauseQuery</code> from the query
     * 
     * @param clauseQuery
     *            Clause to be removed
     */
    public void removeClause(ClauseQuery clauseQuery) {
        ArrayList arrayList = getArrayList(CLAUSE_KEY);
        if (arrayList != null) {
            arrayList.remove(clauseQuery);
        }
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
        buffer.append(" ranges: ");
        appendToString(buffer, getRangeQueries());

        buffer.append(" wildcard: ");
        appendToString(buffer, getWildCardQueries());

        buffer.append(" providers: ");
        ArrayList arrayList = getArrayList(PROVIDER);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        IngridQuery[] provider = (IngridQuery[]) arrayList.toArray(new IngridQuery[arrayList.size()]);
        appendToString(buffer, provider);
        
        buffer.append(" datatypes: ");
        FieldQuery[] dataTypes = getDataTypes();
        for (int i = 0; i < dataTypes.length; i++) {
            buffer.append(dataTypes[i].toString());
        }
        
        buffer.append(" ranking: ");
        buffer.append(getRankingType());
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
     * @return the positive data type's of this query
     */
    public String[] getPositiveDataTypes() {
        FieldQuery[] fields = getDataTypes();
        int count = fields.length;
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < count; i++) {
            if (fields[i].getFieldName().toLowerCase().equals(DATA_TYPE) && !fields[i].isProhibited()) {
                arraylist.add(fields[i].getFieldValue());
            }
        }
        return (String[]) arraylist.toArray(new String[arraylist.size()]);
    }

    /**
     * @return
     */
    public FieldQuery[] getDataTypes() {
        ArrayList arrayList = getArrayList(DATA_TYPE);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        return (FieldQuery[]) arrayList.toArray(new FieldQuery[arrayList.size()]);

    }

    /**
     * @return the negative data type's of this query
     */
    public String[] getNegativeDataTypes() {
        FieldQuery[] fields = getDataTypes();
        int count = fields.length;
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < count; i++) {
            if (fields[i].getFieldName().toLowerCase().equals(DATA_TYPE) && fields[i].isProhibited()) {
                arraylist.add(fields[i].getFieldValue());
            }
        }
        return (String[]) arraylist.toArray(new String[arraylist.size()]);
    }

    /**
     * @return true if the score is ranked
     */
    public boolean isScoreRanked() {
        return isRanked(SCORE_RANKED);
    }

    /**
     * @return true if the date is ranked
     */
    public boolean isDateRanked() {
        return isRanked(DATE_RANKED);
    }

    /**
     * @return true if nothing is ranked
     */
    public boolean isNotRanked() {
        return isRanked(NOT_RANKED);
    }

    /**
     * @param rankValue
     * @return true if the rankValue ranked.
     */
    public boolean isRanked(String rankValue) {
        String ranked = (String) get(RANKED);
        return rankValue.equalsIgnoreCase(ranked);
    }

    public String getRankingType() {
        return (String) get(RANKED);
    }
    
    /**
     * @return the grouped value
     */
    public String getGrouped() {
        return (String) get(GROUPED);
    }
    
    public String[] getPositiveProvider() {
        return getProvider(false);
    }

    public String[] getNegativeProvider() {
        return getProvider(true);
    }

    private String[] getProvider(boolean prohibited) {
        ArrayList arrayList = getArrayList(PROVIDER);
        ArrayList list = new ArrayList();
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            FieldQuery query = (FieldQuery) arrayList.get(i);
            if (query.isProhibited() == prohibited) {
                list.add(query.getFieldValue().toLowerCase());
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

}
