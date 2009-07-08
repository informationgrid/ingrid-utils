/**
 * 
 */
package de.ingrid.utils.metadata;

import java.util.Date;

import junit.framework.TestCase;

/**
 * @author joachim
 *
 */
public class ConfigurableManifestMetadataInjectorTest extends TestCase {

	/**
	 * Test method for {@link de.ingrid.utils.metadata.ConfigurableManifestMetadataInjector#ConfigurableManifestMetadataInjector(java.lang.String)}.
	 */
	public void testConfigurableManifestMetadataInjector() {
		ConfigurableManifestMetadataInjector mi = new ConfigurableManifestMetadataInjector("org.springframework.beans.factory.xml.XmlBeanFactory");
		Metadata metadata = new Metadata();
		mi.injectMetaDatas(metadata);
		assertNotNull(metadata.getVersion());
		assertEquals(IPlugType.OTHER, metadata.getPlugType());
		assertEquals(new Date(0L), metadata.getReleaseDate());
	}

}
