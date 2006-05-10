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

    private static final long serialVersionUID = 5L;

    private static final String LENGTH = "length";

    private static final String HITS = "hits";

    private static final String IPLUG_ID = "iPlugId";

    private static final String RANKED = "ranked";

    private static final String GROUPED_HITS_LENGTH = "groupedHitsLength";

    private static final String INVOLVED_PLUGS = "involvedPlugs";

    /**
     * 
     */
    public IngridHits() {
        // default constructor for serialization
    }

    /**
     * @param plugId
     * @param length
     * @param hits
     * @param isRanked
     */
    public IngridHits(String plugId, long length, IngridHit[] hits, boolean isRanked) {
        setPlugId(plugId);
        setLength(length);
        setHits(hits);
        putBoolean(RANKED, isRanked);
    }

    /**
     * @param totalHits
     * @param hits
     */
    public IngridHits(int totalHits, IngridHit[] hits) {
        setLength(totalHits);
        setHits(hits);
    }

    /**
     * @param totalHits
     * @param hits
     * @param groupedHitsLength
     */
    public IngridHits(int totalHits, IngridHit[] hits, int groupedHitsLength) {
        setLength(totalHits);
        setHits(hits);
        putInt(GROUPED_HITS_LENGTH, groupedHitsLength);
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
     *         container, if this is not setted we return 0;
     */
    public long length() {
        if (get(LENGTH) != null) {
            return getLong(LENGTH);
        }
        return 0;
    }

    /**
     * @return the plugId
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

    /**
     * @return true if hits are ranked.
     */
    public boolean isRanked() {
        if (get(RANKED) != null) {
            return getBoolean(RANKED);
        }
        return false;
    }
    
    /**
     * @param ranked
     */
    public void setRanked(boolean ranked) {
        putBoolean(RANKED,ranked);
    }

    /**
     * @return the number of hits which has been used by grouping to create this
     *         hit container
     */
    public int getGoupedHitsLength() {
        if (containsKey(GROUPED_HITS_LENGTH)) {
            return getInt(GROUPED_HITS_LENGTH);
        }
        return 0;
    }

    /**
     * @return the number of different iplugs the hits come from
     */
    public int getInVolvedPlugs() {
        if (containsKey(INVOLVED_PLUGS)) {
            return getInt(INVOLVED_PLUGS);
        }
        return 0;
    }

    /**
     * @param howMany
     */
    public void setInVolvedPlugs(int howMany) {
        putInt(INVOLVED_PLUGS, howMany);
    }
}
