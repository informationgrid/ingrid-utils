package de.ingrid.utils.processor.impl;

import java.io.File;

import junit.framework.TestCase;
import de.ingrid.utils.IConfigurable;
import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.QueryExtension;
import de.ingrid.utils.QueryExtensionContainer;
import de.ingrid.utils.processor.IPreProcessor;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.queryparser.QueryStringParser;
import de.ingrid.utils.xml.XMLSerializer;

public class QueryExtensionPreProcessorTest extends TestCase {

    private static final String _busUrl = "testBusUrl";

    private File _file;

    @Override
    protected void setUp() throws Exception {
        _file = File.createTempFile("plugdescription", ".xml");
        final PlugDescription plugDescription = new PlugDescription();
        final QueryExtensionContainer container = new QueryExtensionContainer();
        final QueryExtension queryExtension = new QueryExtension();
        final FieldQuery fieldQuery = new FieldQuery(true, true, "foo", "bar");
        queryExtension.addFieldQuery("partner:bw", fieldQuery);
        queryExtension.setBusUrl(_busUrl);
        container.addQueryExtension(queryExtension);
		plugDescription.put(PlugDescription.QUERY_EXTENSION_CONTAINER, container);
        new XMLSerializer().serialize(plugDescription, _file);
    }

    @Override
    protected void tearDown() throws Exception {
        assertTrue(_file.delete());
    }

    public void testProcessorWithoutExtension() throws Exception {
        final IPreProcessor preProcessor = new QueryExtensionPreProcessor();

        final Object object = new XMLSerializer().deSerialize(_file);
        final PlugDescription plugDescription = (PlugDescription) object;

        ((IConfigurable) preProcessor).configure(plugDescription);

        final IngridQuery query = QueryStringParser.parse("wasser");
        FieldQuery[] fields = query.getFields();
        assertEquals(0, fields.length);
        query.put("BUS_URL", "testBusUrl");

        preProcessor.process(query);

        fields = query.getFields();
        assertEquals(0, fields.length);
    }

    public void testProcessorWithExtension() throws Exception {
        final IPreProcessor preProcessor = new QueryExtensionPreProcessor();

        final Object object = new XMLSerializer().deSerialize(_file);
        final PlugDescription plugDescription = (PlugDescription) object;

        ((IConfigurable) preProcessor).configure(plugDescription);

        final IngridQuery query = QueryStringParser.parse("wasser partner:bw");
        FieldQuery[] fields = query.getFields();
        assertEquals(0, fields.length);
        query.put("BUS_URL", "testBusUrl");

        preProcessor.process(query);

        fields = query.getFields();
        assertEquals(1, fields.length);
        final FieldQuery fieldQuery2 = fields[0];
        assertEquals("foo", fieldQuery2.getFieldName());
        assertEquals("bar", fieldQuery2.getFieldValue());

    }

}
