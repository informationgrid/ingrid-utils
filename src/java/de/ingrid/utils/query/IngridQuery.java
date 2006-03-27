/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;

import java.util.ArrayList;
import java.util.List;

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

	private static final String TYPE = "type";

	private static final String REQUIRED = "required";

	private static final String FIELD_KEY = "field";

	private static final String TERM_KEY = "term";

	private static final String CLAUSE_KEY = "clause";

	public static final String DATA_TYPE = "datatype";

	private static final String PROHIBITED = "prohibited";

	public static final String SCORE_RANKED = "score";

	public static final String RANKED = "ranking";

	public static final String DATE_RANKED = "date";

	public static final String NOT_RANKED = "off";

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
	public IngridQuery(boolean required, boolean prohibited, int type,
			String query) {
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
      addToList(RANKED, field.getFieldValue().toLowerCase());
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
		return (FieldQuery[]) arrayList
				.toArray(new FieldQuery[arrayList.size()]);
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
		return (ClauseQuery[]) arrayList.toArray(new ClauseQuery[arrayList
				.size()]);

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
	 * @return the positive data type's of this query
	 */
	public String[] getPositiveDataTypes() {
		FieldQuery[] fields = getDataTypes();
		int count = fields.length;
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < count; i++) {
			if (fields[i].getFieldName().toLowerCase().equals(DATA_TYPE)
					&& !fields[i].isProhibited()) {
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
		return (FieldQuery[]) arrayList
				.toArray(new FieldQuery[arrayList.size()]);

	}

	/**
	 * @return the negative data type's of this query
	 */
	public String[] getNegativeDataTypes() {
		FieldQuery[] fields = getDataTypes();
		int count = fields.length;
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < count; i++) {
			if (fields[i].getFieldName().toLowerCase().equals(DATA_TYPE)
					&& fields[i].isProhibited()) {
				arraylist.add(fields[i].getFieldValue());
			}
		}
		return (String[]) arraylist.toArray(new String[arraylist.size()]);
	}

	public boolean isScoreRanked() {
		return get(RANKED) != null && get(RANKED).equals(SCORE_RANKED);
	}

	public boolean isDateRanked() {
		return get(RANKED) != null && get(RANKED).equals(DATE_RANKED);
	}

	public boolean isNotRanked() {
		return get(RANKED) != null && get(RANKED).equals(NOT_RANKED);
	}

	public String getRankingType() {
		return (String) get(RANKED);
	}

}
