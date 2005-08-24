/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;


/**
 * A Container for the query
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class IngridQuey extends IngridDocument {

    public static final String Terms = "QueryToken";

    public static final String OR_QUERIES = "OrQueries";

    public static final String SUB_QUERIES = "SubQueries";

    /**
     * Constructor
     * 
     * @param id
     * @param query
     */
    public IngridQuey(long id, final String query) {
        super(new Long(id), query);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return (String) getContent();
    }

    public String[] getToken() {
        return (String[]) get(Terms);
    }

    public IngridQuey[] getSubQueries() {
        return (IngridQuey[]) get(SUB_QUERIES);
    }

}
