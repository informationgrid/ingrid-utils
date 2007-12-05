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
     * Constant for topic association type.
     */
    public static final String TOPIC_ASSOCIATION_TYPE = "topicAssociationType";

    /**
     * 
     */
    public static final int TOPIC_FROM_URL = 7;

    private static final String TOPIC_NATIVE_KEY = "topicNativeKey";

    public Topic() {
        super();
    }
    
    /**
     * @param plugId
     * @param documentId
     * @param topicId
     * @param title
     *            the name of the topic
     * @param summary
     */
    public Topic(String plugId, int documentId, String topicId, String title, String summary, String nativeKey) {
        super(plugId, documentId, 0, /* we have only one sns */
        -1f/* topics are unranked==no scores */, title, summary);
        setTopicID(topicId);
        setTopicName(title);
        setTopicNativeKey(nativeKey);
    }


    /**
     * @param plugId
     * @param documentId
     * @param topicId
     * @param title
     *            the name of the topic
     * @param summary
     * @param associationType
     */
    public Topic(String plugId, int documentId, String topicId, String title, String summary, String associationType, String nativeKey) {
        super(plugId, documentId, 0, /* we have only one sns */
        -1f/* topics are unranked==no scores */, title, summary);
        setTopicID(topicId);
        setTopicName(title);
        setTopicAssoc(associationType);
        setTopicNativeKey(nativeKey);
    }

    public void setTopicNativeKey(String nativeKey) {
        put(TOPIC_NATIVE_KEY, nativeKey);
    }
    
    public String getTopicNativeKey() {
        return (String) get(TOPIC_NATIVE_KEY);
    }

    /**
     * @param associationType
     */
    public void setTopicAssoc(String associationType) {
        put(TOPIC_ASSOCIATION_TYPE, associationType);
    }

    /**
    public Topic() {
        super();
    }

    /**
     * @return The association type.
     */
    public String getTopicAssoc() {
        return (String) get(TOPIC_ASSOCIATION_TYPE);
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