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

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.xml.sax.SAXException;

import de.ingrid.utils.DescribableValue;

/**
 * Serialize and de serialize objects to / from xml 
 * 
 * created on 09.08.2005
 * @author  sg
 * @version $Revision: 1.3 $
 */
public class XMLSerializer {

    /**
     * serialize a java bean as xml
     * @param description
     * @param target
     * @throws IOException
     * @throws SAXException
     * @throws IntrospectionException
     */
    public static void serializeAsXML(Object description, File target) throws IOException, SAXException,
            IntrospectionException {
        FileWriter writer = new FileWriter(target);
        writer.write("<?xml version='1.0' ?>");
        BeanWriter beanWriter = new BeanWriter(writer);
        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanWriter.getBindingConfiguration().setMapIDs(true);
        beanWriter.enablePrettyPrint();
        // beanWriter.setWriteEmptyElements(true);
        beanWriter.write(description.getClass().getName(), description);
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
    public static Object loadDescriptionFromXML(Class clazz, File target) throws IntrospectionException, IOException,
            SAXException {
        FileReader fileReader = new FileReader(target);
        BufferedReader xmlReader = new BufferedReader(fileReader);

        BeanReader beanReader = new BeanReader();
        beanReader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanReader.getBindingConfiguration().setMapIDs(true);
        beanReader.registerBeanClass(clazz.getName(), clazz);
        beanReader.registerBeanClass(DescribableValue.class.getName(), DescribableValue.class);

        Object object = beanReader.parse(xmlReader);
        xmlReader.close();
        return object;

    }

}
