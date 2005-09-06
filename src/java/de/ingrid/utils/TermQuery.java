/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

public class TermQuery extends IngridQuery {

   
    /**
     * Constructs a term query
     * @param booleanOperation
     * @param term
     */
    public TermQuery(int booleanOperation, String term) {
        super(IngridQuery.TERM, booleanOperation, term);
    }

    /**
     * @return the query term
     */
    public String getTerm() {
        return (String) getContent();
    }
}
