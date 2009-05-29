package de.ingrid.utils.metadata;

import de.ingrid.utils.PlugDescription;

public interface IMetadataInjector {

	void injectMetaDatas(Metadata metadata);
	
	void configure(PlugDescription description) throws Exception;

}
