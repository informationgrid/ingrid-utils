package de.ingrid.utils;

/**
 * General Detailer interfac to get details of a IngridHit document
 * 
 */
public interface IDetailer {

	/**
	 * @param hit
	 * @return a detailed document of a hit
	 * @throws Exception
	 */
	public IngridHitDetail  getDetails(IngridHit hit) throws Exception;

}
