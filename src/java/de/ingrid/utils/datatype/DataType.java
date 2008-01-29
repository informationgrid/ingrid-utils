package de.ingrid.utils.datatype;

import java.util.Map;

public class DataType {

    private String _name;

    private String _displayName;

    private Map _metaDatas = new java.util.LinkedHashMap();

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
        _metaDatas.put(key, value);
    }

    public Map getMetaDatas() {
        return _metaDatas;
    }

    public boolean contains(Object key, Object value) {
        Object object = _metaDatas.get(key);
        return value.equals(object);
    }

}
