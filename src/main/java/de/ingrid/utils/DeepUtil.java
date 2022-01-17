/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2022 wemove digital solutions GmbH
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
package de.ingrid.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeepUtil {

    public static final int MAX_DEEP = 50;

    private static final Log LOG = LogFactory.getLog(DeepUtil.class);

    public static String deepString(Object object, int deep) {
        String ret = "";
        if (deep < MAX_DEEP) {
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                for (Object object2 : objects) {
                    ret += "_" + deepString(object2, deep + 1);
                }
            } else {
                ret += "_" + ((object == null) ? "" : object.toString());
            }
        }
        return ret;
    }
    
    public static int deepHashcode(Object object, int deep) {
        final int prime = 31;
        int result = 1;
        if ((object instanceof Object[]) && deep < MAX_DEEP) {
            Object[] objects = (Object[]) object;
            for (Object object2 : objects) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("compute hashcode recursive with deep: " + deep);
                }
                result = prime * result + deepHashcode(object2, deep + 1);
            }
        } else {
            result = prime * result + ((object == null) ? 0 : object.hashCode());
        }
        return result;
    }
    
}
