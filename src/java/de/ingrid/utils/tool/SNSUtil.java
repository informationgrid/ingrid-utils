/*
 * Copyright (c) 2006 wemove digital solutions. All rights reserved.
 */
package de.ingrid.utils.tool;

import org.apache.commons.lang.StringUtils;

/**
 * TODO Describe your created type (class, etc.) here.
 *
 * @author joachim@wemove.com
 */
public class SNSUtil {
    
    /**
     * Transforms a SNS location topicId to the internal used ingrid format. 
     * This format follows the 8-digit kgs-Format (KreisGemeindeSchl�ssel) for 
     * the topic references "GEMEINDE, LAND, KREIS".
     * 
     * Other topic references remain unchanged. 
     * 
     * @param topicId The SNS topic ID.
     * @return The location reference.
     */
    public static String transformSpacialReference(String topicId) {
        final String[] TOPIC_REFS = {"GEMEINDE", "LAND", "KREIS"};
        
        String kgsCode = topicId;  
        
        for (int i=0; i< TOPIC_REFS.length; i++) {
            if (topicId.startsWith(TOPIC_REFS[i])) {
                kgsCode = topicId.substring(TOPIC_REFS[i].length());
                if (kgsCode.length() == 10) {
                    kgsCode = topicId.substring(TOPIC_REFS[i].length(), TOPIC_REFS[i].length() + 6);
                    kgsCode = kgsCode.concat(topicId.substring(TOPIC_REFS[i].length()+8));
                } else if (kgsCode.length() < 8) {
                    kgsCode = StringUtils.rightPad(kgsCode, 8, "0");
                }
                break;
            }
        }
        
        return kgsCode;
    }
    

}