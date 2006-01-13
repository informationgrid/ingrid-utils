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

	private static final String DATA_SOURCE_ID = "dataSourceId";

	public IngridHit() {
		// default constructor for serialization
	}

	public IngridHit(String provider, int documentId, int dataSourceId,
			float score) {
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
	 * @return a data source id, that is used interanaly for one datasource
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
