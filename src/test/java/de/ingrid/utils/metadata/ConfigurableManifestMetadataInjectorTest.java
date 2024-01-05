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
/**
 * 
 */
package de.ingrid.utils.metadata;

import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author joachim
 *
 */
public class ConfigurableManifestMetadataInjectorTest {

    /**
     * Test method for {@link de.ingrid.utils.metadata.ConfigurableManifestMetadataInjector#ConfigurableManifestMetadataInjector(java.lang.String)}.
     */
    @Test
    public void testConfigurableManifestMetadataInjector() {
		ConfigurableManifestMetadataInjector mi = new ConfigurableManifestMetadataInjector("org.springframework.beans.factory.xml.XmlBeanFactory");
		Metadata metadata = new Metadata();
		mi.injectMetaDatas(metadata);
		assertNotNull(metadata.getVersion());
		assertEquals(IPlugType.OTHER, metadata.getPlugType());
		assertEquals(new Date(0L), metadata.getReleaseDate());
	}

}
