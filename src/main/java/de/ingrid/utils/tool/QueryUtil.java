/**
 * 
 */
package de.ingrid.utils.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.query.ClauseQuery;
import de.ingrid.utils.query.FieldQuery;
import de.ingrid.utils.query.IngridQuery;

/**
 * Constants and helper methods for IngridQuery etc.
 * This way we avoid changes in IngridQuery keeping compatibility between iPlugs. 
 */
public class QueryUtil {

    private static final Log LOG = LogFactory.getLog(QueryUtil.class);

    /** query field for adding any kind of control information to be processed by ibus or iplugs ! */
    public static final String FIELDNAME_METAINFO = "metainfo";

    /** Remove all occurences of field with given name in field list of given query !
     * @param query query to remove field from
     * @param fieldName name of field to be removed from query
     * @return list of removed FieldQuery elements, empty list if nothing removed !
     */
    public static List<FieldQuery> removeFieldFromQuery(IngridQuery query, String fieldName) {
        List<FieldQuery> retList = new ArrayList<FieldQuery>();
        
        boolean doRemoveField = true;
        while(doRemoveField) {
            FieldQuery removedFieldQuery = query.removeField(fieldName);
            if (removedFieldQuery != null) {
            	retList.add(removedFieldQuery);            	
            } else {
            	doRemoveField = false;
            }
        }
        if (LOG.isDebugEnabled()) {
        	for (FieldQuery fQ : retList) {
                LOG.debug("Removed field '" + fQ.getFieldName() + ":" + fQ.getFieldValue() + "' from query");
        	}
        }
        return retList;
    }

    /**
     * Recursive loop to extract field names from queries and clause subqueries.
     * @param query query to get fieldnames from
     * @param fieldList list to add fieldnames to. NOTICE: Duplicate fieldnames are possible if multiple times in query ! 
     */
    public static void getFieldNamesFromQuery(IngridQuery query, List<String> fieldList) {
        FieldQuery[] fields = query.getFields();
        for (int i = 0; i < fields.length; i++) {
            fieldList.add(fields[i].getFieldName());
        }
        ClauseQuery[] clauses = query.getClauses();
        for (int i = 0; i < clauses.length; i++) {
            getFieldNamesFromQuery(clauses[i], fieldList);
        }
    }

    /** Returns a deep copy of the passed query
     * @param query query to copy
     * @return deep copy of query.
     */
    public static IngridQuery deepCopy(IngridQuery query) throws Exception {
        if (LOG.isDebugEnabled()) {
        	LOG.debug("Create a deep copy of the following query: " + query);
        }
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            // serialize and pass the object
            oos.writeObject(query);
            oos.flush();
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);
            
            // create the new object
            IngridQuery deepCopy = (IngridQuery) ois.readObject();
            if (LOG.isDebugEnabled()) {
            	LOG.debug("Resulting deep copy: " + deepCopy);
            }

            return deepCopy;
        }
        catch(Exception e) {
        	LOG.error("Problems creating deep copy of IngridQuery '" + query + "'" + e);
            throw(e);
        }
        finally {
            oos.close();
            ois.close();
        }
    }
}
