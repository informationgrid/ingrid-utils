/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.io.Serializable;

import de.ingrid.utils.query.IngridQuery;

/**
 * Interface defining the search interface for the Ibus
 * 
 * created on 19.09.2005
 * 
 * @author sg
 * @version $Revision: 1.2 $
 * 
 */
public interface IBus extends Serializable, IDetailer, IRecordLoader {
    /**
     * @param query
     * @param hitsPerPage
     * @param currentPage
     * @param length
     * @param maxMilliseconds
     * @return array of matching documents.
     * @throws Exception
     */
    public IngridHits search(IngridQuery query, int hitsPerPage, int currentPage, int length, int maxMilliseconds)
            throws Exception;

    /**
     * @param plugDescripion
     */
    public void addPlugDescription(PlugDescription plugDescripion);

    /**
     * @param plugDescripion
     */
    public void removePlugDescription(PlugDescription plugDescripion);
    
    /**
     * @param plugId
     * @return The IPlug description.
     */
    public PlugDescription getIPlug(String plugId);

    /**
     * @return all iplugs regested in the regestry
     */
    public PlugDescription[] getAllIPlugs();
}
