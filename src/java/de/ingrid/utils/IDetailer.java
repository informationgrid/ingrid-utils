package de.ingrid.utils;

import de.ingrid.utils.query.IngridQuery;

/**
 * General Detailer interface to get details of a IngridHit document
 * 
 */
public interface IDetailer
 {

	/**
	 * @param hit the hit document
     * @param query a ingridquery to caculate the summary
	 * @return a detailed document of a hit
	 * @throws Exception
	 */
	public IngridHitDetail  getDetail(IngridHit hit, IngridQuery query) throws Exception;

}
