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

    private static final String PROVIDER = "provider";

    private static final String LENGTH = "length";

    private static final String HITS = "hits";

    public IngridHits() {
        // default constructor for serialization
    }

    /**
     * @param provider
     * @param length
     * @param hits
     */
    public IngridHits(String provider, long length, IngridHit[] hits) {
        setProvider(provider);
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
     * @return the unique provider Id
     */
    public String getProvider() {
        return (String) get(PROVIDER);
    }

    /**
     * Sets a unique provider Id
     * 
     * @param providerId
     */
    public void setProvider(String providerId) {
        put(PROVIDER, providerId);

    }

}
