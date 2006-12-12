package de.ingrid.utils;

import de.ingrid.utils.query.IngridQuery;

/**
 * General Searcher interface for IBus and Iplu
 * 
 */
public interface ISearcher {

	/**
	 * Searches the IPlug with the given query and range.
	 * 
	 * @param query The ingrid query to search in the IPlug.
	 * @param start Where to start (range is from 0 to Integer.maxValue).
	 * @param length How many result are needed?
	 * @return A subset of matching <code>Hit</code>s bundled with meta
	 *         information into a <code>Hits</code>.
	 * @throws Exception
	 */
	public IngridHits search(IngridQuery query, int start, int length)
			throws Exception;
}
