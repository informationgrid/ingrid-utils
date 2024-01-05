/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
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
# * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.dsc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * bean for database column information.
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class Column extends UniqueObject implements Serializable {

	private static final long serialVersionUID = Column.class.getName()
			.hashCode();

	public static final String TEXT = "text";

	public static final String INTEGER = "int"; // not supported yet

	public static final String KEYWORD = "keyWord";

	public static final String DATE = "date";

	public static final String DIGIT = "digit"; // not supported yet

    public static final String BINARY = "binary";

	private String fTableName;

	private String fColumnName;

	private String fTargetName;

	private float fBoost;

	private String fType;

	private List<Filter> fFilters = new ArrayList<Filter>();

	private boolean fToIndex;

	private boolean fFilterIsRequired;

	/**
	 * @param tableName
	 * @param columnName
	 * @param type
	 * @param b
	 */
	public Column(String tableName, String columnName, String type, boolean b) {
		fColumnName = columnName;
		fTableName = tableName;
		fType = type;
		fToIndex = b;
	}

	/**
	 * @return Returns the columnName.
	 */
	public String getColumnName() {
		return fColumnName;
	}

	/**
	 * @param columnName
	 *            The columnName to set.
	 */
	public void setColumnName(String columnName) {
		fColumnName = columnName;
	}

	/**
	 * @return Returns the tableName.
	 */
	public String getTableName() {
		return fTableName;
	}

	/**
	 * @param tableName
	 *            The tableName to set.
	 */
	public void setTableName(String tableName) {
		fTableName = tableName;
	}

	/**
	 * @return the tareget name we use for the index field
	 */
	public String getTargetName() {
		if (fTargetName != null) {
			return fTargetName.toLowerCase();
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
        String tableName = getTableName();
        return (tableName != null && !"".equals(tableName) ? (tableName + ".") : "") + getColumnName();
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
    
    /**
     * sets the type
     * @param type
     */
    public void setType(String type) {
        fType = type;
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
		if (getType().equals(TEXT) || getType().equals(KEYWORD)) {
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

	public void setFilterIsRequired(boolean required) {
		fFilterIsRequired = required;
	}

	public boolean filterIsRequired() {
		return fFilterIsRequired;
	}

	/**
	 * add a filter
	 * 
	 * @param filter
	 */
	public void addFilter(Filter filter) {
		if (fFilters == null) {
			fFilters = new ArrayList<Filter>();
		}
		fFilters.add(filter);
	}

	public Filter[] getFilters() {
		if (fFilters != null) {
			return (Filter[]) fFilters.toArray(new Filter[fFilters
					.size()]);
		} else {
			return new Filter[0];
		}
	}

	public void removeFilter(Filter filter) {
		fFilters.remove(filter);
	}

}
