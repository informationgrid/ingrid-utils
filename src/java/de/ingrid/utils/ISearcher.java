package de.ingrid.utils;

import de.ingrid.utils.query.IngridQuery;

/**
 * General Searcher interface for IBus and Iplu
 * 
 */
public interface ISearcher {

	/**
	 * search
	 * 
	 * @param query
	 * @param start Where to start (range is from 0 to Integer.maxValue).
	 * @param length
	 * @return A subset of matching <code>Hit</code>s bundled with meta
	 *         information into a <code>Hits</code>.
	 * @throws Exception
	 */
	public IngridHits search(IngridQuery query, int start, int length)
			throws Exception;
}
