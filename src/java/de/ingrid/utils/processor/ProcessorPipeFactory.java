package de.ingrid.utils.processor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import de.ingrid.utils.IConfigurable;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.tool.SpringUtil;

public class ProcessorPipeFactory {

    private final Class<List<IPreProcessor>> _preProcessors = null;

    private final Class<List<IPostProcessor>> _postProcessors = null;

    private final PlugDescription _plugDescription;

    private static final Log LOG = LogFactoryImpl.getLog(ProcessorPipeFactory.class);

    public ProcessorPipeFactory(PlugDescription plugDescription) {
        _plugDescription = plugDescription;
    }

    public ProcessorPipe getProcessorPipe() {
        ProcessorPipe processorPipe = new ProcessorPipe();
        SpringUtil springUtil = new SpringUtil("spring/spring.xml");
        pushPreProcessors(processorPipe, springUtil);
        pushPostProcessors(processorPipe, springUtil);
        return processorPipe;
    }

    private void pushPostProcessors(ProcessorPipe processorPipe, SpringUtil springUtil) {
        List<IPostProcessor> postProcessors = springUtil.getBean("postProcessors", _postProcessors);
        for (IPostProcessor postProcessor : postProcessors) {
            if (postProcessor instanceof IConfigurable) {
                ((IConfigurable) postProcessor).configure(_plugDescription);
            }
            LOG.info("add post-processor: " + postProcessor.getClass().getName());
            processorPipe.addPostProcessor(postProcessor);
        }
    }

    private void pushPreProcessors(ProcessorPipe processorPipe, SpringUtil springUtil) {
        List<IPreProcessor> preProcessors = springUtil.getBean("preProcessors", _preProcessors);
        for (IPreProcessor preProcessor : preProcessors) {
            if (preProcessor instanceof IConfigurable) {
                ((IConfigurable) preProcessor).configure(_plugDescription);
            }
            LOG.info("add pre-processor: " + preProcessor.getClass().getName());
            processorPipe.addPreProcessor(preProcessor);
        }
    }
}
