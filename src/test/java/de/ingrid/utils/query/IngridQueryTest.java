/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2016 wemove digital solutions GmbH
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

import de.ingrid.utils.queryparser.QueryStringParser;
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
        query.addClause(new ClauseQuery(true, false));
        assertEquals(1, query.getClauses().length);

        query = new IngridQuery(true, false, IngridQuery.TERM, "content");
        assertEquals(IngridQuery.TERM, query.getType());
        assertEquals("content", query.getContent());
        assertEquals(true, query.isRequred());
    }

    /**
     * @throws Exception
     */
    public void testRemoveDataTypes() throws Exception {
        IngridQuery query = new IngridQuery();
        FieldQuery dataType = new FieldQuery(true, false, IngridQuery.DATA_TYPE, "bla");
        query.addField(dataType);
        assertEquals(1, query.getDataTypes().length);
        query.removeDataType("bla");
        assertEquals(0, query.getDataTypes().length);
    }

    /**
     * @throws Exception
     */
    public void testRemoveClause() throws Exception {
        IngridQuery query = new IngridQuery();
        ClauseQuery clause = new ClauseQuery(false, true);
        query.addClause(clause);
        query.removeClause(clause);
        assertEquals(0, query.getClauses().length);
    }

    /**
     * @throws Exception
     */
    public void testDataTypes() throws Exception {
        IngridQuery query = new IngridQuery(true, true, IngridQuery.TERM, "ba");
        assertTrue(query.isProhibited());

        query = new IngridQuery();
        FieldQuery fieldQuery = new FieldQuery(true, true, "datatype", "UDK");
        assertTrue(fieldQuery.isProhibited());

        query.addField(fieldQuery);
        assertEquals(1, query.getDataTypes().length);
        assertTrue(query.getDataTypes()[0].isProhibited());

        query = QueryStringParser.parse("bla datatype:a");
        assertEquals(1, query.getDataTypes().length);
        assertFalse(query.getDataTypes()[0].isProhibited());

        query = QueryStringParser.parse("bla -datatype:a");
        assertEquals(1, query.getDataTypes().length);
        assertTrue(query.getDataTypes()[0].isProhibited());

        query = QueryStringParser.parse("(bla (-datatype:a) datatype:a)");
        assertEquals(2, query.getDataTypes().length);
        assertEquals(1, query.getNegativeDataTypes().length);
        assertEquals(1, query.getPositiveDataTypes().length);
    }

    /**
     * @throws Exception
     */
    public void testProvider() throws Exception {
        IngridQuery query = new IngridQuery();
        assertEquals(query.getPositiveProvider().length, 0);
        query.addField(new FieldQuery(true, false, "provider", "anhalt"));
        assertEquals(query.getPositiveProvider().length, 1);
    }

    /**
     * @throws Exception
     */
    public void testIPugs() throws Exception {
        IngridQuery query = new IngridQuery();
        assertEquals(query.getIPlugs().length, 0);
        query.addField(new FieldQuery(true, false, IngridQuery.IPLUGS, "plugOne"));
        assertEquals(query.getIPlugs().length, 1);
    }
}
