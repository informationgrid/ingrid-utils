/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;

/**
 * ClauseQuery are sub queries in brackets
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class ClauseQuery extends IngridQuery {

    /**
     * 
     */
    private static final long serialVersionUID = ClauseQuery.class.getName().hashCode();

    /**
     * 
     */
    public ClauseQuery() {
        // to be serializable
    }

    /**
     * @param booleanOperator
     */
    public ClauseQuery(boolean required, boolean prohibited) {
        super(required, prohibited, IngridQuery.CLAUSE, null);
    }
}
