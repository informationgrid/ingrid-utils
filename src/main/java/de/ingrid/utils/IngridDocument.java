/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
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
import java.util.List;
import java.util.Set;

/**
 * A data container for general usage in ingrid. Children should store all values via put in the mother to be available
 * via get.
 * 
 * 
 * created on 09.08.2005
 * 
 * @author chef
 * @version $Revision: 1.3 $
 */
public class IngridDocument extends HashMap<Object, Object> implements Externalizable {

   
    private static final long serialVersionUID = 2L;

    /**
     * Deprecated: The ID of the document as an integer. Use DOCUMENT_UID instead.
     */
    //@Deprecated
	//public static final Integer DOCUMENT_ID = new Integer(0);
    public static final String DOCUMENT_ID = "0";
    
    /**
     * This is the unique ID of a document, which can be any string value.
     */
    public static final String DOCUMENT_UID = "_id";

    /**
     * Comment for <code>DOCUMENT_CONTENT</code>
     */
	public static final Integer DOCUMENT_CONTENT = Integer.valueOf(1);

    /**
     * Construtor for normal usage
     * 
     * @param id
     * @param content
     */
    public IngridDocument(Serializable id, Serializable content) {
        // TODO: will be removed in a later version
        put(DOCUMENT_ID, id);
        put(DOCUMENT_UID, id);
        put(DOCUMENT_CONTENT, content);
    }

    /**
     * Constructor for readExternal usage only
     */
    public IngridDocument() {
        // default constructor, use only to read values from Streams
    }

    /**
     * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        int numberOfKeys = keySet().size();
        out.writeInt(numberOfKeys);
        Iterator<Object> iterator = keySet().iterator();
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

    /**
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

    /**
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
    //@Deprecated
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
        put(key, Integer.valueOf(value));

    }

    /**
     * please use putInt for setting int values.
     * 
     * @param key
     * @return a int value for a given key
     */
    public int getInt(Object key) {
        try {
            return ((Integer) get(key)).intValue();
        } catch (Exception e) {
            throw new IllegalArgumentException("value to key is not int or wasn's setted with putInt");
        }

    }

    /**
     * puts a long value
     * 
     * @param key
     * @param value
     */
    public void putLong(Object key, long value) {
        put(key, Long.valueOf(value));

    }

    /**
     * please use putLong for setting int values.
     * 
     * @param key
     * @return a long value for a given key
     */
    public long getLong(Object key) {
        try {
            return ((Long) get(key)).longValue();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("value to key is not long or wasn's setted with putLong");
        }

    }

    /**
     * @param key
     * @return casts the value of a key to an arraylist
     * @throws ClassCastException
     */
    @SuppressWarnings("unchecked")
    public List<Object> getArrayList(Object key) {
        try {
            return (List<Object>) get(key);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
              "value to key is not an arraylist: "
              + key.getClass().getName() + "(" + key.toString() + ") :"
              + get(key).getClass().getName() + "(" + get(key).toString() + ")");
        }
    }
    
    /**
     * @param key
     * @param value
     * @return true if document contained the given key
     */
    public boolean removeFromList(Object key, Object value) {
        boolean contained=false;
        List<?> list=getArrayList(key);
        if(list!=null) {
            contained= list.remove(value);
            if(list.isEmpty()){
                remove(key);
            }
        }
        return contained;
    }

    /**
     * Adds a value to array list, in case the arraylist does not exists under the given key, we create the list. In
     * case a object under this key is already known we throw a ClasscastException
     * 
     * @param key
     * @param value
     */
    public void addToList(Object key, Object value) {
        List<Object> arrayList = getArrayList(key);
        if (arrayList == null) {
            arrayList = new ArrayList<Object>();
            put(key, arrayList);
        }
        arrayList.add(value);
    }

    /**
     * @param key
     * @return a boolean value to a key, in case the key does not exists we throws a illegal argument excetption
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
    public void putBoolean(String key, boolean value) {
        put(key, Boolean.valueOf(value));

    }

    /**
	 * sets a array
	 * @param key
	 * @param values
	 */
	public void setArray(String key, Serializable[] values) {
		put(key, values);
	
	}

	/**
	 * @param key
	 * @return an array
	 */
	public Object[] getArray(String key) {
		return (Object[]) get(key);
	}

	/**
	 * stores a value as float
	 * 
	 * @param key
	 * @param value
	 */
	public void putFloat(String key, float value) {
		put(key, Float.valueOf(value));
	}

	/**
	 * @param key
	 * @return a float value
	 */
	public float getFloat(String key) {
        try {
         return ((Float) get(key)).floatValue();   
        } catch (Exception e) {
            throw new IllegalArgumentException("value to key is not float or wasn's setted with putInt");
        }
	}
	
	
	/**
	 * Convenience method: move type cast into method.
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		try {
			return (String) get(key);
		} catch (Exception e) {
			throw new IllegalArgumentException("Value to key is not String.");
		}
	}

	/**
     * Just a place holder for <code>null</code> inside a stream
     */
    public class NullWritable implements Serializable {

        private static final long serialVersionUID = 1L;
        
        

        // just nothing .. :-)
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Object> keys = keySet();
        builder.append("{");
        for (Object key : keys) {
            Object value = get(key);
            builder.append(DeepUtil.deepString(new Object[] { key }, 1) + ":" + DeepUtil.deepString(new Object[] { value }, 1));
        }
        builder.append("}");
        return builder.toString();
    }
}
