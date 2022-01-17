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
package de.ingrid.utils.datatype;

import java.beans.PropertyEditorSupport;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.datatype.DataType.Pair;

public class DataTypeEditor extends PropertyEditorSupport {

    private static final Log LOG = LogFactory.getLog(DataTypeEditor.class);

    public String getAsText() {
        String ret = null;
        Object object = getValue();
        if (object != null) {
            DataType dataType = (DataType) object;
            ret = dataType.getName() + ".";
            ret += dataType.getDisplayName();
            Set<Pair> metaDatas = dataType.getMetaDatas();
            for (Iterator<Pair> iterator = metaDatas.iterator(); iterator.hasNext();) {
                Pair pair = iterator.next();
                ret += "." + pair.getKey() + ":" + pair.getValue();
            }
        }
        return ret;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        DataType dataType = null;
        try {
            String[] split = text.split("\\.");
            String name = split[0];
            String displayName = split[1];
            dataType = new DataType();
            dataType.setName(name);
            dataType.setDisplayName(displayName);
            for (int i = 2; i < split.length; i++) {
                String string = split[i];
                String[] metadatas = string.split(":");
                String key = metadatas[0];
                String value = metadatas[1];
                dataType.addMetadata(key, value);
            }
        } catch (Exception e) {
            LOG.error("can not create datatype from '" + text + "'", e);
        }
        setValue(dataType);
    }

}
