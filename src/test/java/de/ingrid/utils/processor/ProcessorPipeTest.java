/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2015 wemove digital solutions GmbH
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
 * Copyright (c) 2003 by media style GmbH
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import junit.framework.TestCase;
import de.ingrid.utils.IngridDocument;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.queryparser.QueryStringParser;

public class ProcessorPipeTest extends TestCase {

    public void testAddPreProcessor() throws Exception {
        ProcessorPipe pipe = new ProcessorPipe();
        pipe.addPreProcessor(new DummyPreProcessor());
        IPreProcessor[] preProcessors = pipe.getPreProcessors();
        assertEquals(1, preProcessors.length);
        assertTrue(preProcessors[0] instanceof DummyPreProcessor);
    }

    public void testRemovePreProcessor() throws Exception {
        ProcessorPipe pipe = new ProcessorPipe();
        DummyPreProcessor processor = new DummyPreProcessor();
        pipe.addPreProcessor(processor);
        IPreProcessor[] preProcessors = pipe.getPreProcessors();
        assertEquals(1, preProcessors.length);
        assertTrue(preProcessors[0] instanceof DummyPreProcessor);
        pipe.removePreProcessor(processor);
        assertEquals(0, pipe.getPreProcessors().length);

    }

    public void testPreprocess() throws Exception {
        ProcessorPipe pipe = new ProcessorPipe();
        pipe.addPreProcessor(new DummyPreProcessor());
        IngridQuery query = QueryStringParser.parse("test");
        assertNull(query.get("marker"));
        pipe.preProcess(query);
        assertNotNull(query.get("marker"));
    }

    public void testAddPostProcessor() throws Exception {
        ProcessorPipe pipe = new ProcessorPipe();
        pipe.addPostProcessor(new DummyPostProcessor());
        IPostProcessor[] postProcessors = pipe.getPostProcessors();
        assertEquals(1, postProcessors.length);
        assertTrue(postProcessors[0] instanceof DummyPostProcessor);
    }

    public void testRemovePostProcessor() throws Exception {
        ProcessorPipe pipe = new ProcessorPipe();
        DummyPostProcessor processor = new DummyPostProcessor();
        pipe.addPostProcessor(processor);
        IPostProcessor[] postProcessors = pipe.getPostProcessors();
        assertEquals(1, postProcessors.length);
        assertTrue(postProcessors[0] instanceof DummyPostProcessor);
        pipe.removePostProcessor(processor);
        assertEquals(0, pipe.getPostProcessors().length);

    }

    public void testPostprocess() throws Exception {
        ProcessorPipe pipe = new ProcessorPipe();
        pipe.addPostProcessor(new DummyPostProcessor());
        IngridDocument document = new IngridDocument();
        pipe.postProcess(new IngridQuery(), new IngridDocument[]{document});
        assertNotNull(document.get("marker"));
    }

}
