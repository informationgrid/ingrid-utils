package de.ingrid.utils.metadata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		InputStream inputStream = IMetadataInjector.class
				.getResourceAsStream("metadataInjectors.properties");
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			Class<IMetadataInjector> clazz = (Class<IMetadataInjector>) Class
					.forName(line);
			IMetadataInjector metadataInjector = clazz.newInstance();
			metadataInjector.configure(_description);
			if (metadataInjector instanceof IBusable) {
				((IBusable) metadataInjector).setIBus(_bus);
			}
			list.add(metadataInjector);
		}
		bufferedReader.close();
		return list;
	}
}
