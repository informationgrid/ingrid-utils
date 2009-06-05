package de.ingrid.utils.processor.impl;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

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

    private static final Log LOG = LogFactoryImpl.getLog(QueryExtensionPreProcessor.class);

    @Override
    public void process(IngridQuery query) throws Exception {
        String queryString = query.toString();

        String busUrl = (String) query.get(BUS_URL);
        QueryExtension queryExtension = _queryExtensionContainer.getQueryExtension(busUrl);
        if (queryExtension != null) {
            Pattern pattern = queryExtension.getPattern();
            Matcher matcher = pattern.matcher(queryString);
            if (matcher.find()) {
                Set<FieldQuery> fieldQueries = queryExtension.getFieldQueries();
                for (FieldQuery fieldQuery : fieldQueries) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("add field to query: " + fieldQuery);
                    }
                    query.addField(fieldQuery);
                }
            }
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("pre process finished: " + query);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void configure(PlugDescription plugDescription) {
        _queryExtensionContainer = (QueryExtensionContainer) plugDescription.get("QUERY_EXTENSION");
        _queryExtensionContainer = _queryExtensionContainer != null ? _queryExtensionContainer : new QueryExtensionContainer();
    }

}
