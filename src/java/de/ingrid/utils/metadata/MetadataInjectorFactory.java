package de.ingrid.utils.metadata;

import java.util.ArrayList;
import java.util.List;

import de.ingrid.utils.IBus;
import de.ingrid.utils.PlugDescription;

public class MetadataInjectorFactory {

	private final PlugDescription _description;
	private final IBus _bus;

	public MetadataInjectorFactory(final PlugDescription description,
			final IBus bus) {
		_description = description;
		_bus = bus;
	}

	public List<IMetadataInjector> getMetadataInjectors() throws Exception {
		List<IMetadataInjector> list = new ArrayList<IMetadataInjector>();
		List<String> metadataInjectorNames = (List<String>) _description
				.get(PlugDescription.METADATA_INJECTORS);
		if (metadataInjectorNames != null) {
			for (String injectorName : metadataInjectorNames) {
				Class<IMetadataInjector> clazz = (Class<IMetadataInjector>) Class
						.forName(injectorName);
				IMetadataInjector metadataInjector = clazz.newInstance();
				metadataInjector.configure(_description);
				if (metadataInjector instanceof IBusable) {
					((IBusable) metadataInjector).setIBus(_bus);
				}

				list.add(metadataInjector);
			}
		}
		return list;
	}
}
