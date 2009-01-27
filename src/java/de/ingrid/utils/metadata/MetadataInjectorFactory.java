package de.ingrid.utils.metadata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import de.ingrid.utils.IBus;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.tool.SpringUtil;

public class MetadataInjectorFactory {

    private final PlugDescription _description;

    private final IBus _bus;

    private final Class<List<IMetadataInjector>> _injectorContainer = null;

    private static final Log LOG = LogFactoryImpl.getLog(MetadataInjectorFactory.class);

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
