/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.processor.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.processor.IPreProcessor;
import de.ingrid.utils.query.IngridQuery;

/**
 * logs the queries
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class StatisticPreProcessor implements IPreProcessor {
    private static Log log = LogFactory.getLog(StatisticPreProcessor.class);

    /* (non-Javadoc)
     * @see de.ingrid.utils.processor.IPreProcessor#process(de.ingrid.utils.query.IngridQuery)
     */
    public void process(IngridQuery query) throws Exception {
        log.info(query.toString()); // just logs the query
    }

}
