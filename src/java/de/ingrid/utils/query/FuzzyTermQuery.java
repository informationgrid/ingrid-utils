/**
 * 
 */
package de.ingrid.utils.query;

/**
 * @author mb
 *
 */
public class FuzzyTermQuery extends IngridQuery {

private static final long serialVersionUID = FuzzyTermQuery.class.getName().hashCode();
  
  private static final String fuzzyTerm = "fuzzyTerm";

  /**
   * Constructs a term query
   */
  public FuzzyTermQuery(boolean required, boolean prohibited, String term) {
      super(required, prohibited, IngridQuery.FUZZY_TERM, term);
      put(fuzzyTerm, term);
  }

  /**
   * 
   */
  public FuzzyTermQuery() {
      // be serializable
  }

  /**
   * @return the query term
   */
  public String getTerm() {
      return (String) get(fuzzyTerm);
  }
  
  public String toString() {
    return getTerm();
  }
}
