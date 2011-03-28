/*
 * Copyright (c) 2011 wemove digital solutions. All rights reserved.
 */
package de.ingrid.utils.tool;

/**
 * String Helper methods.
 */
public class StringUtil {

    /** Replace all "\n" and "\r" with the given string
     * @param inString String where line feeds will be replaced
     * @param replaceWith The string to replace line feeds with
     * @return
     */
    public static String replaceLineFeeds(String inString, String replaceWith) {
        return inString.replace("\n", replaceWith).replace("\r", replaceWith);
    }

}
