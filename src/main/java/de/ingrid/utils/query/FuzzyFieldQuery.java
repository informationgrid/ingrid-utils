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
/**
 * 
 */
package de.ingrid.utils.query;

/**
 * @author mb
 * 
 */
public class FuzzyFieldQuery extends IngridQuery {
  private static final long serialVersionUID = FuzzyFieldQuery.class.getName()
      .hashCode();

  private static final String FIELD_NAME = "fuzzyname";

  private static final String FIELD_VALUE = "fuzzyValue";

  /**
   * 
   */
  public FuzzyFieldQuery() {
    super();
  }

  /**
   * @param required
   * @param prohibited
   * @param fieldName
   * @param fuzzyValue 
   */
  public FuzzyFieldQuery(boolean required, boolean prohibited, String fieldName,
      String fuzzyValue) {
    super(required, prohibited, IngridQuery.FUZZY_FIELD, fieldName + ":" + fuzzyValue);
    put(FIELD_NAME, fieldName);
    put(FIELD_VALUE, fuzzyValue);
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
