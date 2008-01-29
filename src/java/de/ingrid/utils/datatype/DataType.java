package de.ingrid.utils.datatype;

import java.util.LinkedHashSet;
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

}
