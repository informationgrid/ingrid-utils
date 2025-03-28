/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
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

    /** Generates DUMMY hit for unranked iPlugs normally delivering no results to show in unranked column in frontend ! */
    public static final String GET_UNRANKED_IPLUGS_WITH_NO_RESULTS = "get_unranked_iplugs_with_no_results";

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
    
    /***/
    public static final String CACHED = "cache";
    
    /***/
    public static final String QUERY_DENY = "metainfo:query_deny";

    public static final String ORIGIN = "origin";

    private transient IngridQuery fLastAddedQuery;
    
    private boolean _wasAnd = false;

    /**
     * Default constructor
     */
    public IngridQuery() {
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
		put(IngridDocument.DOCUMENT_CONTENT, query);
        putBoolean(REQUIRED, required);
        putBoolean(PROHIBITED, prohibited);
        //putBoolean(CACHED, true);
        putInt(TYPE, type);
		// bad hack: try to avoid hashcode collision with all constructor values
		// as string
		put("constructorValuesAsString", "" + required + ":" + prohibited
				+ ":" + type + ":" + query);
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
        } else if (field.getFieldName().equals(CACHED)) {
        	put(CACHED, field.getFieldValue().toLowerCase());
        } else if (field.getFieldName().equals(GET_UNRANKED_IPLUGS_WITH_NO_RESULTS)) {
        	put(GET_UNRANKED_IPLUGS_WITH_NO_RESULTS, field.getFieldValue().toLowerCase());
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
        List<Object> fields = getArrayList(FIELD_KEY);
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
        List<Object> fields = getArrayList(FIELD_KEY);
        if (fields != null) {
            fields.remove(fieldQuery);
        }
    }

    /** Remove FIRST occurence of field with given name in field list !
     * @param fieldName
     * @return null or a removed field query with the given field name (first one in fields list)
     */
    public FieldQuery removeField(String fieldName) {
        List<Object> fields = getArrayList(FIELD_KEY);
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
        List<Object> arrayList = getArrayList(DATA_TYPE);
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
        List<Object> arrayList = getArrayList(FIELD_KEY);
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
        List<Object> arrayList = getArrayList(TERM_KEY);
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
        List<Object> arrayList = getArrayList(RANGE_KEY);
        if (arrayList == null) {
            return new RangeQuery[0];
        }
        return (RangeQuery[]) arrayList.toArray(new RangeQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public WildCardFieldQuery[] getWildCardFieldQueries() {
        List<Object> arrayList = getArrayList(WILDCARD_FIELD_KEY);
        if (arrayList == null) {
            return new WildCardFieldQuery[0];
        }
        return (WildCardFieldQuery[]) arrayList.toArray(new WildCardFieldQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public WildCardTermQuery[] getWildCardTermQueries() {
        List<Object> arrayList = getArrayList(WILDCARD_TERM_KEY);
        if (arrayList == null) {
            return new WildCardTermQuery[0];
        }
        return (WildCardTermQuery[]) arrayList.toArray(new WildCardTermQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public FuzzyFieldQuery[] getFuzzyFieldQueries() {
        List<Object> arrayList = getArrayList(FUZZY_FIELD_KEY);
        if (arrayList == null) {
            return new FuzzyFieldQuery[0];
        }
        return (FuzzyFieldQuery[]) arrayList.toArray(new FuzzyFieldQuery[arrayList.size()]);
    }

    /**
     * @return an array of range queries
     */
    public FuzzyTermQuery[] getFuzzyTermQueries() {
        List<Object> arrayList = getArrayList(FUZZY_TERM_KEY);
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
        List<Object> arrayList = getArrayList(CLAUSE_KEY);
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
        List<Object> arrayList = getArrayList(CLAUSE_KEY);
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

        buffer.append(" " + CACHED + ": " + isCacheOn());

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
        List<Object> arrayList = getArrayList(PROVIDER);
        if (arrayList == null) {
            arrayList = new ArrayList<Object>();
        }
        IngridQuery[] provider = (IngridQuery[]) arrayList.toArray(new IngridQuery[arrayList.size()]);
        appendToString(buffer, provider);

        buffer.append(" partners: ");
        arrayList = getArrayList(PARTNER);
        if (arrayList == null) {
            arrayList = new ArrayList<Object>();
        }
        IngridQuery[] partner = (IngridQuery[]) arrayList.toArray(new IngridQuery[arrayList.size()]);
        appendToString(buffer, partner);
        
        buffer.append(" datatypes: ");
        FieldQuery[] dataTypes = getDataTypes();
        for (int i = 0; i < dataTypes.length; i++) {
            buffer.append(dataTypes[i].toString());
        }
        
        buffer.append(" iplugs: ");
        String[] iPlugs = getIPlugs();
        for (int i=0; i<iPlugs.length; i++) {
            buffer.append(iPlugs[i]).append(" ");
        }

        buffer.append(" ranking: ");
        buffer.append(getRankingType());

        buffer.append(" grouped: ");
        buffer.append(getGrouped());

        if (isGetUnrankedIPlugsWithNoResults()) {
            buffer.append(" " + GET_UNRANKED_IPLUGS_WITH_NO_RESULTS + ": " + get(GET_UNRANKED_IPLUGS_WITH_NO_RESULTS));
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
     * @return the positive data type's of this query
     */
    public String[] getPositiveDataTypes() {
        FieldQuery[] fields = getDataTypes();
        int count = fields.length;
        List<String> arraylist = new ArrayList<String>();
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
        List<Object> arrayList = getArrayList(DATA_TYPE);
        if (arrayList == null) {
            arrayList = new ArrayList<Object>();
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
        List<String> arraylist = new ArrayList<String>();
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
     * 
     * @return true if cache is set to "true"
     */
    public boolean isCacheOff() {
    	String cached = (String)get(CACHED);
    	
    	if (cached == null) {
    		return false;
    	}
    	return cached.equals("off");
    }
    
    public boolean isCacheOn() {
    	return !isCacheOff();
    }

    /**
     * @return true if unranked iplugs with NO results will deliver dummy hit
     */
    public boolean isGetUnrankedIPlugsWithNoResults() {
    	if (get(GET_UNRANKED_IPLUGS_WITH_NO_RESULTS) != null) {
    		return true;
    	}
    	return false;
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
        List<Object> arrayList = getArrayList(name);
        List<String> list = new ArrayList<String>();
        if (arrayList == null) {
            arrayList = new ArrayList<Object>();
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
        List<Object> plugTerms = getArrayList(IPLUGS);
        if (plugTerms == null) {
            return new String[0];
        }
        List<String> iplugs = new ArrayList<String>(plugTerms.size());
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
        List<IngridQuery> clauses = new ArrayList<IngridQuery>();
        fillWithClausesRecursiv(clauses, this);
        return (IngridQuery[]) clauses.toArray(new IngridQuery[clauses.size()]);
    }

    private void fillWithClausesRecursiv(List<IngridQuery> clauses, IngridQuery query) {
        ClauseQuery[] queries = query.getClauses();
        for (int i = 0; i < queries.length; i++) {
            fillWithClausesRecursiv(clauses, queries[i]);
        }
        clauses.add(query);
    }
    
    public boolean isRejected() {
        List<Object> fields = getArrayList("field");
        if (fields != null && fields.toString().contains(QUERY_DENY)) {
            return true;
        }
        return false;
    }
    
    /*
    // might be used when language needs to be transfered to iPlugs but no as a field
    public void addLanguageFromLocale(Locale loc) {
        put(LANGUAGE, loc.getLanguage());
    }

    public String getLanguage() {
        return (String)get(LANGUAGE);
    }*/
}
