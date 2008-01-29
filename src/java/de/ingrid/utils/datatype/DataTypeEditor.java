package de.ingrid.utils.datatype;

import java.beans.PropertyEditorSupport;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataTypeEditor extends PropertyEditorSupport {

    private static final Log LOG = LogFactory.getLog(DataTypeEditor.class);

    public String getAsText() {
        String ret = null;
        Object object = getValue();
        if (object != null) {
            DataType dataType = (DataType) object;
            ret = dataType.getName() + ".";
            ret += dataType.getDisplayName();
            Map metaDatas = dataType.getMetaDatas();
            Set keySet = metaDatas.keySet();
            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
                Object key = iterator.next();
                Object value = metaDatas.get(key);
                ret += "." + key + ":" + value;
            }
        }
        return ret;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        DataType dataType = null;
        try {
            String[] split = text.split("\\.");
            String name = split[0];
            String displayName = split[1];
            dataType = new DataType();
            dataType.setName(name);
            dataType.setDisplayName(displayName);
            for (int i = 2; i < split.length; i++) {
                String string = split[i];
                String[] metadatas = string.split(":");
                String key = metadatas[0];
                String value = metadatas[1];
                dataType.addMetadata(key, value);
            }
        } catch (Exception e) {
            LOG.error("can not create datatype", e);
        }
        setValue(dataType);
    }

}
