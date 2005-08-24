/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Controller that parse a string and return a IngridQuery object
 * 
 * created on 09.08.2005
 * @author  sg
 * @version $Revision: 1.3 $
 */
public class QueryParser {

    /**
     * @param queryString
     * @return query object form string
     * @throws ParseException
     *             in case sys
     */
    public static IngridQuey parseQuery(String queryString) throws ParseException {
        if (queryString == null) {
            throw new ParseException("queryString can not be null", -1);
        }

        queryString = removePattern(queryString, "  ");
        queryString = removePattern(queryString, "AND");
        return processQuery(queryString.trim());
    }

    private static IngridQuey processQuery(String queryString) throws ParseException {
        System.out.println("---------------------------");
        System.out.println(queryString);
        long queryKey = (System.currentTimeMillis() + queryString.hashCode());
        IngridQuey quey = new IngridQuey(queryKey, queryString);

        ArrayList orQueries = new ArrayList();
        ArrayList subQueries = new ArrayList();
        ArrayList terms = new ArrayList();
        int start = 0;
        String[] keys = new String[] { "AND", "OR", "(", ")", " " };

        int[] nextMarker = getNextMarker(queryString, keys, start);
        int pos = nextMarker[0];
        int key = nextMarker[1];
        boolean toContinue = true;
        while (toContinue) {
            System.out.println("iteration...");

            if (pos > -1) {

                switch (key) {
                case 0:
                    System.err.println("and");
                    break;
                case 1:
                    orQueries.add(processQuery(queryString.substring(0, pos)));
                    pos = pos + 2; // since it is OR
                    // String restString = queryString.substring(pos ,
                    // queryString.length());
                    int[] orEndMarker = getNextMarker(queryString, new String[] { "(", "AND", "OR" }, pos);
                    orQueries.add(processQuery(queryString.substring(pos, orEndMarker[0])));
                    start = orEndMarker[0] - 1;
                    break;
                case 2:
                    subQueries.add(processQuery(queryString.substring(0, pos).trim()));
                    int[] bracketEndMarker = getNextMarker(queryString, new String[] { ")" }, pos);
                    subQueries.add(processQuery(queryString.substring(pos + 1, bracketEndMarker[0])));
                    start = bracketEndMarker[0] + 1;
                    break;
                case 3:
                    throw new ParseException("missing open bracket, found closing: ", pos);
                case 4:
                    
                    
                    terms.add(queryString.substring(start, pos));
                    start = pos + 1;
                    break;
                case -1:
                    // no pattern more found
                    if (start < pos) {
                    
                        terms.add(queryString.substring(start, pos));
                    }
                    toContinue = false;
                    break;
                }
            }
            nextMarker = getNextMarker(queryString, keys, start);
            pos = nextMarker[0];
            key = nextMarker[1];
            System.out.println("start: " +start + " pos: " +pos + " key: "+ key);
        }
        quey.put(IngridQuey.SUB_QUERIES, subQueries.toArray(new IngridQuey[subQueries.size()]));
        quey.put(IngridQuey.OR_QUERIES, orQueries.toArray(new IngridQuey[orQueries.size()]));
        quey.put(IngridQuey.Terms, terms.toArray(new String[terms.size()]));
        return quey;
    }

    private static int[] getNextMarker(String queryString, String[] keys, int start) {
        // System.out.println("marker for: " + queryString + "start: " + start);
        int[] pos = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            pos[i] = queryString.indexOf(keys[i], start);
        }
        int bestMatch = queryString.length();
        int keyPointer = -1;
        for (int i = 0; i < pos.length; i++) {
            if (pos[i] < bestMatch && pos[i] > -1) {
                bestMatch = pos[i];
                keyPointer = i;
            }
        }
        return new int[] { bestMatch, keyPointer };

    }

    /**
     * @param queryString
     * @return
     */
    private static String removePattern(final String queryString, final String pattern) {
        StringBuffer buffer = new StringBuffer();
        int start = 0;
        int end = -1;
        int offset = pattern.length();
        boolean nothingDone = true;
        while ((end = queryString.indexOf(pattern, start)) > -1) {
            buffer.append(queryString.substring(start, end));
            start = end + offset;
            nothingDone = false;
        }
        if (nothingDone) {
            return queryString;
        }
        buffer.append(queryString.subSequence(start, queryString.length()));
        return buffer.toString();
    }
}
