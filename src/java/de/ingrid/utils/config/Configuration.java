/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.ingrid.utils.xml.XMLSerializer;

/**
 * Objects that holds onfiguration describable key values tuples. The
 * configurtation itself can be serialized to xml and loaded from a xml file.
 * Also it is possible to overload the configuration values, by loading a set of
 * files. Existing key values will be overwritten by key values in the latest
 * loaded file. created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class Configuration {

    private List properties = new ArrayList();

    /**
     * @return number of key value tubles in this configuration
     */
    public int size() {
        return this.properties.size();
    }

    /**
     * sets the value and description for a given key. In case the key already
     * exists we overwrite it.
     * 
     * @param key
     * @param value
     * @param description
     */
    public void set(String key, String value, String description) {
        boolean updated = false;
        int count = size();
        for (int i = 0; i < count; i++) {
            Property property = (Property) this.properties.get(i);
            if (property.getKey().equals(key)) {
                property.setValue(value);
                if (description != null && !property.getDescription().equals(description)) {
                    property.setDescription(description);
                }
                updated = true;
            }
        }
        if (!updated) {
            Property keyValue = new Property(key, value, description);
            this.properties.add(keyValue);
        }

    }

    /**
     * @param key
     * @param defaultValue
     * @return the value for this key or the default value
     */
    public String get(String key, String defaultValue) {
        int count = size();
        for (int i = 0; i < count; i++) {
            Property descripedKeyValue = (Property) this.properties.get(i);
            if (descripedKeyValue.getKey().equals(key)) {
                return descripedKeyValue.getValue();
            }
        }
        return defaultValue;
    }

    public int getAsInt(String key, int defaultValue) {
        String string = get(key, null);
        if (string != null) {
            try {
                return Integer.parseInt(string);
            } catch (Exception e) {
                // we just ignore this parse exception...
            }

        }
        return defaultValue;
    }

    /**
     * return the i'th key value tuple.
     * 
     * @param i
     * @return
     */
    public Property get(int i) {
        return (Property) this.properties.get(i);
    }

    /**
     * saves this configuration into a file
     * 
     * @param file
     * @throws IOException
     */
    public void save(File file) throws IOException {
        XMLSerializer serializer = new XMLSerializer();
        setAliases(serializer);
        serializer.serialize(this, file);
    }

    /**
     * Overloads the local values with them in a xml configuration file
     * 
     * @param file
     * @throws IOException
     */
    public void load(File file) throws IOException {
        load(null, file);
    }

    /**
     * Overloads the loca values with them in a xml configuration file read from
     * a inputstream
     * 
     * @param inputStream
     * @throws IOException
     */
    public void load(InputStream inputStream) throws IOException {
        load(inputStream, null);
    }

    private void load(InputStream inputStream, File file) throws IOException {
        XMLSerializer serializer = new XMLSerializer();
        setAliases(serializer);
        Configuration loadedConf;
        if (file != null) {
            loadedConf = (Configuration) serializer.deSerialize(file);
        } else {
            loadedConf = (Configuration) serializer.deSerialize(inputStream);
        }
        int count = loadedConf.size();
        for (int i = 0; i < count; i++) {
            Property value = loadedConf.get(i);
            set(value.getKey(), value.getValue(), value.getDescription());
        }
    }

    /**
     * sets aliases for xml serialization
     * 
     * @param serializer
     */
    private void setAliases(XMLSerializer serializer) {
        serializer.aliasClass("configuration", Configuration.class);
        serializer.aliasClass("property", Property.class);
    }

}
