/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
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
package de.ingrid.utils.metadata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.IBus;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.tool.SpringUtil;

public class MetadataInjectorFactory {

    private final PlugDescription _description;

    private final IBus _bus;

    private final Class<List<IMetadataInjector>> _injectorContainer = null;

    private static final Log LOG = LogFactory.getLog(MetadataInjectorFactory.class);

    public MetadataInjectorFactory(final PlugDescription description, final IBus bus) {
        _description = description;
        _bus = bus;
    }

    public List<IMetadataInjector> getMetadataInjectors() throws Exception {
        List<IMetadataInjector> list = new ArrayList<IMetadataInjector>();
        SpringUtil springUtil = new SpringUtil("spring/spring.xml");
        List<IMetadataInjector> injectors = springUtil.getBean("metadataInjectors", _injectorContainer);
        if (injectors != null) {
            for (IMetadataInjector metadataInjector : injectors) {
                metadataInjector.configure(_description);
                if (metadataInjector instanceof IBusable) {
                    ((IBusable) metadataInjector).setIBus(_bus);
                }
                LOG.info("add metadata injector: " + metadataInjector.getClass().getName());
                list.add(metadataInjector);
            }
        }
        return list;
    }
}
