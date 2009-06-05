package de.ingrid.utils.tool;

import junit.framework.TestCase;
import de.ingrid.utils.IIngridHitEnrichment;
import de.ingrid.utils.IngridHit;

public class SpringUtilTest extends TestCase {

	public void testLoadBean() throws Exception {
		SpringUtil util = new SpringUtil("spring-test1.xml");
		IngridHit ingridHit = util.getBean("hit", IngridHit.class);
		IIngridHitEnrichment hitEnrichment = util.getBean("hitEnrichment",
				IIngridHitEnrichment.class);

		assertNull(ingridHit.getHitId());
		hitEnrichment.enrichment(ingridHit);
		assertNotNull(ingridHit.getHitId());
		assertEquals("foo", ingridHit.getHitId());
		
	}
}
