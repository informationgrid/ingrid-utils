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

package de.ingrid.utils.query;

import junit.framework.TestCase;

/**
 * IngridQueryTest 
 * 
 * <p/>created on 20.10.2005
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 *  
 */
public class IngridQueryTest extends TestCase {

    /**
     * 
     * @throws Exception
     */
    public void testQuery() throws Exception {
        IngridQuery query = new IngridQuery();
        query.addClause(new ClauseQuery(IngridQuery.AND));
        assertEquals(1,query.getClauses().length);
        
        query=new IngridQuery(IngridQuery.TERM, IngridQuery.AND, "content");
        assertEquals(IngridQuery.TERM,query.getType());
        assertEquals("content",query.getContent());
        assertEquals(IngridQuery.AND,query.getOperation());
    }
}
