/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import de.ingrid.utils.dsc.Record;

/**
 * Interface that can implemented to support complete record loading..
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public interface IRecordLoader {

    /**
     * Returns a record to a hit.
     * @param hit The hit to that a record is requested.
     * @return The record.
     * @throws Exception
     */
    public Record getRecord(IngridHit hit) throws Exception;
}
