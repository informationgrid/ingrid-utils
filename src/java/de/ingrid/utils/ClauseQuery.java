/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

public class ClauseQuery extends IngridQuery {
    public ClauseQuery(int booleanOperator) {
        super(IngridQuery.CLAUSE, booleanOperator, null);
    }
}
