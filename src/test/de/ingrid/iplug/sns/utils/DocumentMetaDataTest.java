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
public class DocumentMetaDataTest extends TestCase {
    private static final String ADMIN = "KREIS065";
    private static final String TYPE = "anyType";
    private static final String TO = "2005";
    private static final String FROM = "2003";
    
    /**
     * 
     */
    public void testGetter(){
        DocumentMetaData metaData = new DocumentMetaData(null, null);
        valid(null, metaData.getFrom());
        valid(null, metaData.getTo());
        valid(null, metaData.getType());
        valid(null, metaData.getAdministrativeID());
        metaData.put(DocumentMetaData.FROM, FROM);
        metaData.put(DocumentMetaData.TO, TO);
        metaData.put(DocumentMetaData.TYPE, TYPE);
        metaData.put(DocumentMetaData.ADMINISTRATIVE_ID, ADMIN);
        valid(FROM, metaData.getFrom());
        valid(TO, metaData.getTo());
        valid(TYPE, metaData.getType());
        valid(ADMIN, metaData.getAdministrativeID());
    }
    
    /**
     * 
     */
    public void testSetter(){
        DocumentMetaData metaData = new DocumentMetaData(null, null);
        // tests initial setting
        metaData.setFrom(FROM);
        metaData.setTo(TO);
        metaData.setType(TYPE);
        metaData.setAdministrativeID(ADMIN);
        valid(FROM, metaData.get(DocumentMetaData.FROM));
        valid(TO, metaData.get(DocumentMetaData.TO));
        valid(TYPE, metaData.get(DocumentMetaData.TYPE));
        valid(ADMIN, metaData.get(DocumentMetaData.ADMINISTRATIVE_ID));
        
        //tests owerwriting
        final String newFrom = "1999";
        final String newTo = "2000";
        final String newType = "newType";
        final String newAdmin = "GEMEINDE098";
        metaData.setFrom(newFrom);
        metaData.setTo(newTo);
        metaData.setType(newType);
        metaData.setAdministrativeID(newAdmin);
        valid(newFrom, metaData.get(DocumentMetaData.FROM));
        valid(newTo, metaData.get(DocumentMetaData.TO));
        valid(newType, metaData.get(DocumentMetaData.TYPE));
        valid(newAdmin, metaData.get(DocumentMetaData.ADMINISTRATIVE_ID));
    }
    
    /**
     * @param expected
     * @param real
     */
    private void valid(Object expected, Object real){
        assertEquals(expected, real);
    }

}
