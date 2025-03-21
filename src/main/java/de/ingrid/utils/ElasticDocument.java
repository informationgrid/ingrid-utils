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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticDocument extends HashMap<String, Object>{
    
    /**
     * 
     */
    private static final long serialVersionUID = -9176076545333543500L;
    
    public ElasticDocument() {}
    
    public ElasticDocument( Map<String, Object> map ) {
        super( map );
    }

    /**
     * Add a key with its value to the analyzed document, which will be used for indexing.
     * If the key is already present in the document, then the old value will be converted
     * to a list and the new value appended to it.
     * 
     * @param key is the name of the field to be stored
     * @param value is the value of the field
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object put(String key, Object value) {
        Object obj = get( key );
        // if key does not exist yet then just add key/value to document
        if (obj == null) {
            super.put( key, value );
        } else {
            // if the object behind the key already is an array, we just add the value to this array
            if (obj instanceof List<?>) {
                ((List<Object>) obj).add( value );
            } else {
                // this is the second time where the same key is added, so we have to convert the object
                // into a list of objects
                ArrayList<Object> list = new ArrayList<Object>();
                list.add( obj );
                list.add( value );
                super.put( key, list );
            }
        }
        return get( key );
    }
    
    /**
     * Get the value(s) for a given key as a string array. If the value is null, an empty array
     * will be returned. If the value is a string, an array with one entry is returned. If the
     * value is a list, then it will be converted to an array.
     * @param key is the key to look for value(s)
     * @return
     */
    @SuppressWarnings("unchecked")
    public String[] getValues(String key) {
        Object obj = get( key );
        String[] result = null;
        
        if (obj == null) {
            result = new String[0];
        } else if (obj instanceof List<?>) {
            return ((List<Object>) obj).toArray( new String[0] );
        } else {
            result = new String[] { (String) obj };
        }
        return result;
    }

}
