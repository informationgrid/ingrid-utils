/*
 * 
 * $Source: /cvs/SiemensPI/ms_codetemplates.xml,v $
 */

package de.ingrid.utils.processor;

import java.io.Serializable;
import java.util.ArrayList;

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

    private static Log log = LogFactory.getLog(ProcessorPipe.class);

    private ArrayList fPreProcessors = new ArrayList();

    private ArrayList fPostProcessors = new ArrayList();

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
            if (log.isTraceEnabled()) {
                log.trace(preProcessors[i].getClass().getName() + " execution time (ms): "
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
            if (log.isTraceEnabled()) {
                log.trace(postProcessors[i].getClass().getName() + " execution time (ms): "
                        + (System.currentTimeMillis() - start));
            }
        }
    }

}
