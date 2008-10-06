/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.util.ArrayList;

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

    
    private static final long serialVersionUID = 3L;

    private static final String SCORE = "score";

    private static final String DATA_SOURCE_ID = "dataSourceId";

    private static final String IPLUG_ID = "iPlugId";

    private static final String GROUPED_BY_FIELD = "groupedBy";

    private static final String GROUP_HIT = "groupHit";
    
    private static final String DATE = "date";
    
    private static final String GROUP_TOTAL_HIT_LENGTH = "groupTotalHitLength";

	private static final String HIT_ID = "hitId";

    /**
     * 
     */
    public IngridHit() {
        // default constructor for serialization
    }

    /**
     * @param plugId
     * @param documentId
     * @param dataSourceId
     * @param score
     */
    public IngridHit(String plugId, int documentId, int dataSourceId, float score) {
        // FIXME: documentId mismatch here an int in the parent a serializable; this isn't consistent
        setPlugId(plugId);
        setDocumentId(documentId);
        setDataSourceId(dataSourceId);
        setScore(score);
        setDate(0);
    }
    
    
    /**
     * @param plugId
     * @param documentId
     * @param dataSourceId
     * @param date
     */
    public IngridHit(String plugId, int documentId, int dataSourceId, int date) {
      // FIXME: documentId mismatch here an int in the parent a serializable; this isn't consistent
      setPlugId(plugId);
      setDocumentId(documentId);
      setDataSourceId(dataSourceId);
      setDate(date);
      setScore(0.0f);
  }
    

    /**
     * @param date
     */
    private void setDate(int date) {
      putInt(DATE, date);
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
     * @return the id of plug
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
     * sets the value that used to group hits
     * @param value
     */
    public void addGroupedField(String value) {
        addToList(GROUPED_BY_FIELD, value);
    }
    
    /**
     * @return values that is used to group hits
     */
    public String[] getGroupedFields(){
      ArrayList arrayList = getArrayList(GROUPED_BY_FIELD);
      if(arrayList!=null){
          return (String[]) arrayList.toArray(new String[arrayList.size()]);
      }
      return null;
    }

    /**
     * adds a hit to the group of this hit
     * @param hit
     */
    public void addGroupHit(IngridHit hit) {
        addToList(GROUP_HIT, hit);
    }
    
    /**
     * @return all members of this group
     */
    public IngridHit[] getGroupHits() {
        ArrayList arrayList = getArrayList(GROUP_HIT);
        if(arrayList==null){
            arrayList = new ArrayList();
        }
        return (IngridHit[]) arrayList.toArray(new IngridHit[arrayList.size()]);
    }

    public int getGroupTotalHitLength() {
        return getInt(GROUP_TOTAL_HIT_LENGTH);
    }

    public void setGroupTotalHitLength(int groupTotalHitLength) {
        putInt(GROUP_TOTAL_HIT_LENGTH, groupTotalHitLength);
    }
    
    public void setHitId(String id) {
		put(HIT_ID, id);
	}

	public String getHitId() {
		return (String) get(HIT_ID);
	}

	public int hashCode() {
		return containsKey(HIT_ID) ? getHitId().hashCode() : super.hashCode();
	}

	public boolean equals(Object o) {
		IngridHit otherHit = (IngridHit) o;
		return containsKey(HIT_ID) ? getHitId().equals(otherHit.getHitId())
				: super.equals(o);
	}
}
