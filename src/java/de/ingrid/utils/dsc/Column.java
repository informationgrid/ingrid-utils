/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.dsc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * bean for database column information.
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class Column extends UniqueObject implements Serializable {

    private static final long serialVersionUID = (long)Column.class.getName().hashCode();

    public static final String TEXT = "text";

	public static final String INTEGER = "int"; // not supported yet

	public static final String KEYWORD = "keyWord";

	public static final String DATE = "date";

	public static final String DIGIT = "digit"; // not supported yet

	private String fTableName;

	private String fColumnName;

	private String fTargetName;

	private float fBoost;

	private String fType;

	private ArrayList fFilters = new ArrayList();

	private boolean fToIndex;

	private boolean fFilterIsRequred;

	/**
	 * @param tableName
	 * @param columnName
	 * @param type
	 * @param b
	 */
	public Column(String tableName, String columnName, String type, boolean b) {
		this.fColumnName = columnName;
		this.fTableName = tableName;
		this.fType = type;
		this.fToIndex = b;
	}

	/**
	 * @return Returns the columnName.
	 */
	public String getColumnName() {
		return this.fColumnName;
	}

	/**
	 * @param columnName
	 *            The columnName to set.
	 */
	public void setColumnName(String columnName) {
		this.fColumnName = columnName;
	}

	/**
	 * @return Returns the tableName.
	 */
	public String getTableName() {
		return this.fTableName;
	}

	/**
	 * @param tableName
	 *            The tableName to set.
	 */
	public void setTableName(String tableName) {
		this.fTableName = tableName;
	}

	/**
	 * @return the tareget name we use for the index field
	 */
	public String getTargetName() {
		if (fTargetName != null) {
			return fTargetName;
		} else {
			return toString();
		}
	}

	/**
	 * sets the target name that is used as field name in the index
	 * 
	 * @param name
	 */
	public void setTargetName(String name) {
		fTargetName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getTableName() + "." + getColumnName();
	}

	/**
	 * @return the boosting value
	 */
	public float getBoost() {
		return fBoost;
	}

	/**
	 * sets the boosting value.
	 * 
	 * @param boost
	 */
	public void setBoost(float boost) {
		fBoost = boost;
	}

	/**
	 * @return the columntype
	 */
	public String getType() {
		return fType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		if (arg0 instanceof Column) {
			Column column = (Column) arg0;
			return column.getTableName().equals(getTableName())
					&& column.getColumnName().equals(getColumnName());
		}
		return false;
	}

	/**
	 * @return return a quate char in case the column of type text
	 */
	public String getQuoteChar() {
		if (getType().equals(TEXT)) {
			return "'";
		}
		return "";
	}

	/**
	 * @return true in case this column should be added to the index.
	 */
	public boolean toIndex() {
		return fToIndex;
	}

	/**
	 * @param set
	 *            to true if this column should added to the index.
	 */
	public void addToIndex(boolean b) {
		fToIndex = b;
	}

	public void setFilterIsRequired(boolean required){
		this.fFilterIsRequred = required;
	}
	
	public boolean filterIsRequired(){
		return this.fFilterIsRequred;
	}
	
    /**
     * add a filter
     * @param filter
     */
    public void addFilter(Filter filter) {
       this.fFilters.add(filter);
    }
    
    public Filter[] getFilters(){
        return (Filter[]) this.fFilters.toArray(new Filter[this.fFilters.size()]);
    }
    
    public void removeFilter(Filter filter){
        this.fFilters.remove(filter);
    }
    
}
