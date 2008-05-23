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

    private final File _datatypeFile;

    public DataTypeProvider(File datatypeFile, PropertyEditor propertyEditor) {
        _datatypeFile = datatypeFile;
        _propertyEditor = propertyEditor;
    }

    public DataType[] getDataTypes() {
        List datatypes = new ArrayList();
        readInDatatypes(datatypes);

        return (DataType[]) datatypes.toArray(new DataType[datatypes.size()]);
    }

    /**
     * @return all datatypes the given datatype includes. Nested includes are
     *         not supported.
     */
    public DataType[] getIncludedDataTypes(DataType dataType) {
        return getIncludedDataTypes(getDataTypes(), dataType);
    }

    public DataType[] getIncludedDataTypes(String dataTypeName) {
        DataType[] allDatatypes = getDataTypes();
        return getIncludedDataTypes(allDatatypes, getMatchingDataType(allDatatypes, dataTypeName));
    }

    private DataType[] getIncludedDataTypes(DataType[] dataTypes, DataType dataType) {
        List<Object> includedDataTypeNames = dataType.get("include");
        List<DataType> includedDataTypes = new ArrayList<DataType>(includedDataTypeNames.size());
        if (!includedDataTypeNames.isEmpty()) {
            for (Object dataTypeName : includedDataTypeNames) {
                includedDataTypes.add(getMatchingDataType(dataTypes, (String) dataTypeName));
            }
        }
        return includedDataTypes.toArray(new DataType[includedDataTypes.size()]);
    }

    private DataType getMatchingDataType(DataType[] allDatatypes, String dataTypeName) {
        for (DataType dataType : allDatatypes) {
            if (dataType.getName().equals(dataTypeName)) {
                return dataType;
            }
        }
        throw new IllegalStateException("could not found datatype with name '" + dataTypeName + "'");
    }

    private void readInDatatypes(List datatypes) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(_datatypeFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                datatypes.add(extractDatatype(line));
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
    }

    private Object extractDatatype(String line) {
        _propertyEditor.setAsText(line);
        return _propertyEditor.getValue();
    }

}
