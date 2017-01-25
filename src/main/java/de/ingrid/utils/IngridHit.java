/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2017 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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

    @Deprecated
	private static final String HIT_ID = "hitId";
	
	private static final String HIT_DETAIL = "hitDetail";

	private static final String DUMMY_HIT = "dummyHit";

    /**
     * 
     */
    public IngridHit() {
        // default constructor for serialization
        setDate(0);
    }

    /**
     * @param plugId
     * @param documentId
     * @param dataSourceId
     * @param score
     */
    @Deprecated
    public IngridHit(String plugId, int documentId, int dataSourceId, float score) {
        // FIXME: documentId mismatch here an int in the parent a serializable; this isn't consistent
        setPlugId(plugId);
        setDocumentId( documentId );
        setDataSourceId(dataSourceId);
        setScore(score);
        setDate(0);
    }
    
    public IngridHit(String plugId, String documentId, int dataSourceId, float score) {
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
    @Deprecated
    public IngridHit(String plugId, int documentId, int dataSourceId, int date) {
        // FIXME: documentId mismatch here an int in the parent a serializable; this isn't consistent
        setPlugId(plugId);
        setDocumentId(documentId);
        setDataSourceId(dataSourceId);
        setDate(date);
        setScore(0.0f);
    }
    
    public IngridHit(String plugId, String documentId, int dataSourceId, int date) {
        // FIXME: documentId mismatch here an int in the parent a serializable; this isn't consistent
        setPlugId(plugId);
        //setDocumentId(documentId);
        setDataSourceId(dataSourceId);
        setDate(date);
        setScore(0.0f);
    }
    //

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
     * Deprecated: Use getDocumentUId() instead.
     * @return a provider specific document id
     */
    public String getDocumentId() {
        return String.valueOf( get(DOCUMENT_ID) );
    }
    
    /**
     * Get the unique ID of the document.
     * @return the ID as a string
     */
    public String getDocumentUId() {
        return getString(DOCUMENT_UID);
    }

    /**
     * Deprecated: sets a provider specific document id
     * Use setDocumentId(String) instead.
     * 
     * @param documentId
     */
    @Deprecated
    public void setDocumentId(int documentId) {
        put(DOCUMENT_ID, String.valueOf( documentId));
    }
    
    /**
     * Set the ID of a document, which will be used to identify it.
     * @param documentUId is the unique ID
     */
    public void setDocumentId(String documentId) {
        put(DOCUMENT_ID, documentId);
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
      List<?> arrayList = getArrayList(GROUPED_BY_FIELD);
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
        List<?> arrayList = getArrayList(GROUP_HIT);
        if(arrayList==null){
            arrayList = new ArrayList<Object>();
        }
        return (IngridHit[]) arrayList.toArray(new IngridHit[arrayList.size()]);
    }

    public int getGroupTotalHitLength() {
        return getInt(GROUP_TOTAL_HIT_LENGTH);
    }

    public void setGroupTotalHitLength(int groupTotalHitLength) {
        putInt(GROUP_TOTAL_HIT_LENGTH, groupTotalHitLength);
    }
    
    /**
     * Set the HitID.
     * @deprecated The HitId is never used, instead use documentId!
     */
    @Deprecated
    public void setHitId(String id) {
		put(HIT_ID, id);
	}

    /**
     * Get the HitID.
     * @deprecated The HitId is never used, instead use documentId!
     */
    @Deprecated
	public String getHitId() {
		return (String) get(HIT_ID);
	}

	public void setHitDetail(IngridHitDetail hitDetail) {
        put(HIT_DETAIL, hitDetail);
    }

    public IngridHitDetail getHitDetail() {
        return (IngridHitDetail) get(HIT_DETAIL);
    }
	
	public void setDummyHit(boolean isDummyHit) {
        putBoolean(DUMMY_HIT, isDummyHit);
    }

    public boolean isDummyHit() {
    	if (get(DUMMY_HIT) != null) {
    		return getBoolean(DUMMY_HIT);
    	}
        return false;
    }
	
	public int hashCode() {
		return containsKey(DOCUMENT_ID) ? calcHashFromId( getDocumentId() ) : super.hashCode();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private int calcHashFromId(String id) {
	    int hash = 0;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(id.getBytes());
            hash = new String(messageDigest.digest()).hashCode();
        } catch (NoSuchAlgorithmException e) {
            hash = id.hashCode();
        }
        return hash;
	}

	public boolean equals(Object o) {
		IngridHit otherHit = (IngridHit) o;
		return containsKey(DOCUMENT_ID) ? getDocumentId().equals(otherHit.getDocumentId())
				: super.equals(o);
	}
}
