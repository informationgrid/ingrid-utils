/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;

public class FieldQuery extends IngridQuery {

    private static final String FIELD_NAME = "fieldName";

    private static final String FIELD_VALUE = "fieldValue";

    public FieldQuery(int booleanOperation, String fieldKey, String fieldValue) {
        super(IngridQuery.FIELD, booleanOperation, fieldKey+":"+fieldValue);
        put(FIELD_NAME, fieldKey);
        put(FIELD_VALUE, fieldValue);

    }

    /**
     * @return the fieldname
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

}
