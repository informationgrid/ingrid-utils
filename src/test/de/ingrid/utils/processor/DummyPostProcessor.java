/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import de.ingrid.utils.IngridDocument;

public class DummyPostProcessor implements IPostProcessor {

    public void process(IngridDocument[] documents) throws Exception {
        documents[0].put("marker", "blu");
    }

}
