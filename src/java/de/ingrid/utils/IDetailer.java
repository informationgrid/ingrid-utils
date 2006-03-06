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
     * @param requestedFields fields the detailer should puhs into the detail object, if this fields are unknow just igrnore it
	 * @return a detailed document of a hit
	 * @throws Exception
	 */
	public IngridHitDetail  getDetail(IngridHit hit, IngridQuery query, String[] requestedFields) throws Exception;

    
    
    /**
     * @param hits
     * @param query
     * @param requestedFields fields the detailer should puhs into the detail object, if this fields are unknow just igrnore it
     * @return an array of details or a emthy array.
     * @throws Exception
     */
    public IngridHitDetail[]  getDetails(IngridHit[] hits, IngridQuery query, String[] requestedFields) throws Exception;



	public void close() throws Exception;
    
    
}
