/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.dsc;

import java.io.Serializable;

/**
 * Bean represeting a sql Filter some thing like: Select * from Users where
 * *Alter > 23*
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class Filter implements Serializable{
    
    private static final long serialVersionUID = (long)Filter.class.getName().hashCode();

	public final static int EQUALS = 0;

	public final static int LARGER_AS = 1;

	public final static int SMALLER_AS = 2;

	public final static int NOT = 3;

	private int fFilterType;

	private String fFilterValue;

	/**
	 * @param filterType
	 * @param filterValue
	 */
	public Filter(int filterType, String filterValue) {
		fFilterType = filterType;
		fFilterValue = filterValue;
	}

	/**
	 * @return filterType
	 */
	public int getFilterType() {
		return fFilterType;
	}

	/**
	 * @return a filter value
	 */
	public String getFilterValue() {
		return fFilterValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getCompareSymbol() + getFilterValue();

	}

	/**
	 * @param filterType
	 * @return a sql symbol for the type, e.g. = or < or > or !=
	 */
	public String getCompareSymbol() {
		switch (getFilterType()) {
		case EQUALS:
			return "=";
		case LARGER_AS:
			return ">";
		case SMALLER_AS:
			return "<";
		case NOT:
			return "!=";

		default:
			throw new IllegalArgumentException("unknown Filter type");
		}
	}
}
