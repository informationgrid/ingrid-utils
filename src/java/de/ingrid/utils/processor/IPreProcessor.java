/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import de.ingrid.utils.query.IngridQuery;

public interface IPreProcessor {

    /**
     * process the query object we directly manipulate the query parameter and
     * do not return new objects for performance reasons.
     * 
     * @param query
     * @throws Exception
     */
  public  void process(IngridQuery query) throws Exception;

}
