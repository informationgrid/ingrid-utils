/**
 * 
 */
package de.ingrid.utils.metadata;

import de.ingrid.utils.PlugDescription;

/**
 * @author joachim
 *
 */
public class IBusManifestMetadataInjector extends ManifestMetadataInjector {

	public IBusManifestMetadataInjector() {
		// create ibus specific plugdescription and initialize the ManifestMetadataInjector
		PlugDescription description = new PlugDescription();
		description.setIPlugClass("de.ingrid.ibus.Bus");
		this.configure(description);
	}

}
