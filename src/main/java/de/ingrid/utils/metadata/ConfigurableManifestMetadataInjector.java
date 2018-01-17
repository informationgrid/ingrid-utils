/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2018 wemove digital solutions GmbH
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
/**
 * 
 */
package de.ingrid.utils.metadata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.PlugDescription;

/**
 * @author joachim
 *
 */
public class ConfigurableManifestMetadataInjector extends ManifestMetadataInjector {

	private static final Log LOG = LogFactory.getLog(ConfigurableManifestMetadataInjector.class);
	
	
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
