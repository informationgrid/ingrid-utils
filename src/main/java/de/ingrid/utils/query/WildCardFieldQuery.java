/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 wemove digital solutions GmbH
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
 * 
 */

package de.ingrid.utils.query;

/**
 * 
 */
public class WildCardFieldQuery extends IngridQuery {

  private static final long serialVersionUID = WildCardFieldQuery.class.getName()
      .hashCode();

  private static final String FIELD_NAME = "wildcardName";

  private static final String FIELD_VALUE = "wildcardValue";
  
  public WildCardFieldQuery() {
        super();
    }

  /**
   * @param required
   * @param prohibited
   * @param fieldName
   * @param wildCardValue
   */
  public WildCardFieldQuery(boolean required, boolean prohibited, String fieldName,
      String wildCardValue) {
    super(required, prohibited, IngridQuery.WILDCARD_FIELD, fieldName + ":"
        + wildCardValue);
    put(FIELD_NAME, fieldName);
    put(FIELD_VALUE, wildCardValue);
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

  public String toString() {
    return getFieldName() + ":" + getFieldValue();
  }
}
