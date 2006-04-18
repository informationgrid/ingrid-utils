/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */
package de.ingrid.iplug.sns.utils;

import de.ingrid.utils.IngridHitDetail;

/**
 * 
 * created on 21.07.2005
 * <p>
 * 
 * @author hs
 */
public class Topic extends IngridHitDetail {

    /**
     * 
     */
    public static final String REQUEST_TYPE = "sns_request_type";

    /**
     * 
     */
    public static final int TOPIC_FROM_TERM = 0;

    /**
     * 
     */
    public static final int TOPIC_FROM_TEXT = 1;

    /**
     * 
     */
    public static final int TOPIC_FROM_TOPIC = 2;

    /**
     * 
     */
    public static final int ANNIVERSARY_FROM_TOPIC = 3;

    /**
     * 
     */
    public static final int EVENT_FROM_TOPIC = 4;

    /**
     * 
     */
    public static final int SIMILARTERMS_FROM_TOPIC = 5;

    /**
     * 
     */
    public static final int SIMILARLOCATIONS_FROM_TOPIC = 6;

    private static final long serialVersionUID = Topic.class.getName().hashCode();

    /**
     * Constant for topicID.
     */
    public static final String TOPIC_ID = "topicID";

    /**
     * Constant for topicName.
     */
    public static final String TOPIC_NAME = "topicName";

    /**
     * @param plugId
     * @param documentId
     * @param topicId
     * @param title
     *            the name of the topic
     * @param summary
     */
    public Topic(String plugId, int documentId, String topicId, String title, String summary) {
        super(plugId, documentId, 0, /* we have only one sns */
        -1f/* topics are unranked==no scores */, title, summary);
        setTopicID(topicId);
        setTopicName(title);
    }

    /**
     * 
     */
    public Topic() {
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
    public void setTopicID(String topicID) {
        put(TOPIC_ID, topicID);
    }

    /**
     * 
     * @param topicName
     */
    public void setTopicName(String topicName) {
        put(TOPIC_NAME, topicName);
    }

    /**
     * @return The topicName if this attribute is set.
     */
    public String toString() {
        return getTopicName();
    }

}