/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2017 wemove digital solutions GmbH
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
public class FuzzyTermQuery extends IngridQuery {

private static final long serialVersionUID = FuzzyTermQuery.class.getName().hashCode();
  
  private static final String fuzzyTerm = "fuzzyTerm";

  /**
   * Constructs a term query
 * @param required 
 * @param prohibited 
 * @param term 
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
