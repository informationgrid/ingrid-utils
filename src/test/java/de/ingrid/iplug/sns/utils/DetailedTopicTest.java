/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
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
        DetailedTopic metaData = new DetailedTopic(null, 0, null, null, null, null);
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
        DetailedTopic metaData = new DetailedTopic(null, 0, null, null, null, null);
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
