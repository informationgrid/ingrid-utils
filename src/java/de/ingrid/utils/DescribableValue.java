/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

/**
 * Container for describable string values
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class DescribableValue {

    private String fValue;

    private String fDescription;

    public DescribableValue() {
        // default constructor
    }

    public DescribableValue(String value, String description) {
        this.fDescription = description;
        this.fValue = value;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return this.fDescription;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return this.fValue;
    }

    public String toString() {
        return getValue() != null ? getValue().toString() : "";
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.fDescription = description;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.fValue = value;
    }

}
