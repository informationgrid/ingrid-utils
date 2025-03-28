/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.ingrid.utils.PlugDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author joachim@wemove.com
 *
 */
public class ManifestMetadataInjectorTest {

    /**
     * Test method for
     * {@link de.ingrid.utils.metadata.ManifestMetadataInjector#injectMetaDatas(de.ingrid.utils.metadata.Metadata)}.
     *
     * @throws Exception
     */
    @Test
    public void testInjectMetaDatas() throws Exception {
		PlugDescription pd = new PlugDescription();
		pd.setIPlugClass("org.springframework.beans.factory.xml.XmlBeanFactory");
		ManifestMetadataInjector mdi = new ManifestMetadataInjector();
		mdi.configure(pd);
		Metadata metadata = new Metadata();
		mdi.injectMetaDatas(metadata);
		assertNotNull(metadata.getVersion());
		assertEquals(IPlugType.OTHER, metadata.getPlugType());
		assertEquals(new GregorianCalendar(1970, Calendar.JANUARY, 1, 0, 0, 0).getTime()
				, metadata.getReleaseDate());
		System.out.println("injected Metadata: " + metadata);

		// test wrong package/missing MANIFEST
		pd.setIPlugClass("non.existing.package.class");
		mdi = new ManifestMetadataInjector();
		mdi.configure(pd);
		metadata = new Metadata();
		mdi.injectMetaDatas(metadata);
		assertEquals("unknown", metadata.getVersion());
		assertEquals(IPlugType.OTHER, metadata.getPlugType());
		assertEquals(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime(), metadata.getReleaseDate());
	}

}
