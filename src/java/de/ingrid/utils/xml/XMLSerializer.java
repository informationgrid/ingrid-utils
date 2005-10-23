/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.xml;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.SAXException;

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

    /**
     * serialize a java bean as xml
     * 
     * @param object
     * @param target
     * @throws IOException
     */
    public static void serializeAsXML(Object object, File target) throws IOException {
        FileWriter writer = new FileWriter(target);
        XStream xstream = new XStream();
        xstream.alias(object.getClass().getName(), object.getClass());
        String xml = xstream.toXML(object);
        System.out.println(xml);
        writer.write(xml);
        writer.close();
    }

    /**
     * @param clazz
     * @param target
     * @return bean loaded from a xml file
     * @throws IntrospectionException
     * @throws IOException
     * @throws SAXException
     */
    public static Object loadDescriptionFromXML(Class clazz, File target) throws IOException {
        XStream xstream = new XStream();
        xstream.alias(clazz.getName(), clazz);
        String xml = getContents(target);
        return xstream.fromXML(xml);
    }

    /**
     * @param aFile
     * @return text content from a given file
     * @throws IOException
     */
    private static String getContents(File aFile) throws IOException {
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(aFile));
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
