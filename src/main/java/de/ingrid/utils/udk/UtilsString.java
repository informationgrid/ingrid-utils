/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
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
package de.ingrid.utils.udk;

/**
 * @author joachim
 *
 */
public class UtilsString {
	
	public static String transformNumberStrToIGCNumber(String val) {
		// x,xxx.xx
		if (val.matches("[-+]?[0-9]*(,[0-9][0-9][0-9])+[0-9]*\\.[0-9]+")) {
			return val.replaceAll(",", "");
		// x.xxx,xx
		} else if (val.matches("[-+]?[0-9]*(\\.[0-9][0-9][0-9])+[0-9]*\\,[0-9]+")){
			return val.replaceAll("\\.", "").replaceAll(",", "\\.");
		// x.xxx.xxx
		} else if (val.matches("(\\d)+(\\.[0][0][0])+")){
			return val.replaceAll("\\.", "");
		// xx,x
		} else if (val.matches("(\\d)+,(\\d)+")){
			return val.replaceAll(",", "\\.");
		} else {
			return val;		
		}
	}
	
    public static String gmlPosListToWktCoordinates(String posList) {
        return gmlPosListToWktCoordinates(posList, null);
    }

    public static String gmlPosListToWktCoordinates(String posList, String type) {
        if (posList == null || posList.isEmpty()) {
            return "";
        }

        posList = posList.replace("/[\r\n]/g", "");
        String coords = "";
        String[] arr = posList.split(" ");
        for(int i=0; i<arr.length; i+=2) {
            if (!coords.isEmpty()) {
                coords += ", ";
            }
            coords += arr[i] + " " + arr[i+1];
        }
        if(type != null) {
            return type + " (" + coords + ")";
        } else {
            return "(" + coords + ")";
        }
    }
}
