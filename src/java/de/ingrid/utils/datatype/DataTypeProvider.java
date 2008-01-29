package de.ingrid.utils.datatype;

import java.beans.PropertyEditor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataTypeProvider implements IDataTypeProvider {

    private static final Log LOG = LogFactory.getLog(DataTypeProvider.class);

    private final PropertyEditor _propertyEditor;

    private final File _datatypeProperty;

    public DataTypeProvider(File datatypeProperty, PropertyEditor propertyEditor) {
        _datatypeProperty = datatypeProperty;
        _propertyEditor = propertyEditor;
    }

    public DataType[] getDataTypes() {
        List datatypes = new ArrayList();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(_datatypeProperty);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                _propertyEditor.setAsText(line);
                Object value = _propertyEditor.getValue();
                datatypes.add((DataType) value);
            }
            bufferedReader.close();
        } catch (IOException e) {
            LOG.warn("can not load datatype file.", e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                LOG.warn("can not close datatype-resource-stream.", e);
            }
        }

        return (DataType[]) datatypes.toArray(new DataType[datatypes.size()]);
    }

}
