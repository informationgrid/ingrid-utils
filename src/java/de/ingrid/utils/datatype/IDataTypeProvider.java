package de.ingrid.utils.datatype;

public interface IDataTypeProvider {

    DataType[] getDataTypes();

    DataType[] getIncludedDataTypes(DataType dataType);

    DataType[] getIncludedDataTypes(String dataTypeName);

}
