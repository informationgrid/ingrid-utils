/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;

import java.util.ArrayList;
import java.util.List;

import de.ingrid.utils.IngridDocument;
import de.ingrid.utils.PlugDescription;

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
    public static final int WILDCARD_FIELD = 6;

    /***/
    public static final int FUZZY_FIELD = 7;

    /***/
    public static final int WILDCARD_TERM = 8;

    /***/
    public static final int FUZZY_TERM = 9;

    private static final String TYPE = "type";

    private static final String REQUIRED = "required";

    private static final String FIELD_KEY = "field";
    
    /***/
    public static final String TERM_KEY = "term";

    private static final String RANGE_KEY = "range";

    private static final String WILDCARD_FIELD_KEY = "wildcard_field";

    private static final String WILDCARD_TERM_KEY = "wildcard_term";

    private static final String FUZZY_FIELD_KEY = "fuzzy_field";

    private static final String FUZZY_TERM_KEY = "fuzzy_term";

    private static final String CLAUSE_KEY = "clause";

    /***/
    public static final String DATA_TYPE = "datatype";

    /***/
    private static final String PROHIBITED = "prohibited";

    /***/
    public static final String SCORE_RANKED = "score";

    /***/
    public static final String RANKED = "ranking";

    /***/
    public static final String DATE_RANKED = "date";

    /***/
    public static final String NOT_RANKED = "off";

    /***/
    public static final String ANY_RANKED = "any";

    /***/
    private static final String GROUPED = "grouped";

    /***/
    public static final String GROUPED_OFF = "grouped_off";

    /***/
    public static final String GROUPED_BY_PLUGID = "grouped_by_plugId";

    /***/
    public static final String GROUPED_BY_ORGANISATION = "grouped_by_organisation";

    /***/
    public static final String GROUPED_BY_PARTNER = "grouped_by_partner";

    /***/
    public static final String GROUPED_BY_DATASOURCE = "grouped_by_datasource";

    /***/
    public static final String PROVIDER = PlugDescription.PROVIDER;

    /***/
    public static final String PARTNER = PlugDescription.PARTNER;

    /***/
    public static final String IPLUGS = "iplugs";

    private transient IngridQuery fLastAddedQuery;
    
    private boolean _wasAnd = false;

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
     * @param prohibited
     * @param query
     */
    public IngridQuery(boolean required, boolean prohibited, int type, String query) {
        super(new Long(System.currentTimeMillis()), query);
        putBoolean(REQUIRED, required);
        putBoolean(PROHIBITED, prohibited);
        putInt(TYPE, type);

    }

    /**
     * @return if required
     */
    public boolean isRequred() {
        return getBoolean(REQUIRED);
    }

    /**
     * @param required
     */
    public void setRequired(boolean required) {
        putBoolean(REQUIRED, required);
    }

    /**
     * @return true if prohibited
     */
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
        } else if (field.getFieldName().equals(PARTNER)) {
            addToList(PARTNER, field);
        } else if (field.getFieldName().equals(IPLUGS)) {
            addToList(IPLUGS, field);
        } else {
            addToList(FIELD_KEY, field);
        }
        this.fLastAddedQuery = field;
    }

    /**
     * @param fieldName
     * @return true if query contains a field with the given name
     */
    public boolean containsField(String fieldName) {
        ArrayList fields = getArrayList(FIELD_KEY);
        if (fields != null) {
            int size = fields.size();
            for (int i = 0; i < size; i++) {
                if (((FieldQuery) fields.get(i)).getFieldName().equals(fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * removes a fieldquery from the query
     * 
     * @param fieldQuery
     */
    public void removeField(FieldQuery fieldQuery) {
        ArrayList fields = getArrayList(FIELD_KEY);
        if (fields != null) {
            fields.remove(fieldQuery);
        }
    }

    /**
     * @param fieldName
     * @return null or a removed field query with the given field name
     */
    public FieldQuery removeField(String fieldName) {
        ArrayList fields = getArrayList(FIELD_KEY);
        if (fields != null) {
            int size = fields.size();
            for (int i = 0; i < size; i++) {
                if (((FieldQuery) fields.get(i)).getFieldName().equals(fieldName)) {
                    return (FieldQuery) fields.remove(i);
                }
            }
        }
        return null;
    }

    /**
     * removes a data type from the query
     * 
     * @param dataType
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
        this.fLastAddedQuery = term;
    }
    
    /**
     * Set true if last operator was AND.
     * 
     * @param wasAnd
     */
    public void setLastWasAnd(boolean wasAnd){
    	_wasAnd = wasAnd;
    }
    
    /**
     * 
     */
    public void setLastQueryUnrequired() {
        if (this.fLastAddedQuery == null) {
            return;
        }
        if(!_wasAnd){
        	this.fLastAddedQuery.setRequired(false);
        }
    }
    
    public void setLastQueryRequired(){
    	if (this.fLastAddedQuery == null) {
            return;
        }
    	this.fLastAddedQuery.setRequired(true);
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
    public void addRangeQuery(RangeQuery query) {
        addToList(RANGE_KEY, query);
        this.fLastAddedQuery = query;
    }

    /**
     * @param query
     */
    public void addWildCardFieldQuery(WildCardFieldQuery query) {
        addToList(WILDCARD_FIELD_KEY, query);
        this.fLastAddedQuery = query;
    }

    /**
     * @param query
     */
    public void addWildCardTermQuery(WildCardTermQuery query) {
        addToList(WILDCARD_TERM_KEY, query);
        this.fLastAddedQuery = query;
    }

    /**
     * @param query
     */
    public void addFuzzyFieldQuery(FuzzyFieldQuery query) {
        addToList(FUZZY_FIELD_KEY, query);
        this.fLastAddedQuery = query;
    }

    /**
     * @param query
     */
    public void addFuzzyTermQuery(FuzzyTermQuery query) {
        addToList(FUZZY_TERM_KEY, query);
        this.fLastAddedQuery = query;
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
    public WildCardFieldQuery[] getWildCardFieldQueries() {
        ArrayList arrayList = getArrayList(WILDCARD_FIELD_KEY);
        if (arrayList == null) {
            return new WildCardFieldQuery[0];
        }
        return (WildCardFieldQuery[]) arrayList.toArray(new WildCardFieldQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public WildCardTermQuery[] getWildCardTermQueries() {
        ArrayList arrayList = getArrayList(WILDCARD_TERM_KEY);
        if (arrayList == null) {
            return new WildCardTermQuery[0];
        }
        return (WildCardTermQuery[]) arrayList.toArray(new WildCardTermQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public FuzzyFieldQuery[] getFuzzyFieldQueries() {
        ArrayList arrayList = getArrayList(FUZZY_FIELD_KEY);
        if (arrayList == null) {
            return new FuzzyFieldQuery[0];
        }
        return (FuzzyFieldQuery[]) arrayList.toArray(new FuzzyFieldQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public FuzzyTermQuery[] getFuzzyTermQueries() {
        ArrayList arrayList = getArrayList(FUZZY_TERM_KEY);
        if (arrayList == null) {
            return new FuzzyTermQuery[0];
        }
        return (FuzzyTermQuery[]) arrayList.toArray(new FuzzyTermQuery[arrayList.size()]);
    }

    /**
     * adds a clause query
     * 
     * @param clauseQuery
     */
    public void addClause(ClauseQuery clauseQuery) {
        addToList(CLAUSE_KEY, clauseQuery);
        this.fLastAddedQuery = clauseQuery;
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

        buffer.append(" wildcardFields: ");
        appendToString(buffer, getWildCardFieldQueries());

        buffer.append(" wildcardTerms: ");
        appendToString(buffer, getWildCardTermQueries());

        buffer.append(" fuzzyFields: ");
        appendToString(buffer, getFuzzyFieldQueries());

        buffer.append(" fuzzyTerms: ");
        appendToString(buffer, getFuzzyTermQueries());

        buffer.append(" providers: ");
        ArrayList arrayList = getArrayList(PROVIDER);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        IngridQuery[] provider = (IngridQuery[]) arrayList.toArray(new IngridQuery[arrayList.size()]);
        appendToString(buffer, provider);

        buffer.append(" partners: ");
        arrayList = getArrayList(PARTNER);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        IngridQuery[] partner = (IngridQuery[]) arrayList.toArray(new IngridQuery[arrayList.size()]);
        appendToString(buffer, partner);
        
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
     * @return all data types
     */
    public FieldQuery[] getDataTypes() {
        ArrayList arrayList = getArrayList(DATA_TYPE);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        
        IngridQuery[] clauses = getClauses();
        for (int i = 0; i < clauses.length; i++) {
            FieldQuery[] fields = clauses[i].getDataTypes();
            for (int j = 0; j < fields.length; j++) {
                arrayList.add(fields[j]);
            }
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

    /**
     * @return the rank type
     */
    public String getRankingType() {
        return (String) get(RANKED);
    }

    /**
     * @return the grouped value
     */
    public String getGrouped() {
        return (String) get(GROUPED);
    }

    /**
     * @return all unprohibeted providers
     */
    public String[] getPositiveProvider() {
        return getFields(PROVIDER, false);
    }

    /**
     * @return all prohibeted providers
     */
    public String[] getNegativeProvider() {
        return getFields(PROVIDER, true);
    }

    /**
     * @return all unprohibeted partners
     */
    public String[] getPositivePartner() {
        return getFields(PARTNER, false);
    }

    /**
     * @return all prohibeted partners
     */
    public String[] getNegativePartner() {
        return getFields(PARTNER, true);
    }

    private String[] getFields(String name, boolean prohibited) {
        ArrayList arrayList = getArrayList(name);
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
        
        IngridQuery[] clauses = getClauses();
        for (int i = 0; i < clauses.length; i++) {
            String[] fields = clauses[i].getFields(name, prohibited);
            for (int j = 0; j < fields.length; j++) {
                list.add(fields[j]);
            }
        }
        
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * @return all iplugs the query is restricted to
     */
    public String[] getIPlugs() {
        ArrayList plugTerms = getArrayList(IPLUGS);
        if (plugTerms == null) {
            return new String[0];
        }
        List iplugs = new ArrayList(plugTerms.size());
        for (int i = 0; i < plugTerms.size(); i++) {
            FieldQuery query = (FieldQuery) plugTerms.get(i);
            iplugs.add(query.getFieldValue().toLowerCase());
        }
        return (String[]) iplugs.toArray(new String[iplugs.size()]);
    }

    /**
     * @return An array which contains the query itself and all its clauses.
     */
    public IngridQuery[] getAllClauses() {
        List clauses = new ArrayList();
        fillWithClausesRecursiv(clauses, this);
        return (IngridQuery[]) clauses.toArray(new IngridQuery[clauses.size()]);
    }

    private void fillWithClausesRecursiv(List clauses, IngridQuery query) {
        ClauseQuery[] queries = query.getClauses();
        for (int i = 0; i < queries.length; i++) {
            fillWithClausesRecursiv(clauses, queries[i]);
        }
        clauses.add(query);
    }
    

}
