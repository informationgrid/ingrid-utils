/**
 * 
 */
package de.ingrid.utils.metadata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import de.ingrid.utils.PlugDescription;

/**
 * @author joachim
 *
 */
public class ConfigurableManifestMetadataInjector extends ManifestMetadataInjector {

	private static final Log LOG = LogFactoryImpl.getLog(ConfigurableManifestMetadataInjector.class);
	
	
	public ConfigurableManifestMetadataInjector(String className) {
		// create ibus specific plugdescription and initialize the ManifestMetadataInjector
		PlugDescription description = new PlugDescription();
		description.setIPlugClass(className);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Configure ManifestMetadataInjector with class name: " + description.getIPlugClass());
		}
		this.configure(description);
	}

}
