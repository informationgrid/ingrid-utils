package de.ingrid.utils;

import de.ingrid.utils.query.IngridQuery;

/**
 * General Detailer interface to get details of a IngridHit document.
 */
public interface IDetailer {

    /**
     * Searches for details to a hit.
     * 
     * @param hit
     *            The from search returned hit document.
     * @param query
     *            The query for the hit to caculate a summary.
     * @param requestedFields
     *            Fields the detailer should push into the detail object. This can be set to NULL if it isn't needed.
     * @return A constructed document with details to a hit.
     * @throws Exception
     */
    public IngridHitDetail getDetail(IngridHit hit, IngridQuery query, String[] requestedFields) throws Exception;

    /**
     * Searches for details to a array of hits.
     * 
     * @param hits
     *            The array of hit documents from a search.
     * @param query
     *            The query for the hit to caculate a summary.
     * @param requestedFields
     *            Fields the detailer should push into the detail object. This can be set to NULL if it isn't needed.
     * @return An array of constructed documents with details to a hit. If nothing is found the array is empty.
     * @throws Exception
     */
    public IngridHitDetail[] getDetails(IngridHit[] hits, IngridQuery query, String[] requestedFields) throws Exception;

    /**
     * Closes the detailer.
     * @throws Exception If something goes wrong during close.
     */
    public void close() throws Exception;
}
