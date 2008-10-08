package de.ingrid.utils;

import java.util.ArrayList;
import java.util.List;

public class IngridHitsEnrichmentFactory {

	private List _enrichmentCollection = new ArrayList();

	public List getIngridHitsEnrichmentCollection() {
		return _enrichmentCollection;
	}
	
	public void register(IIngridHitEnrichment enrichment) {
		_enrichmentCollection.add(enrichment);
	}

}
