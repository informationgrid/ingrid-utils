/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2016 wemove digital solutions GmbH
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
