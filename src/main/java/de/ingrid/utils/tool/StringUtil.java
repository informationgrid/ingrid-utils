/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
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
/*
 * Copyright (c) 2011 wemove digital solutions. All rights reserved.
 */
package de.ingrid.utils.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * String Helper methods.
 */
public class StringUtil {

    /** Make a string to a "one liner" !
     * Replaces all "\n" and "\r" with the given string. ALSO removes leading whitespaces (trim()) !
     * @param inString String where line feeds will be replaced
     * @param replaceWith The string to replace line feeds with
     * @return
     */
    public static String replaceLineFeeds(String inString, String replaceWith) {
        return inString.replace("\n", replaceWith).replace("\r", replaceWith).trim();
    }

    /** Remove all occurences of the given string from the given string list !
     * @param stringList list to remove string from
     * @param stringToRemove the string to remove
     * @return cleared list, no occurence of given stringToRemove anymore
     */
    public static List<String> removeStringFromStringList(List<String> stringList, String stringToRemove) {
        // Query may contain "metainfo" field not supported by all iplugs !
        // Remove "metainfo" field from field list for checking ! So old iplugs won't be removed !
    	Iterator<String> it = stringList.iterator();
    	while(it.hasNext()) {
            if (stringToRemove.equals(it.next())) {
            	it.remove();
            }
    	}
    	
    	return stringList;
    }

    /** Remove all occurences of the given string from the given string array !
     * @param stringArray array to remove string from
     * @param stringToRemove the string to remove
     * @return cleared array, no occurence of given stringToRemove anymore
     */
    public static String[] removeStringFromStringArray(String[] stringArray, String stringToRemove) {
        List<String> clearedList =
        	StringUtil.removeStringFromStringList(new ArrayList<String>(Arrays.asList(stringArray)), stringToRemove);
    	return clearedList.toArray(new String[clearedList.size()]);
    }

    /** Does the given array contain the given string ?
     * @param stringArray the array
     * @param stringToContain the string to be contained
     * @return true=array contains string, false=array does NOT contain string
     */
    public static boolean containsString(String[] stringArray, String stringToContain) {
    	return Arrays.asList(stringArray).contains(stringToContain);
    }
}
