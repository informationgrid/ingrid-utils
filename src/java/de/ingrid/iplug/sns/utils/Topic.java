/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */
package de.ingrid.iplug.sns.utils;

import de.ingrid.utils.IngridDocument;

/**
 * 
 * created on 21.07.2005
 * <p>
 * 
 * @author hs
 */
public class Topic extends IngridDocument {
    private static final long serialVersionUID = 12L;

    /**
     * Constant for topicID.
     */
    public static final String TOPIC_ID = "topicID";

    /**
     * Constant for topicName.
     */
    public static final String TOPIC_NAME = "topicName";

    /**
     * @param topicID
     * @param topicName
     */
    public Topic(String topicID, String topicName) {
        put(TOPIC_ID, topicID);
        put(TOPIC_NAME, topicName);
    }
    
    /**
     * 
     */
    public Topic(){
        super();
    }

    /**
     * @return Returns the topicID.
     */
    public String getTopicID() {
        return (String) get(TOPIC_ID);
    }

    /**
     * @return Returns the topicName.
     */
    public String getTopicName() {
        return (String) get(TOPIC_NAME);
    }
    
    /**
     * 
     * @param topicID
     */
    public void setTopicID(String topicID){
        put(TOPIC_ID, topicID);
    }
    
    /**
     * 
     * @param topicName
     */
    public void setTopicName(String topicName){
        put(TOPIC_NAME, topicName);
    }
    
    /**
     * @return The topicName if this attribute is set.
     */
    public String toString() {
        return getTopicName();
    }

}