package de.ingrid.utils.metadata;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;
import de.ingrid.utils.PlugDescription;

public class VersionInjectorTest extends TestCase {

	public void testInjectVersion() throws Exception {
		IMetadataInjector injector = new DefaultMetadataInjector();
		PlugDescription plugDescription = new PlugDescription();
		
		assertEquals(null, plugDescription.get(DefaultMetadataInjector.DEFAULT_METADATA));
		injector.injectMetaDatas(plugDescription);
		
		Metadata metadata = (Metadata) plugDescription.get(DefaultMetadataInjector.DEFAULT_METADATA);
		
		assertEquals("0.1.2", metadata.getVersion());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals(dateFormat.format(new Date(0)), dateFormat.format(metadata
				.getReleaseDate().getTime()));
		assertEquals(IPlugType.OTHER, metadata.getPlugType());
	}
}
