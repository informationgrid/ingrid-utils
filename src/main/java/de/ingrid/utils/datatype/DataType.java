/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2015 wemove digital solutions GmbH
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
package de.ingrid.utils.datatype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DataType {

    private String _name;

    private String _displayName;

    private Set<Pair> _metaDatas = new LinkedHashSet<Pair>();

    public class Pair {
        private Object _key;

        private Object _value;

        public Pair(Object key, Object value) {
            _key = key;
            _value = value;
        }

        public Object getKey() {
            return _key;
        }

        public Object getValue() {
            return _value;
        }

        public boolean equals(Object obj) {
            Pair other = (Pair) obj;
            return other._key.equals(_key) && other._value.equals(_value);
        }

        public int hashCode() {
            return _key.hashCode() + _value.hashCode();
        }
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDisplayName() {
        return _displayName;
    }

    public void setDisplayName(String displayName) {
        _displayName = displayName;
    }

    public void addMetadata(Object key, Object value) {
        _metaDatas.add(new Pair(key, value));
    }

    public Set<Pair> getMetaDatas() {
        return _metaDatas;
    }

    public boolean contains(Object key, Object value) {
        return _metaDatas.contains(new Pair(key, value));
    }

    public List<Object> get(String key) {
        List<Object> values = new ArrayList<Object>();
        for (Iterator<Pair> iterator = _metaDatas.iterator(); iterator.hasNext();) {
            Pair pair = iterator.next();
            if (pair.getKey().equals(key)) {
                values.add(pair.getValue());
            }

        }
        return values;
    }

    public int hashCode() {
        return _name.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DataType)) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }

    public String toString() {
        return _name;
    }
}
