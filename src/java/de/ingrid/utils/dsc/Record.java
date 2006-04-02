/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.dsc;

import java.util.ArrayList;
import java.util.Arrays;

import de.ingrid.utils.IngridDocument;

/**
 * Container for a database record
 * 
 * @author sg
 * 
 */
public class Record extends IngridDocument {

    private static final long serialVersionUID = (long) Record.class.getName().hashCode();

    private static final String COLUMNS = "columns";

    private static final String VALUES = "values";

    private static final String SUB_RECORDS = "subRecords";

    public static final int XML = 0;

    public static final int DB = 1;

    private static final String RECORD_TYPE = "recordType";

    private static final String XML_CONTENT = "xmlContent";

    
    public Record() {
        // for serialization
    }
    
    public Record(int type) {
        setType(type);
    }

    /**
     * sets the type of the record
     * @param type
     */
    public void setType(int type) {
        putInt(RECORD_TYPE, type);
    }
    
    /**
     * @return record type (db or xml)
     */
    public int getType() {
        return  getInt(RECORD_TYPE);
    }

    /**
     * Adds a column and the value to the record
     * 
     * @param column
     * @param value
     */
    public void addColumn(Column column, String value) {
        addToList(COLUMNS, column);
        addToList(VALUES, value);
        put(column.toString(), value);
    }

    /**
     * @return ge number of columns
     */
    public int numberOfColumns() {
        return getArrayList(COLUMNS).size();

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("new record: ");

        int count = numberOfColumns();
        for (int i = 0; i < count; i++) {
            buffer.append(getArrayList(COLUMNS).get(i));
            buffer.append(" : ");
            buffer.append(getArrayList(VALUES).get(i));
            buffer.append(" ");
        }
        buffer.append("\n");

        Record[] subRecords = getSubRecords();
        if (subRecords != null) {
            for (int i = 0; i < subRecords.length; i++) {
                buffer.append("\t");
                buffer.append(subRecords[i].toString());
                buffer.append("\n");
            }
        }

        buffer.append("\n");
        return buffer.toString();
    }

    /**
     * sets the subrecords
     * 
     * @param subRecords
     */
    public void addSubRecords(Record[] subRecords) {
        ArrayList arrayList = getArrayList(SUB_RECORDS);
        if (arrayList == null) {
            arrayList = new ArrayList();
            put(SUB_RECORDS, arrayList);
        }
        arrayList.addAll(Arrays.asList(subRecords));
    }

    /**
     * @return the subRecords.
     */
    public Record[] getSubRecords() {
        ArrayList arrayList = getArrayList(SUB_RECORDS);
        if (arrayList != null) {
            return (Record[]) arrayList.toArray(new Record[arrayList.size()]);
        }
        return new Record[0];
    }

    /**
     * @param i
     * @return a column
     */
    public Column getColumn(int i) {
        return (Column) getArrayList(COLUMNS).get(i);

    }

    /**
     * @param i
     * @return a column value
     */
    public String getValueAsString(Column column) {
        if (containsKey(column.toString())) {
            String value = (String) get(column.toString());
            if (value == null) {
                return "";
            }
            return value;
        }
        Record[] subRecords = getSubRecords();
        for (int i = 0; i < subRecords.length; i++) {
            Record record = subRecords[i];
            String valueAsString = record.getValueAsString(column);
            if (valueAsString != null) {
                return valueAsString;
            }
        }
        return null;

    }

    // /**
    // * @param i
    // * @return a column value
    // */
    // public Date getValueAsDate(int i) {
    // return (Date) getArrayList(VALUES).get(i);
    // }

    /**
     * @param column
     * @return the value of a given column
     */
    public String getValueForColumn(Column column) {
        return getValueAsString(column);
        // Column[] columns = getColumns();
        //		
        // for (int i = 0; i < columns.length; i++) {
        // Column oneColumn = columns[i];
        // if (oneColumn.toString().equals(column.toString())) {
        // return getValueAsString(i);
        // }
        //			
        // }
        // throw new IllegalArgumentException("no value for given column: "
        // + column.toString());

    }

    public Column[] getColumns() {
        ArrayList arrayList = getArrayList(COLUMNS);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        // Record[] subRecords = getSubRecords();
        // for (int i = 0; i < subRecords.length; i++) {
        // Record subRecord = subRecords[i];
        // arrayList.addAll(Arrays.asList(subRecord.getColumns()));
        // }
        return (Column[]) arrayList.toArray(new Column[arrayList.size()]);
    }

    
    
    /**
     * Sets the xml content of the record
     * @param value
     */
    public void setXML(String value) {
       put(XML_CONTENT, value);
    }

    /**
     * @return the xml content of this record
     */
    public String getXML(){
        return (String) get(XML_CONTENT);
    }
}
