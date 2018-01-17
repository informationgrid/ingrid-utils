/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2018 wemove digital solutions GmbH
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

package de.ingrid.utils;

import java.io.Serializable;

import de.ingrid.utils.metadata.Metadata;
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
public interface IBus extends Serializable, IDetailer, IRecordLoader, ICaller {
    /**
     * Multicast the query to all connected IPlugs and return founded results.
     * 
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
     * @param plugId
     * @param md5Hash
     * @return true if bus contains a plugDescription with the following md5
     *         hash
     */
    public boolean containsPlugDescription(String plugId, String md5Hash);

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
    
    /**
     * Returns all registered IPlugs.
     * 
     * @return All registered IPlugs without checking the time stamp.
     */
    public PlugDescription[] getAllIPlugsWithoutTimeLimitation();
    
    Serializable getMetadata(String plugId, String metadataKey);
    
    Metadata getMetadata(String plugId);
    
    /**
	 * 
	 * @return the ibus metadata
	 */
    Metadata getMetadata();
    
    IngridHits searchAndDetail(IngridQuery query, int hitsPerPage,
			int currentPage, int startHit, int maxMilliseconds,
			String[] requestedFields) throws Exception;
    
    IngridDocument call(IngridCall targetInfo) throws Exception;
}
