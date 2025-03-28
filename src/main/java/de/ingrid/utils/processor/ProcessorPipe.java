/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
/*
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.IngridDocument;
import de.ingrid.utils.query.IngridQuery;

/**
 * Pipe that preprocess query objectes and post process document objects
 * 
 * created on 20.09.2005
 * 
 * @author sg
 * @version $Revision: 1.2 $
 * 
 */
public class ProcessorPipe implements Serializable {

    private static final long serialVersionUID = 6L;

    private static Log log = LogFactory.getLog(ProcessorPipe.class);

    private List<IPreProcessor> fPreProcessors = new ArrayList<IPreProcessor>();

    private List<IPostProcessor> fPostProcessors = new ArrayList<IPostProcessor>();

    /**
     */
    public ProcessorPipe() {
        // for serialization
    }

    /**
     * Adds a pre processors
     * 
     * @param processor
     */
    public void addPreProcessor(IPreProcessor processor) {
        if (log.isDebugEnabled()) {
            log.debug("add preprocessor: " + processor.getClass().getName());
        }
        this.fPreProcessors.add(processor);
    }

    /**
     * @return all registered pre processors
     */
    public IPreProcessor[] getPreProcessors() {
        return (IPreProcessor[]) this.fPreProcessors.toArray(new IPreProcessor[this.fPreProcessors.size()]);
    }

    /**
     * pre processes the query, we directly manipulate the query parameter..
     * 
     * @param query
     * @throws Exception
     */
    public void preProcess(IngridQuery query) throws Exception {
        IPreProcessor[] preProcessors = getPreProcessors();
        for (int i = 0; i < preProcessors.length; i++) {
            if (log.isDebugEnabled()) {
                log.debug("run preprocessor: " + preProcessors[i].getClass().getName());
            }
            long start = System.currentTimeMillis();
            preProcessors[i].process(query);
            if (log.isDebugEnabled()) {
                log.debug(preProcessors[i].getClass().getName() + " execution time (ms): "
                        + (System.currentTimeMillis() - start));
            }
        }
    }

    /**
     * Removes a pre processor
     * 
     * @param processor
     */
    public void removePreProcessor(IPreProcessor processor) {
        if (log.isDebugEnabled()) {
            log.debug("remove preprocessor: " + processor.getClass().getName());
        }
        this.fPreProcessors.remove(processor);
    }

    /**
     * Adds a post processor
     * 
     * @param processor
     */
    public void addPostProcessor(IPostProcessor processor) {
        if (log.isDebugEnabled()) {
            log.debug("add postprocessor: " + processor.getClass().getName());
        }
        this.fPostProcessors.add(processor);
    }

    /**
     * @return all registed post processors
     */
    public IPostProcessor[] getPostProcessors() {
        return (IPostProcessor[]) this.fPostProcessors.toArray(new IPostProcessor[this.fPostProcessors.size()]);
    }

    /**
     * removes a post processor
     * 
     * @param processor
     */
    public void removePostProcessor(IPostProcessor processor) {
        if (log.isDebugEnabled()) {
            log.debug("remove postprocessor: " + processor.getClass().getName());
        }
        this.fPostProcessors.remove(processor);
    }

    /**
     * post processes the query, we directly manipulate the query parameter..
     * 
     * @param ingridQuery
     * @param documents
     * @throws Exception
     */
    public void postProcess(IngridQuery ingridQuery, IngridDocument[] documents) throws Exception {
        IPostProcessor[] postProcessors = getPostProcessors();
        for (int i = 0; i < postProcessors.length; i++) {
            if (log.isDebugEnabled()) {
                log.debug("run postprocessor: " + postProcessors[i].getClass().getName());
            }
            long start = System.currentTimeMillis();
            postProcessors[i].process(ingridQuery, documents);
            if (log.isDebugEnabled()) {
                log.debug(postProcessors[i].getClass().getName() + " execution time (ms): "
                        + (System.currentTimeMillis() - start));
            }
        }
    }

}
