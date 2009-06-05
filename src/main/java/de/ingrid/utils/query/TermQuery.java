/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;


/**
 * Term queries are queries having just one term.
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class TermQuery extends IngridQuery {

    private static final long serialVersionUID = 10L;

    /**
     * Constructs a term query
     */
    public TermQuery(boolean required, boolean prohibited, String term) {
        super(required, prohibited, IngridQuery.TERM, term);
    }

    /**
     * 
     */
    public TermQuery() {
        // be serializable
    }

    /**
     * @return the query term
     */
    public String getTerm() {
        return (String) getContent();
    }
}
