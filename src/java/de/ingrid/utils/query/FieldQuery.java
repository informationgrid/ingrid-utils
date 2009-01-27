/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;

/**
 * 
 */
public class FieldQuery extends IngridQuery {

    private static final long serialVersionUID = FieldQuery.class.getName().hashCode();

    private static final String FIELD_NAME = "fieldName";

    private static final String FIELD_VALUE = "fieldValue";

    /**
     * @param required
     * @param prohibited
     * @param fieldKey
     * @param fieldValue
     */
    public FieldQuery(boolean required, boolean prohibited, String fieldKey, String fieldValue) {
        super(required, prohibited, IngridQuery.FIELD, fieldKey + ":" + fieldValue);
        put(FIELD_NAME, fieldKey);
        put(FIELD_VALUE, fieldValue);
    }

    /**
     * 
     */
    public FieldQuery() {
        // to be serializable
    }

    /**
     * @return The name of the field.
     */
    public String getFieldName() {
        return (String) get(FIELD_NAME);
    }

    /**
     * @return the field value
     */
    public String getFieldValue() {
        return (String) get(FIELD_VALUE);
    }
    
    public String toString() {
        String prefix = isProhibited() ? "-" : (isRequred() ? "+" : "");
        return prefix + "(" + getFieldName() + ":" + getFieldValue() + ")";
    }

}
