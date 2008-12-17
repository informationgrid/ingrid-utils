package de.ingrid.utils.metadata;

import java.util.ArrayList;
import java.util.List;

public class MetadataInjectorFactory {

	public static List<IMetadataInjector> getMetadataInjectors() {
		List<IMetadataInjector> list = new ArrayList<IMetadataInjector>();
		list.add(new DefaultMetadataInjector());
		return list;
	}
}
