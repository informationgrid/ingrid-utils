/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A data container for general usage in ingrid. Children should store all
 * values via put in the mother to be available via get.
 * 
 * 
 * created on 09.08.2005
 * 
 * @author chef
 * @version $Revision: 1.3 $
 */
public class IngridDocument extends HashMap implements Externalizable {

    public static final Integer DOCUMENT_ID = new Integer(0);

    public static final Integer DOCUMENT_CONTENT = new Integer(1);

    /**
     * Construtor for normal usage
     * 
     * @param id
     * @param content
     */
    public IngridDocument(Serializable id, Serializable content) {
        put(DOCUMENT_ID, id);
        put(DOCUMENT_CONTENT, content);

    }

    /**
     * Constructor for readExternal usage only
     */
    public IngridDocument() {
        // default constructor, use only to read values from Streams
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        int numberOfKeys = keySet().size();
        out.writeInt(numberOfKeys);
        Iterator iterator = keySet().iterator();
        while (iterator.hasNext()) {
            Serializable key = (Serializable) iterator.next();
            out.writeObject(key);
            Serializable value = (Serializable) get(key);
            if (value == null) {
                value = new NullWritable();
            }
            out.writeObject(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
     */
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int numberOfKeys = in.readInt();
        for (int i = 0; i < numberOfKeys; i++) {
            Serializable key = (Serializable) in.readObject();
            Serializable value = (Serializable) in.readObject();
            if (value instanceof NullWritable) {
                value = null;
            }
            put(key, value);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object key, Object value) {
        if (!(key instanceof Serializable)) {
            throw new IllegalArgumentException("key must be serializble");
        }
        if (value != null && !(value instanceof Serializable)) {
            throw new IllegalArgumentException("value must be serializble or null");
        }

        return super.put(key, value);
    }

    /**
     * @return the id of this document
     */
    public Serializable getId() {
        return (Serializable) get(DOCUMENT_ID);
    }

    /**
     * @return the content of the document
     */
    public Serializable getContent() {
        return (Serializable) get(DOCUMENT_CONTENT);
    }

    /**
     * puts a int value
     * 
     * @param key
     * @param value
     */
    public void putInt(Object key, int value) {
        put(key, new Integer(value));

    }

    /**
     * please use putInt for setting int values.
     * 
     * @param key
     * @return a int value for a given key
     * @throws ClassCastException
     *             in case the value isn't a int
     */
    public int getInt(Object key) {
        try {
            return ((Integer) get(key)).intValue();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("value to key is not int or wasn's setted with putInt");
        }

    }

    /**
     * @param key
     * @return casts the value of a key to an arraylist
     * @throws ClassCastException
     */
    public ArrayList getArrayList(Object key)  {
        try {
            return (ArrayList) get(key);    
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("value to key is not an arraylist");
        }
        

    }

    /**
     * Adds a value to array list, in case the arraylist does not exists under
     * the given key, we create the list. In case a object under this key is
     * already known we throw a ClasscastException
     * 
     * @param key
     * @param value
     */
    public void addToList(Object key, Object value) {
        ArrayList arrayList = getArrayList(key);
        if (arrayList == null) {
            arrayList = new ArrayList();
            put(key, arrayList);
        }
        arrayList.add(value);
    }

    /**
     * @param key
     * @return a boolean value to a key, in case the key does not exists we
     *         throws a illegal argument excetption
     */
    public boolean getBoolean(String key) {
        Boolean booleanObj = (Boolean) get(key);
        if (booleanObj == null) {
            throw new IllegalArgumentException("unknown key");
        }
        return booleanObj.booleanValue();
    }

    /**
     * Sets a boolean value,
     * 
     * @see IngridDocument#getBoolean(String)
     * @param key
     * @param value
     */
    public void setBoolean(String key, boolean value) {
        put(key, new Boolean(value));

    }

    /**
     * Just a place holder for <code>null</code> inside a stream
     */
    public class NullWritable implements Serializable {

        // just nothing .. :-)
    }

}
