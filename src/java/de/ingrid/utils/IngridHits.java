/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

/**
 * Container to store a set of hit objects and the number of all hits for the
 * done search created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class IngridHits extends IngridDocument {

    private static final String LENGTH = "length";

    private static final String HITS = "hits";

    private static final String IPLUG_ID = "iPlugId";

    public IngridHits() {
        // default constructor for serialization
    }

    /**
     * @param provider
     * @param length
     * @param hits
     */
    public IngridHits(String plugId, long length, IngridHit[] hits) {
        setPlugId(plugId);
        setLength(length);
        setHits(hits);
    }

    /**
     * @param hits
     */
    private void setHits(IngridHit[] hits) {
        setArray(HITS, hits);
    }

    /**
     * @return a subset of hit objects for the done search request
     */
    public IngridHit[] getHits() {
        return (IngridHit[]) getArray(HITS);
    }

    /**
     * @param length
     */
    private void setLength(long length) {
        putLong(LENGTH, length);
    }

    /**
     * @return number of all hits found for the search request Attention is not
     *         equals to number of <code>Hit</code>s stored in this
     *         container.
     */
    public long length() {
        return getLong(LENGTH);
    }

    /**
     * @return
     */
    public String getPlugId() {
        return (String) get(IPLUG_ID);
    }

    /**
     * @param id
     */
    public void setPlugId(String id) {
        put(IPLUG_ID, id);
    }

}
