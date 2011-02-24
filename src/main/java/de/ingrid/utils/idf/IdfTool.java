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
     * Compresses the content in record["data"] and sets record["compressed"] =
     * "true".
     * 
     * @param record
     * @return
     */
    public static Record compressIdfRecord(Record record) {
        record.put("data", GZipTool.gzip((String) record.get("data")));
        record.put("compressed", "true");
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
        if (record.get("compressed") != null
                && record.get("compressed").equals("true")) {
            record.put("data", GZipTool.ungzip((String) record.get("data")));
        }
        return record;
    }
    

}
