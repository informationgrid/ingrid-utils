/*
 * Copyright 2004-2005 weta group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 *  $Source:  $
 */

package de.ingrid.utils;

import java.util.Arrays;

import de.ingrid.utils.query.ClauseQuery;
import de.ingrid.utils.query.IngridQuery;
import junit.framework.TestCase;

/**
 * Test for {@link de.ingrid.utils.query.IngridQuery} 
 * 
 * <p/>created on 30.05.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 *  
 */
public class IngridQueryTest extends TestCase {

    /**
     * 
     */
    public void testAllClauses(){
        IngridQuery query=new IngridQuery(true,true,1,"clause0");
        assertEquals(1, query.getAllClauses().length);
        ClauseQuery clause1=new ClauseQuery(true,true);
        ClauseQuery clause2=new ClauseQuery(true,true);
        query.addClause(clause1);
        query.addClause(clause2);
        assertEquals(3, query.getAllClauses().length);
        assertTrue(Arrays.asList(query.getAllClauses()).contains(clause1));
        assertTrue(Arrays.asList(query.getAllClauses()).contains(clause2));
        
        ClauseQuery clause11=new ClauseQuery(true,true);
        clause1.addClause(clause11);
        assertEquals(4, query.getAllClauses().length);
        assertTrue(Arrays.asList(query.getAllClauses()).contains(clause11));
        
    }
}
