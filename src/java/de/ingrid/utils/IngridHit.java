/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

/**
 * An container for iplug hits This container does not contain any document
 * details just the information that are required to rank all hits
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class IngridHit extends IngridDocument {

    private static final String PROVIDER = "provider";

    private static final String SCORE = "score";

    public IngridHit() {
        // default constructor for serialization
    }

    public IngridHit(String provider, int documentId, float score) {
        setProvider(provider);
        setDocumentId(documentId);
        setScore(score);
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
     * Sets the score of a document
     * 
     * @param score
     */
    public void setScore(float score) {
        putFloat(SCORE, score);
    }

    /**
     * @return the score of this document
     * 
     */
    public float getScore() {
        return getFloat(SCORE);

    }
}
