package de.ingrid.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;

import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.xml.PlugdescriptionSerializer;

public class QueryExtensionContainerTest extends TestCase {

    public void testReadNew() throws IOException, ClassNotFoundException {
        final PlugdescriptionSerializer serializer = new PlugdescriptionSerializer();
        final InputStream stream = new ClassPathResource("new_queryextension.xml").getInputStream();
        final PlugDescription pd = (PlugDescription) serializer.deSerialize(stream);

        doContainerTest(pd.getQueryExtensionContainer());
    }

    public void testReadOld() throws IOException {
        final PlugdescriptionSerializer serializer = new PlugdescriptionSerializer();
        final InputStream stream = new ClassPathResource("old_queryextension.xml").getInputStream();
        final PlugDescription pd = (PlugDescription) serializer.deSerialize(stream);

        doContainerTest(pd.getQueryExtensionContainer());
    }

    private static void doContainerTest(final QueryExtensionContainer container) {
        assertNotNull(container);

        final Map<String, QueryExtension> extensions = container.getQueryExtensions();
        assertNotNull(extensions);
        assertEquals(1, extensions.size());

        final QueryExtension extension = extensions.get("/test:ibus");
        assertNotNull(extension);

        final Set<Pattern> patterns = extension.getPatterns();
        assertEquals(1, patterns.size());
        final Iterator<Pattern> patit = patterns.iterator();
        final Pattern pattern = patit.next();
        assertEquals("test.*", pattern.pattern());

        final Set<FieldQuery> queries = extension.getFieldQueries();
        assertEquals(2, queries.size());
        final Iterator<FieldQuery> it = queries.iterator();
        final FieldQuery first = it.next();
        assertEquals(false, first.isRequred());
        assertEquals(true, first.isProhibited());
        assertEquals("partner", first.getFieldName());
        assertEquals("bw", first.getFieldValue());

        final FieldQuery second = it.next();
        assertEquals(true, second.isRequred());
        assertEquals(false, second.isProhibited());
        assertEquals("test", second.getFieldName());
        assertEquals("true", second.getFieldValue());
    }
}
