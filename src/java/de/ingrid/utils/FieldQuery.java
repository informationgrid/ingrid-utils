/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

public class FieldQuery extends IngridQuery {

    private String fFieldName;

    private String fFieldValue;

    public FieldQuery(int booleanOperation, String field) {
        super(IngridQuery.FIELD, booleanOperation, field);
        int pos = field.indexOf(":");
        this.fFieldName = field.substring(0, pos);
        this.fFieldValue = field.substring(Math.min(pos + 1, field.length()), field.length());

    }

    /**
     * @return the fieldname
     */
    public String getFieldName() {
        return this.fFieldName;
    }

    /**
     * @return the field value
     */
    public String getFieldValue() {
        return this.fFieldValue;
    }

}
