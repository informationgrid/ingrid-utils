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
