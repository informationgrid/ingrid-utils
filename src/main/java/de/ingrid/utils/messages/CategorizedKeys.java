/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class CategorizedKeys {

    private static HashMap fCache = new HashMap();

    private HashMap fCategories = new HashMap();

    private CategorizedKeys(String bundle_name) throws IOException {
      InputStream resourceAsStream = CategorizedKeys.class.getResourceAsStream(bundle_name);
      loadCategorizedProperties(resourceAsStream);
    }

    private CategorizedKeys(InputStream inputStream) throws IOException {
      loadCategorizedProperties(inputStream);
    }
    /**
     * loads one property file and parse the categories and store the keys and
     * values in different category belonning properties objects
     * 
     * @param bundle_name
     * @throws IOException
     */
    private void loadCategorizedProperties(InputStream inputStream)
            throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);

        Enumeration enumeration = properties.keys();
        while (enumeration.hasMoreElements()) {
            String element = (String) enumeration.nextElement();
            int pointPos = element.indexOf(".");
            String categoryName = element.substring(0, pointPos);
            String keyName = element.substring(pointPos + 1, element.length());
            String value = properties.getProperty(element);
            Properties catProperties;
            if (this.fCategories.containsKey(categoryName)) {
                catProperties = (Properties) fCategories.get(categoryName);
            } else {
                catProperties = new Properties();
                fCategories.put(categoryName, catProperties);
            }
            catProperties.put(keyName, value);
        }
    }

    /**
     * @param bundle_name (this name will be used as key for the cache)
     * @return a cached CategorizedKeys Object or creates a new one.
     * @throws IOException
     */
    public static CategorizedKeys get(String bundle_name) throws IOException {
        if (!fCache.containsKey(bundle_name)) {
            CategorizedKeys keys = new CategorizedKeys(bundle_name);
            fCache.put(bundle_name, keys);
        }
        return (CategorizedKeys) fCache.get(bundle_name);
    }

    /**
     * @param bundle_name
     * @return a cached CategorizedKeys Object or creates a new one.
     * @throws IOException
     */
    public static CategorizedKeys get(String cacheKey, InputStream inputStream) throws IOException {
      if (!fCache.containsKey(cacheKey)) {
        CategorizedKeys keys = new CategorizedKeys(inputStream);
        fCache.put(cacheKey, keys);
    }
    return (CategorizedKeys) fCache.get(cacheKey);
    }
    /**
     * @param key
     * @return the first value from a category properties object that is found
     *         for the given key Attention keys should be unique for all
     *         categories
     */
    public String getString(String key) {
        Properties[] properties = (Properties[]) fCategories.values().toArray(
                new Properties[fCache.size()]);
        for (int i = 0; i < properties.length; i++) {
            Properties catProperties = properties[i];
            String property = catProperties.getProperty(key);
            if (property != null) {
                return property;
            }

        }
        return null;
    }

    /**
     * @return an array of all known categories
     */
    public String[] getCategories() {
        String[] strings = (String[]) fCategories.keySet().toArray(
                new String[fCategories.size()]);
        return strings == null ? new String[]{} : strings;
    }

    /**
     * @param category
     * @return the property keys of a given category
     */
    public String[] getKeysForCategory(String category) {
        Properties properties = (Properties) fCategories.get(category);
        return (String[]) properties.keySet().toArray(
                new String[properties.size()]);
    }

    
    /**
     * 
     */
    public static void clear() {
        fCache.clear();
    }
}