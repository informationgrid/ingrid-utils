/**
 * 
 */
package de.ingrid.utils.metadata;

import java.util.Date;

import junit.framework.TestCase;
import de.ingrid.utils.PlugDescription;

/**
 * @author joachim@wemove.com
 * 
 */
public class ManifestMetadataInjectorTest extends TestCase {

	/**
	 * Test method for
	 * {@link de.ingrid.utils.metadata.ManifestMetadataInjector#injectMetaDatas(de.ingrid.utils.metadata.Metadata)}.
	 * 
	 * @throws Exception
	 */
	public void testInjectMetaDatas() throws Exception {
		PlugDescription pd = new PlugDescription();
		pd.setIPlugClass("org.springframework.beans.factory.xml.XmlBeanFactory");
		ManifestMetadataInjector mdi = new ManifestMetadataInjector();
		mdi.configure(pd);
		Metadata metadata = new Metadata();
		mdi.injectMetaDatas(metadata);
		assertNotNull(metadata.getVersion());
		assertEquals(IPlugType.OTHER, metadata.getPlugType());
		assertEquals(new Date(0L), metadata.getReleaseDate());
	}

}
