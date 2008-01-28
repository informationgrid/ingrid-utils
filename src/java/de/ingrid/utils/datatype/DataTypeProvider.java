package de.ingrid.utils.datatype;

import java.beans.PropertyEditor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataTypeProvider implements IDataTypeProvider {

    private static final String DATA_TYPE_FILE_NAME = "datatypes.properties";

    private static final Log LOG = LogFactory.getLog(DataTypeProvider.class);

    private final PropertyEditor _propertyEditor;

    public DataTypeProvider(PropertyEditor propertyEditor) {
        _propertyEditor = propertyEditor;
    }

    public DataType[] getDataTypes() {
        InputStream resourceAsStream = DataTypeProvider.class.getResourceAsStream("/" + DATA_TYPE_FILE_NAME);
        List datatypes = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
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
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            } catch (IOException e) {
                LOG.warn("can not close datatype-resource-stream.", e);
            }
        }

        return (DataType[]) datatypes.toArray(new DataType[datatypes.size()]);
    }

}
