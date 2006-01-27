/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

/**
 * An container for iplug hits This container does not contain any document details just the information that are
 * required to rank all hits
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class IngridHit extends IngridDocument {

    private static final String PROVIDER = "provider";

    private static final String SCORE = "score";

    private static final String DATA_SOURCE_ID = "dataSourceId";

    private static final String IPLUG_ID = "iPlugId";

    /**
     * 
     */
    public IngridHit() {
        // default constructor for serialization
    }

    /**
     * @param provider
     * @param documentId
     * @param dataSourceId
     * @param score
     */
    public IngridHit(String provider, int documentId, int dataSourceId, float score) {
        // FIXME: documentId mismatch here an int in the parent a serializable; this isn't consistent
        setProvider(provider);
        setDocumentId(documentId);
        setDataSourceId(dataSourceId);
        setScore(score);
    }

    /**
     * sets a id of the internal datasource split
     * 
     * @param dataSourceId
     */
    public void setDataSourceId(int dataSourceId) {
        putInt(DATA_SOURCE_ID, dataSourceId);

    }

    /**
     * @return A data source id, that is used internaly for one datasource.
     */
    public int getDataSourceId() {
        return getInt(DATA_SOURCE_ID);
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

    /**
     * @return a provider specific document id
     */
    public int getDocumentId() {
        return getInt(DOCUMENT_ID);
    }

    /**
     * sets a provider specific document id
     * 
     * @param documentId
     */
    public void setDocumentId(int documentId) {
        putInt(DOCUMENT_ID, documentId);
    }

    /**
     * Sets the score of a document. This score must be between 0 and 1.
     * 
     * @param score
     *            Range from 0 to 1.
     */
    public void setScore(float score) {
        putFloat(SCORE, score);
    }

    /**
     * @return The score of the current document from 0 to 1.
     * 
     */
    public float getScore() {
        return getFloat(SCORE);
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
