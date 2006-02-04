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
public class DetailedTopicTest extends TestCase {
    private static final String ADMIN = "KREIS065";
    private static final String TYPE = "anyType";
    private static final String TO = "2005";
    private static final String FROM = "2003";
    
    /**
     * 
     */
    public void testGetter(){
        DetailedTopic metaData = new DetailedTopic(null, null,null);
        valid(null, metaData.getFrom());
        valid(null, metaData.getTo());
        valid(null, metaData.getType());
        valid(null, metaData.getAdministrativeID());
        metaData.put(DetailedTopic.FROM, FROM);
        metaData.put(DetailedTopic.TO, TO);
        metaData.put(DetailedTopic.TYPE, TYPE);
        metaData.put(DetailedTopic.ADMINISTRATIVE_ID, ADMIN);
        valid(FROM, metaData.getFrom());
        valid(TO, metaData.getTo());
        valid(TYPE, metaData.getType());
        valid(ADMIN, metaData.getAdministrativeID());
    }
    
    /**
     * 
     */
    public void testSetter(){
        DetailedTopic metaData = new DetailedTopic(null, null, null);
        // tests initial setting
        metaData.setFrom(FROM);
        metaData.setTo(TO);
        metaData.setType(TYPE);
        metaData.setAdministrativeID(ADMIN);
        valid(FROM, metaData.get(DetailedTopic.FROM));
        valid(TO, metaData.get(DetailedTopic.TO));
        valid(TYPE, metaData.get(DetailedTopic.TYPE));
        valid(ADMIN, metaData.get(DetailedTopic.ADMINISTRATIVE_ID));
        
        //tests owerwriting
        final String newFrom = "1999";
        final String newTo = "2000";
        final String newType = "newType";
        final String newAdmin = "GEMEINDE098";
        metaData.setFrom(newFrom);
        metaData.setTo(newTo);
        metaData.setType(newType);
        metaData.setAdministrativeID(newAdmin);
        valid(newFrom, metaData.get(DetailedTopic.FROM));
        valid(newTo, metaData.get(DetailedTopic.TO));
        valid(newType, metaData.get(DetailedTopic.TYPE));
        valid(newAdmin, metaData.get(DetailedTopic.ADMINISTRATIVE_ID));
    }
    
    /**
     * @param expected
     * @param real
     */
    private void valid(Object expected, Object real){
        assertEquals(expected, real);
    }

}
