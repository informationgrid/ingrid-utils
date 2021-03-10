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
package de.ingrid.utils.processor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.IConfigurable;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.tool.SpringUtil;

public class ProcessorPipeFactory {

    private final Class<List<IPreProcessor>> _preProcessors = null;

    private final Class<List<IPostProcessor>> _postProcessors = null;

    private final PlugDescription _plugDescription;

    private static final Log LOG = LogFactory.getLog(ProcessorPipeFactory.class);

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
        if (postProcessors != null) {
            for (IPostProcessor postProcessor : postProcessors) {
                if (postProcessor instanceof IConfigurable) {
                    ((IConfigurable) postProcessor).configure(_plugDescription);
                }
                LOG.info("add post-processor: " + postProcessor.getClass().getName());
                processorPipe.addPostProcessor(postProcessor);
            }
        }
    }

    private void pushPreProcessors(ProcessorPipe processorPipe, SpringUtil springUtil) {
        List<IPreProcessor> preProcessors = springUtil.getBean("preProcessors", _preProcessors);
        if (preProcessors != null) {
            for (IPreProcessor preProcessor : preProcessors) {
                if (preProcessor instanceof IConfigurable) {
                    ((IConfigurable) preProcessor).configure(_plugDescription);
                }
                LOG.info("add pre-processor: " + preProcessor.getClass().getName());
                processorPipe.addPreProcessor(preProcessor);
            }
        }
    }
}
