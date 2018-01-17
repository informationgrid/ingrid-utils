/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2018 wemove digital solutions GmbH
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
        final FieldQuery second = it.next();
        
        FieldQuery[] fqs = new FieldQuery[] { first, second };
        
        for (FieldQuery fieldQuery : fqs) {
            if ("bw".equals( fieldQuery.getFieldValue() )) {
                assertEquals(false, fieldQuery.isRequred());
                assertEquals(true, fieldQuery.isProhibited());
                assertEquals("partner", fieldQuery.getFieldName());
                assertEquals("bw", fieldQuery.getFieldValue());
            } else {
                assertEquals(true, fieldQuery.isRequred());
                assertEquals(false, fieldQuery.isProhibited());
                assertEquals("test", fieldQuery.getFieldName());
                assertEquals("true", fieldQuery.getFieldValue());
            }
            
        }
    }
}
