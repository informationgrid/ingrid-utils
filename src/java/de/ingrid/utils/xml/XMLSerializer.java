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

/**
 * Serialize and de serialize objects to / from xml
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class XMLSerializer {

    private XStream fXStream;

    public XMLSerializer() {
        this.fXStream = new XStream();
    }

    /**
     * sets an alias name of a class, that is used until serialization as node
     * name
     * 
     * @param name
     * @param clazz
     */
    public void aliasClass(String name, Class clazz) {
        this.fXStream.alias(name, clazz);
    }

    public void serialize(Object object, File target) throws IOException {
        FileWriter writer = new FileWriter(target);
        String xml = this.fXStream.toXML(object);
        System.out.println(xml);
        writer.write(xml);
        writer.close();
    }

    /**
     * @param clazz
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
        return readContent(new BufferedReader(new FileReader(aFile)));
    }

    /**
     * @param inputStream
     * @return text content from a inputstream
     * @throws IOException
     */
    public static String getContents(InputStream inputStream) throws IOException {
        return readContent(new BufferedReader(new InputStreamReader(inputStream)));
    }

    private static String readContent(BufferedReader input) throws IOException {
        StringBuffer contents = new StringBuffer();
        try {
            String line = null; // not declared within while loop
            while ((line = input.readLine()) != null) {
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }

        } finally {
            if (input != null) {
                input.close();
            }
        }
        return contents.toString();
    }

}
