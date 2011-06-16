package de.ingrid.utils.processor.impl;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.IConfigurable;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.QueryExtension;
import de.ingrid.utils.QueryExtensionContainer;
import de.ingrid.utils.processor.IPreProcessor;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;

public class QueryExtensionPreProcessor implements IPreProcessor, IConfigurable {

    private static final String BUS_URL = "BUS_URL";

    private QueryExtensionContainer _queryExtensionContainer = new QueryExtensionContainer();

    private static final Log LOG = LogFactory.getLog(QueryExtensionPreProcessor.class);

    @Override
    public void process(IngridQuery query) throws Exception {
        String queryString = query.toString();

		// get bus url
        String busUrl = (String) query.get(BUS_URL);
		// find corresponding query extension
        QueryExtension queryExtension = _queryExtensionContainer.getQueryExtension(busUrl);
        if (queryExtension != null) {
			// get all existing patterns
			Set<Pattern> patterns = queryExtension.getPatterns();
			for (Pattern pattern : patterns) {
				// if the query matches a pattern
				// add all field queries of it
				if (pattern.matcher(queryString).find()) {
					Set<FieldQuery> fieldQueries = queryExtension.getFieldQueries(pattern);
					if (fieldQueries != null) {
						for (FieldQuery fieldQuery : fieldQueries) {
						    // reject query immediately if it shall be denied 
						    if ("metainfo:query_deny".equals(fieldQuery.getContent())) {
						        if (LOG.isDebugEnabled()) {
	                                LOG.debug("Rejected query because it was DENIED!");
	                            }
						        query.addField(fieldQuery);
						        return;
						    }
							if (LOG.isDebugEnabled()) {
								LOG.debug("add field to query: " + fieldQuery);
							}
							query.addField(fieldQuery);
						}
					}
				} else {
				    Set<FieldQuery> fieldQueries = queryExtension.getFieldQueries(pattern);
				    for (FieldQuery fieldQuery : fieldQueries) {
				        // reject query if allow-pattern doesn't match query
				        if ("metainfo:query_allow".equals(fieldQuery.getContent())) {
				            if (LOG.isDebugEnabled()) {
                                LOG.debug("Rejected query because it didn't match the ALLOW-pattern!");
                            }
				            query.addField(new FieldQuery(true, false, "metainfo", "query_deny"));
				            return;
				        }
                    }
				}
			}
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("pre process finished: " + query);
        }
    }

    @Override
    public void configure(PlugDescription plugDescription) {
		_queryExtensionContainer = (QueryExtensionContainer) plugDescription.get(PlugDescription.QUERY_EXTENSION_CONTAINER);
        _queryExtensionContainer = _queryExtensionContainer != null ? _queryExtensionContainer : new QueryExtensionContainer();
    }

}
