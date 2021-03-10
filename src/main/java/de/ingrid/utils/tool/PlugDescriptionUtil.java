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
/**
 * 
 */
package de.ingrid.utils.tool;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.PlugDescription;

/**
 * Constants and helper methods for PlugDescription etc.
 * This way we avoid changes in PlugDescription keeping compatibility ...
 */
public class PlugDescriptionUtil {

    private static final Log LOG = LogFactory.getLog(PlugDescriptionUtil.class);

    /** Add a field to the PlugDescription.
     * e.g. add field "metainfo", so control data from query can be passed to iplug (plug won't be filtered !)
     * @param pd pd where field is added
     * @param fieldname field to be added
     * @return true=field was added, false=field was already in pd
     */
    public static boolean addFieldToPlugDescription(PlugDescription pd, String fieldname) {
        List<String> pdFields = Arrays.asList(pd.getFields());
    	if (!pdFields.contains(fieldname)) {
            pd.addField(fieldname);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Added field '" + fieldname + "' to plugdescription.");                    	
            }
            return true;
    	}
        
        return false;
    }

    /** Checks whether PD contains the given field
     * @param pd
     * @param fieldname
     * @return true=pd contains fieldname, false=no field with given name
     */
    public static boolean hasField(PlugDescription pd, String fieldname) {
    	return StringUtil.containsString(pd.getFields(), fieldname);
    }
}
