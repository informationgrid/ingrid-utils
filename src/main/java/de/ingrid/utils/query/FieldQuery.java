/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2018 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
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

import de.ingrid.utils.IngridDocument;

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

    /** Set/Change the name of the field
     * @param name
     */
    public void setFieldName(String name) {
        put(FIELD_NAME, name);
		put(IngridDocument.DOCUMENT_CONTENT, getFieldContent());
    }

    /** Set/Change the value of the field
     * @param value
     */
    public void setFieldValue(String value) {
        put(FIELD_VALUE, value);
		put(IngridDocument.DOCUMENT_CONTENT, getFieldContent());
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
        return prefix + "(" + getFieldContent() + ")";
    }
    
    private String getFieldContent() {
    	return ("" + getFieldName() + ":" + getFieldValue());
    }

}
