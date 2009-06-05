/*
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import de.ingrid.utils.query.IngridQuery;

public class DummyPreProcessor implements IPreProcessor {

    public void process(IngridQuery query) throws Exception {
        query.put("marker", "bla");
    }

}
