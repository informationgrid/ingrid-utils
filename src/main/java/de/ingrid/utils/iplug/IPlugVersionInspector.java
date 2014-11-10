/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 wemove digital solutions GmbH
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
package de.ingrid.utils.iplug;

import java.util.ArrayList;

import de.ingrid.utils.PlugDescription;

/**
 * Helper Class Obtains the iPlugVersion
 * 
 * 
 * @author joachim@wemove.com
 * 
 */
public class IPlugVersionInspector {

    public static final String VERSION_IDC_1_0_2_DSC_OBJECT = "VERSION_IDC_1_0_2_DSC_OBJECT";

    public static final String VERSION_IDC_1_0_3_DSC_OBJECT = "VERSION_IDC_1_0_3_DSC_OBJECT";

    public static final String VERSION_IDC_1_0_5_DSC_OBJECT = "VERSION_IDC_1_0_5_DSC_OBJECT";

    public static final String VERSION_IDC_1_0_8_DSC_OBJECT = "VERSION_IDC_1_0_8_DSC_OBJECT";

    public static final String VERSION_UDK_5_0_DSC_OBJECT = "VERSION_UDK_5_0_DSC_OBJECT";

    public static final String VERSION_IDC_1_0_2_DSC_ADDRESS = "VERSION_IDC_1_0_2_DSC_ADDRESS";

    public static final String VERSION_IDC_1_0_5_DSC_ADDRESS = "VERSION_IDC_1_0_5_DSC_ADDRESS";

    public static final String VERSION_UDK_5_0_DSC_ADDRESS = "VERSION_UDK_5_0_DSC_ADDRESS";

    public static final String VERSION_IDF_1_0_DSC_OBJECT = "VERSION_IDF_1_0_DSC_OBJECT";

    public static final String VERSION_UNKNOWN = "VERSION_UNKNOWN";

    public static String getIPlugVersion(PlugDescription plugDescription) {

        ArrayList<?> fields = (ArrayList<?>) plugDescription.get(PlugDescription.FIELDS);

        if (hasDataType(plugDescription, "IDF_1.0")) {
            return VERSION_IDF_1_0_DSC_OBJECT;
        } else if (fields != null && fields.contains("object_use.terms_of_use")) {
            return VERSION_IDC_1_0_8_DSC_OBJECT;
        } else if (fields != null && fields.contains("t01_object.data_language_key")
                && fields.contains("t01_object.metadata_language_key") && fields.contains("t02_address.country_key")) {
            return VERSION_IDC_1_0_5_DSC_OBJECT;
        } else if (fields.contains("t01_object.obj_id") && fields.contains("parent.object_node.obj_uuid")
                && fields.contains("object_access.terms_of_use")) {
            return VERSION_IDC_1_0_3_DSC_OBJECT;
        } else if (fields.contains("t01_object.obj_id") && fields.contains("parent.object_node.obj_uuid")) {
            return VERSION_IDC_1_0_2_DSC_OBJECT;
        } else if (fields.contains("t01_object.obj_id") && fields.contains("t01_st_bbox.loc_town_no")) {
            return VERSION_UDK_5_0_DSC_OBJECT;
        } else if (fields != null && fields.contains("t02_address.adr_id")
                && fields.contains("parent.address_node.addr_uuid") && fields.contains("t02_address.country_key")) {
            return VERSION_IDC_1_0_5_DSC_ADDRESS;
        } else if (fields.contains("t02_address.adr_id") && fields.contains("parent.address_node.addr_uuid")) {
            return VERSION_IDC_1_0_2_DSC_ADDRESS;
        } else if (fields.contains("t02_address.adr_id") && fields.contains("t022_adr_adr.adr_from_id")) {
            return VERSION_UDK_5_0_DSC_ADDRESS;
        }

        return VERSION_UNKNOWN;
    }

    public static boolean hasDataType(PlugDescription iPlug, String dataType) {
        String[] dataTypes = iPlug.getDataTypes();
        for (int i = 0; i < dataTypes.length; i++) {
            if (dataTypes[i].equalsIgnoreCase(dataType)) {
                return true;
            }
        }
        return false;
    }

}
