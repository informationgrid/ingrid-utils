/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
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
import de.ingrid.utils.query.FuzzyFieldQuery;
import de.ingrid.utils.query.FuzzyTermQuery;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.query.RangeQuery;
import de.ingrid.utils.query.TermQuery;
import de.ingrid.utils.query.WildCardFieldQuery;
import de.ingrid.utils.query.WildCardTermQuery;

/**
 * Constants and helper methods for IngridQuery etc. This way we avoid changes
 * in IngridQuery keeping compatibility between iPlugs.
 */
public class QueryUtil {

    private static final Log LOG = LogFactory.getLog(QueryUtil.class);

    /**
     * query field for adding any kind of control information to be processed by
     * ibus or iplugs !
     */
    public static final String FIELDNAME_METAINFO  = "metainfo";
    
    /**
     * query field used by iPlug-SE so far for searching in sns data for more
     * results. Other iPlugs should ignore this field!
     */
    public static final String FIELDNAME_INCL_META = "incl_meta";

    /**
     * Remove all occurences of field with given name in field list of given
     * query !
     * 
     * @param query
     *            query to remove field from
     * @param fieldName
     *            name of field to be removed from query
     * @return list of removed FieldQuery elements, empty list if nothing
     *         removed !
     */
    public static List<FieldQuery> removeFieldFromQuery(IngridQuery query, String fieldName) {
        List<FieldQuery> retList = new ArrayList<FieldQuery>();

        boolean doRemoveField = true;
        while (doRemoveField) {
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
     * 
     * @param query
     *            query to get fieldnames from
     * @param fieldList
     *            list to add fieldnames to. NOTICE: Duplicate fieldnames are
     *            possible if multiple times in query !
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

    /**
     * Returns a deep copy of the passed query
     * 
     * @param query
     *            query to copy
     * @return deep copy of query.
     */
    public static IngridQuery deepCopy(IngridQuery query) throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Create a deep copy of the following query: " + query);
        }
        
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);      
                ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bin);
                
        ) {
            
            // serialize and pass the object
            oos.writeObject(query);
            oos.flush();

            // create the new object
            IngridQuery deepCopy = (IngridQuery) ois.readObject();
            if (LOG.isDebugEnabled()) {
                LOG.debug("Resulting deep copy: " + deepCopy);
            }

            return deepCopy;
        } catch (Exception e) {
            LOG.error("Problems creating deep copy of IngridQuery '" + query + "'" + e);
            throw (e);
        }
    }

    /**
     * Creates a String representation
     * 
     * @param q
     * @return
     */
    public static String query2String(IngridQuery q) {
        StringBuilder sb = new StringBuilder();
        for (TermQuery tq : q.getTerms()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(tq.isProhibited() ? "-" : (tq.isRequred() ? "+" : ""));
            sb.append(tq.getTerm());
        }
        for (FuzzyTermQuery ftq : q.getFuzzyTermQueries()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(ftq.isProhibited() ? "-" : (ftq.isRequred() ? "+" : ""));
            sb.append(ftq.getTerm() + "~");
        }
        for (WildCardTermQuery wtq : q.getWildCardTermQueries()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(wtq.isProhibited() ? "-" : (wtq.isRequred() ? "+" : ""));
            sb.append(wtq.getTerm() + "~");
        }
        for (FieldQuery fq : q.getFields()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(fq.isProhibited() ? "-" : (fq.isRequred() ? "+" : ""));
            sb.append(fq.getFieldName() + ":" + fq.getFieldValue());
        }
        for (FuzzyFieldQuery ffq : q.getFuzzyFieldQueries()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(ffq.isProhibited() ? "-" : (ffq.isRequred() ? "+" : ""));
            sb.append(ffq.getFieldName() + ":" + ffq.getFieldValue() + "~");
        }
        for (WildCardFieldQuery wfq : q.getWildCardFieldQueries()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(wfq.isProhibited() ? "-" : (wfq.isRequred() ? "+" : ""));
            sb.append(wfq.getFieldName() + ":" + wfq.getFieldValue() + "*");
        }
        for (RangeQuery rq : q.getRangeQueries()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(rq.isProhibited() ? "-" : (rq.isRequred() ? "+" : ""));
            sb.append(rq.getRangeName() + ":");
            sb.append(rq.isInclusive() ? "[" : "{");
            sb.append(rq.getRangeFrom() + " TO " + rq.getRangeTo());
            sb.append(rq.isInclusive() ? "]" : "}");
        }
        for (String datatype : q.getPositiveDataTypes()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append("+datatype:" + datatype);
        }
        for (String datatype : q.getNegativeDataTypes()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append("-datatype:" + datatype);
        }
        for (String partner : q.getPositivePartner()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append("+partner:" + partner);
        }
        for (String partner : q.getNegativePartner()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append("-partner:" + partner);
        }
        for (String provider : q.getPositiveProvider()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append("+provider:" + provider);
        }
        for (String provider : q.getNegativeProvider()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append("-provider:" + provider);
        }
        for (IngridQuery clause : q.getClauses()) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(clause.isProhibited() ? "-" : (clause.isRequred() ? "+" : ""));
            sb.append("(");
            sb.append(query2String(clause));
            sb.append(")");
        }
        return sb.toString();
    }

}
