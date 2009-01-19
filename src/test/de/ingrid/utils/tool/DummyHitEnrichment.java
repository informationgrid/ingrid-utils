package de.ingrid.utils.tool;

import de.ingrid.utils.IIngridHitEnrichment;
import de.ingrid.utils.IngridHit;

public class DummyHitEnrichment implements IIngridHitEnrichment {

	@Override
	public void enrichment(IngridHit ingridHit) {
		ingridHit.setHitId("foo");
	}

}
