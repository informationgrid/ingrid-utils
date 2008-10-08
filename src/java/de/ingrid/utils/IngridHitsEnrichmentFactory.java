package de.ingrid.utils;

import java.util.ArrayList;
import java.util.List;

public class IngridHitsEnrichmentFactory {

	private List<IIngridHitEnrichment> _enrichmentCollection = new ArrayList<IIngridHitEnrichment>();

	public List<IIngridHitEnrichment> getIngridHitsEnrichmentCollection() {
		return _enrichmentCollection;
	}
	
	public void register(IIngridHitEnrichment enrichment) {
		_enrichmentCollection.add(enrichment);
	}

}
