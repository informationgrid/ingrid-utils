/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
/**
 * 
 */
package de.ingrid.utils.idf;

import de.ingrid.utils.dsc.Record;
import de.ingrid.utils.tool.GZipTool;

/**
 * Tool for all IDF (IngGrid Detaildata Format) helper methods.
 * 
 * @author joachim@wemove.com
 * 
 */
public class IdfTool {

    /**
     * Key used in a record for the IDF data.
     */
    public static final String KEY_DATA = "data";

    /**
     * Key used in a record for flagging if the data is compressed or not.
     */
    public static final String KEY_COMPRESSED = "compressed";

    /**
     * Compresses the content in record["data"] and sets record["compressed"] =
     * "true".
     * 
     * @param record
     * @return
     */
    public static Record compressIdfRecord(Record record) {
        record.put(KEY_DATA, GZipTool.gzip((String) record.get(KEY_DATA)));
        record.put(KEY_COMPRESSED, "true");
        return record;
    }

    /**
     * Decompress the content in record["data"] if record["compressed"] ==
     * "true". Does nothing if not.
     * 
     * @param record
     * @return
     */
    public static Record uncompressIdfRecord(Record record) {
        if (record.get(KEY_COMPRESSED) != null && record.get(KEY_COMPRESSED).equals("true")) {
            record.put(KEY_DATA, GZipTool.ungzip((String) record.get(KEY_DATA)));
            record.remove(KEY_COMPRESSED);
        }
        return record;
    }

    /**
     * Retrieve the IDF data from a record. Uncompresses the data if needed.
     * 
     * @param record
     * @return
     */
    public static String getIdfDataFromRecord(Record record) {
        uncompressIdfRecord(record);
        return record.getString(KEY_DATA);
    }

    /**
     * Creates a IDF record from a String. Compresses the record according to
     * property compressed.
     * 
     * @param idf
     * @param compressed
     * @return
     */
    public static Record createIdfRecord(String idf, boolean compressed) {
        Record r = new Record();
        r.put(KEY_DATA, idf);
        if (compressed) {
            compressIdfRecord(r);
        }
        return r;
    }

}
