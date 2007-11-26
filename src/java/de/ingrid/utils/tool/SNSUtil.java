/*
 * Copyright (c) 2006 wemove digital solutions. All rights reserved.
 */
package de.ingrid.utils.tool;

/**
 * TODO Describe your created type (class, etc.) here.
 * 
 * @author joachim@wemove.com
 */
public class SNSUtil {

    /**
     * Transforms a SNS location topicId to the internal used ingrid format. This format follows the 8-digit kgs-Format
     * (KreisGemeindeSchlüssel) for the topic references "GEMEINDE, BUNDESLAND, KREIS".
     * 
     * Other topic references remain unchanged.
     * 
     * @param nativeKey
     *            The SNS native key.
     * @return The location reference.
     */
    public static String transformSpacialReference(String nativeKey) {
        String agsCode = nativeKey;

        final String agsString = "ags:";
        int index = nativeKey.indexOf(agsString);
        if (index != -1) {
            final int startIndex = index + agsString.length();
            agsCode = nativeKey.substring(startIndex, startIndex + 7);
        }

        return agsCode;
    }

}
