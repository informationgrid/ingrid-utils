/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import de.ingrid.utils.IngridDocument;

public interface IPostProcessor {

    /**
     * post process a ingrid document, we directly manipulating the document
     * parameter for performance reasons.
     * 
     * @param document
     * @throws Exception
     */
    public void process(IngridDocument document) throws Exception;

}
