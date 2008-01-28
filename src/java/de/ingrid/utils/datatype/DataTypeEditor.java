package de.ingrid.utils.datatype;

import java.beans.PropertyEditorSupport;

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
            ret += dataType.getDisplayName() + ".";
            ret += dataType.isVisible();
        }
        return ret;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        DataType dataType = null;
        try {
            String[] split = text.split("\\.");
            String name = split[0];
            String displayName = split[1];
            String visible = split[2];
            dataType = new DataType();
            dataType.setName(name);
            dataType.setDisplayName(displayName);
            dataType.setVisible(Boolean.valueOf(visible).booleanValue());
        } catch (Exception e) {
            LOG.error("can not create datatype", e);
        }
        setValue(dataType);
    }

}
