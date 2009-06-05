/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.processor.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.IngridDocument;
import de.ingrid.utils.processor.IPostProcessor;
import de.ingrid.utils.query.IngridQuery;

/**
 * logs results
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class StatisticPostProcessor implements IPostProcessor {
    private static Log log = LogFactory.getLog(StatisticPostProcessor.class);

    public void process(IngridQuery query, IngridDocument[] document) throws Exception {
        log.info(query.toString() + "  matches: " + document.length);
    }

}
