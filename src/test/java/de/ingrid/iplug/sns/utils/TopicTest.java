/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */
package de.ingrid.iplug.sns.utils;

import junit.framework.TestCase;

/**
 * 
 * created on 21.07.2005 <p>
 *
 * @author hs
 */
public class TopicTest extends TestCase {
    private static final String NAME = "name";
    private static final String ID = "id";
    /**
     * 
     */
    public void testTopic(){
        Topic topic = new Topic(null, 0, null, null, null, null);
        assertNotNull(topic);
        valid(null, topic.get(Topic.TOPIC_ID));
        valid(null, topic.get(Topic.TOPIC_NAME));
        topic = new Topic(null, 0, ID, null, null, null);
        assertNotNull(topic);
        valid(ID, topic.get(Topic.TOPIC_ID));
        topic = new Topic(null,0,  ID, NAME, null, null);
        assertNotNull(topic);
        valid(NAME, topic.get(Topic.TOPIC_NAME)); 
    }
    /**
     * 
     */
    public void testGetter(){
        Topic topic = new Topic(null, 0, null, null, null, null);
        valid(null, topic.getTopicID());
        valid(null, topic.getTopicName());
        topic = new Topic(null, 0, ID, null, null, null);
        valid(ID, topic.getTopicID());
        topic = new Topic(null,0, ID, NAME, null, null);
        valid(NAME, topic.getTopicName()); 
    }
    
    /**
     * 
     */
    public void testSetter(){
        // tests initial setting
        Topic topic = new Topic(null, 0,null, null, null, null);
        topic.setTopicID(ID);
        topic.setTopicName(NAME);
        valid(ID, topic.get(Topic.TOPIC_ID));
        valid(NAME, topic.get(Topic.TOPIC_NAME));
        
        // tests owerwriting
        final String newID = "newID";
        final String newName = "newName";
        topic.setTopicID(newID);
        topic.setTopicName(newName);
        valid(newID, topic.get(Topic.TOPIC_ID));
        valid(newName, topic.get(Topic.TOPIC_NAME)); 
    }
    
    /**
     * @param expected
     * @param real
     */
    private void valid(Object expected, Object real){
        assertEquals(expected, real);
    }
    

}