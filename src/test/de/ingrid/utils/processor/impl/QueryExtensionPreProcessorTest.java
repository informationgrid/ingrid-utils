package de.ingrid.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import de.ingrid.utils.IConfigurable;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.processor.IPreProcessor;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.queryparser.QueryStringParser;

public class QueryExtensionPreProcessorTest extends TestCase {

    private static final String _busUrl = "testBusUrl";

    public void testProcessor() throws Exception {
        IPreProcessor preProcessor = new QueryExtensionPreProcessor();
        PlugDescription plugDescription = new PlugDescription();

        FieldQuery fieldQuery = new FieldQuery(true, true, "foo", "bar");
        List<FieldQuery> list = new ArrayList<FieldQuery>();
        list.add(fieldQuery);
        Map<String, List<FieldQuery>> queryExtension = new HashMap<String, List<FieldQuery>>();
        queryExtension.put(_busUrl, list);
        plugDescription.put("QUERY_EXTENSION", queryExtension);

        ((IConfigurable) preProcessor).configure(plugDescription);

        IngridQuery query = QueryStringParser.parse("wasser");
        FieldQuery[] fields = query.getFields();
        assertEquals(0, fields.length);
        query.put("BUS_URL", "testBusUrl");
        preProcessor.process(query);
        fields = query.getFields();
        assertEquals(1, fields.length);
        FieldQuery fieldQuery2 = fields[0];
        assertEquals("foo", fieldQuery2.getFieldName());
        assertEquals("bar", fieldQuery2.getFieldValue());

    }
}
