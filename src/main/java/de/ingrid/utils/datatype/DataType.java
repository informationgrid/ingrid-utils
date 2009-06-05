package de.ingrid.utils.datatype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DataType {

    private String _name;

    private String _displayName;

    private Set _metaDatas = new LinkedHashSet();

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

    public Set getMetaDatas() {
        return _metaDatas;
    }

    public boolean contains(Object key, Object value) {
        return _metaDatas.contains(new Pair(key, value));
    }

    public List get(String key) {
        List values = new ArrayList();
        for (Iterator iterator = _metaDatas.iterator(); iterator.hasNext();) {
            Pair pair = (Pair) iterator.next();
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
