/**
 * 
 */
package de.ingrid.utils.query;

/**
 * @author mb
 *
 */
public class WildCardTermQuery extends IngridQuery {

  private static final long serialVersionUID = WildCardTermQuery.class.getName().hashCode();
  
  private static final String WILD_TERM = "wildCardTerm";

  /**
   * Constructs a term query
   */
  public WildCardTermQuery(boolean required, boolean prohibited, String term) {
      super(required, prohibited, IngridQuery.WILDCARD_TERM, term);
      put(WILD_TERM, term);
  }

  /**
   * 
   */
  public WildCardTermQuery() {
      // be serializable
  }

  /**
   * @return the query term
   */
  public String getTerm() {
      return (String) get(WILD_TERM);
  }
  
  
  public String toString() {
    return getTerm();
  }
}
