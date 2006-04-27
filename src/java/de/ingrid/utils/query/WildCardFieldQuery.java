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
