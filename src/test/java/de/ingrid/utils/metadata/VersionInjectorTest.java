package de.ingrid.utils.metadata;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class VersionInjectorTest extends TestCase {

	public void testInjectVersion() throws Exception {
		IMetadataInjector injector = new DefaultMetadataInjector();

		Metadata metadata = new Metadata();
		assertNotNull(metadata.getVersion());
		injector.injectMetaDatas(metadata);
		
		assertEquals("0.1.2", metadata.getVersion());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("1970-01-01", dateFormat.format(metadata
				.getReleaseDate().getTime()));
		assertEquals(IPlugType.OTHER, metadata.getPlugType());
	}
}
