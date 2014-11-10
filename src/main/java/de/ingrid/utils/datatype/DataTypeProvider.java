/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 wemove digital solutions GmbH
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
        List includedDataTypeNames = dataType.get("include");
        List includedDataTypes = new ArrayList(includedDataTypeNames.size());
        if (!includedDataTypeNames.isEmpty()) {
            for (int i = 0; i < includedDataTypeNames.size(); i++) {
                includedDataTypes.add(getMatchingDataType(dataTypes, (String) includedDataTypeNames.get(i)));
            }
        }
        return (DataType[]) includedDataTypes.toArray(new DataType[includedDataTypes.size()]);
    }

    private DataType getMatchingDataType(DataType[] allDatatypes, String dataTypeName) {
        for (int i = 0; i < allDatatypes.length; i++) {
            if (allDatatypes[i].getName().equals(dataTypeName)) {
                return allDatatypes[i];
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
