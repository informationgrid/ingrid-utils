/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
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
/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Serialize and de serialize objects to / from xml
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class XMLSerializer {
    
    private final Logger log = LogManager.getLogger( XMLSerializer.class );

    private XStream fXStream;

    /**
     *  
     */
    public XMLSerializer() {
		this.fXStream = new XStream();
		fXStream.addPermission(AnyTypePermission.ANY);
    }

    /**
     * sets an alias name of a class, that is used until serialization as node
     * name
     * 
     * @param name
     * @param clazz
     */
    public void aliasClass(String name, Class<?> clazz) {
	this.fXStream.alias(name, clazz);
    }

    /**
     * @param object
     * @param target
     * @throws IOException
     */
    public void serialize(Object object, File target) throws IOException {
	try (FileWriter writer = new FileWriter(target)) {

	    String xml = this.fXStream.toXML(object);

	    if (log.isDebugEnabled()) log.debug( xml );

	    writer.write(xml);
	}
    }

    /**
     * @param target
     * @return bean loaded from a xml file
     * @throws IOException
     */
    public Object deSerialize(File target) throws IOException {
	String xml = getContents(target);
	return this.fXStream.fromXML(xml);
    }

    /**
     * @param inputStream
     * @return object loaded from xml of a stream
     * @throws IOException
     */
    public Object deSerialize(InputStream inputStream) throws IOException {
	String xml = getContents(inputStream);
	return this.fXStream.fromXML(xml);
    }

    /**
     * @param aFile
     * @return text content from a given file
     * @throws IOException
     */
    public static String getContents(File aFile) throws IOException {
	try (FileReader fr = new FileReader(aFile);
		BufferedReader br = new BufferedReader(fr);) {
	    return readContent(br);
	}
    }

    /**
     * @param inputStream
     * @return text content from a inputstream
     * @throws IOException
     */
    public static String getContents(InputStream inputStream)
	    throws IOException {
	try (InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

	) {
	    return readContent(br);
	}
    }

    private static String readContent(BufferedReader input) throws IOException {
	StringBuffer contents = new StringBuffer();
	String line = null; // not declared within while loop
	while ((line = input.readLine()) != null) {
	    contents.append(line);
	    contents.append(System.getProperty("line.separator"));
	}

	return contents.toString();
    }

}
