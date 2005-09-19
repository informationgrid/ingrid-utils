/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;

public class FieldQuery extends IngridQuery {

    private static final String FIELD_NAME = "fieldName";

    private static final String FIELD_VALUE = "fieldValue";

    public FieldQuery(int booleanOperation, String field) {
        super(IngridQuery.FIELD, booleanOperation, field);
        int pos = field.indexOf(":");
        put(FIELD_NAME, field.substring(0, pos));
        put(FIELD_VALUE, field.substring(Math.min(pos + 1, field.length()), field.length()));

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
