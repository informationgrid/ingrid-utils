/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
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

/**
 * 
 */
public class RangeQuery extends IngridQuery {

  private static final long serialVersionUID = RangeQuery.class.getName()
      .hashCode();

  private static final String RANGE_NAME = "rangeName";

  private static final String RANGE_FROM = "fromValue";

  private static final String RANGE_TO = "toValue";

  private boolean fInclusive;
  
  
  public RangeQuery() {
      super();
  }

  /**
   * @param required
   * @param prohibited
   * @param fieldName
   * @param from
   * @param to
   */
  public RangeQuery(boolean required, boolean prohibited, String fieldName,
      String from, String to, boolean inclusive) {
    super(required, prohibited, IngridQuery.RANGE, fieldName + ":[" + from
        + " TO " + to + "]");
    this.fInclusive = inclusive;
    put(RANGE_NAME, fieldName);
    put(RANGE_FROM, from);
    put(RANGE_TO, to);
  }

  /**
   * @return the rangeName
   */
  public String getRangeName() {
    return (String) get(RANGE_NAME);
  }

  /**
   * @return range from
   */
  public String getRangeFrom() {
    return (String) get(RANGE_FROM);
  }

  /**
   * @return range to
   */
  public String getRangeTo() {
    return (String) get(RANGE_TO);
  }

  /**
   * @return inclusive
   */
  public boolean isInclusive() {
    return this.fInclusive;
  }

  public String toString() {
    return getRangeName() + ":[" + getRangeFrom() + " TO " + getRangeTo() + "]";
  }
}
