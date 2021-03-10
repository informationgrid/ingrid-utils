/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
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
