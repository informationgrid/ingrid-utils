/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2016 wemove digital solutions GmbH
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
     * Transforms a SNS location native key into the representation used within ingrid. 
     * It strips the namespace from the native key. If two namespaces are supplied, the returned 
     * namespace can be selected by namespace. If the namespace is null
     * automatic namespace extraction will be applied.
     * 
     * If no namespace was found in nativeKey the original nativeKey will be returned. 
     * 
     * @param nativeKey
     *            The SNS native key.
     * @param namespace
     *            The namespace of the native key ("rs:" or "ags:" or null for automatic namespace cutting).
     * @return The location reference.
     */
    public static String transformSpacialReference(String namespace, String nativeKey) {
        String agsCode = nativeKey;

        int index;
        if (namespace == null) {
        	index = nativeKey.indexOf(':');
        	agsCode = nativeKey.substring(index + 1);
        	index = agsCode.indexOf(' ');
        	if (index != -1) { 
        		agsCode = agsCode.substring(0, index);
        	}
        } else {
	        index = nativeKey.indexOf(namespace);
	        if (index != -1) {
	            final int startIndex = index + namespace.length();
	            int endIndex = nativeKey.indexOf(' ', startIndex);
	            if (endIndex < 0) {
	                agsCode = nativeKey.substring(startIndex);
	            } else {
	                agsCode = nativeKey.substring(startIndex, endIndex);
	            }
	        }
        }

        return agsCode;
    }

    /** Prepare topicId for transport via IngridQuery.
     * Topic ID from GSSoil ThesaurusService is URL. Has to be processed, put in quotes ... */
    public static String marshallTopicId(String topicId) {
    	String result = topicId;
    	
    	if (topicId != null) {
    		if (topicId.indexOf("/") != -1) {
    			result = topicId.replace("/", "\\");
    			result = "\"" + result + "\"";
    		}
    	}

    	return result;
    }

    /** Bring topicId back to original state after transport via IngridQuery.
     * Topic ID from GSSoil ThesaurusService is URL. Was processed, put in quotes ... */
    public static String unmarshallTopicId(String topicId) {
    	String result = topicId;
    	
    	if (result != null) {
    		if (result.indexOf("\\") != -1) {
    			result = result.replace("\\", "/");
    			result = result.replace("\"", "");
    		}
    	}

    	return result;
    }
}
