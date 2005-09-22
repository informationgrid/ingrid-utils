/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import de.ingrid.utils.IngridDocument;

public interface IPostProcessor {

    /**
     * post process a ingrid documents, we directly manipulating the documents
     * parameter for performance reasons.
     * 
     * @param documents
     * @throws Exception
     */
    public void process(IngridDocument[] documents) throws Exception;

}
