package de.ingrid.utils.processor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import de.ingrid.utils.IConfigurable;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.processor.IPreProcessor;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;

public class QueryExtensionPreProcessor implements IPreProcessor, IConfigurable {

    private static final String BUS_URL = "BUS_URL";

    private Map<String, List<FieldQuery>> _queryExtension = new HashMap<String, List<FieldQuery>>();

    private static final Log LOG = LogFactoryImpl.getLog(QueryExtensionPreProcessor.class);

    @Override
    public void process(IngridQuery query) throws Exception {
        String busUrl = (String) query.get(BUS_URL);
        if (_queryExtension.containsKey(busUrl)) {
            List<FieldQuery> fieldQueries = _queryExtension.get(busUrl);
            for (FieldQuery fieldQuery : fieldQueries) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("add field to query: " + fieldQuery);
                }
                query.addField(fieldQuery);
            }
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("pre process finished: " + query);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void configure(PlugDescription plugDescription) {
        _queryExtension = (Map<String, List<FieldQuery>>) plugDescription.get("QUERY_EXTENSION");
        _queryExtension = _queryExtension != null ? _queryExtension : new HashMap<String, List<FieldQuery>>();
    }

}
