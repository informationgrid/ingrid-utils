/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2019 wemove digital solutions GmbH
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
package de.ingrid.utils.capabilities;

public class CapabilitiesUtils {
    
    public static enum ServiceType {
        CSW(1), WMS(2), WFS(3), WCTS(4), WCS(6);
        
        int id;        
        
        ServiceType(int i) { id = i; }
        public int getId() { return id; }
    }

    /**
     * Add URL parameters if necessary, so that the capabilities document can be fetched correctly!
     * The string is analyzed for the necessary parameters and if not found, they will be appended.
     * The service type will be automatically set to CSW.
     * 
     * @param url is the string to check for getCapabilities-parameter
     * @return a valid url string with getCapabilities-parameter.<br>
     * 		   NOTICE: default is SERVICE=CSW if service type cannot be determined !
     */
    public static String getMissingCapabilitiesParameter(String url) {
        return getMissingCapabilitiesParameter( url, -1);
    }
    
    /**
     * Add URL parameters if necessary, so that the capabilities document can be fetched correctly!
     * The string is analyzed for the necessary parameters and if not found, they will be appended.
     * The type is used to identify the service.
     * 
     * @param url is the string to check for getCapabilities-parameter
     * @param type defines the service type
     * @return a valid url string with getCapabilities-parameter.
     */
    public static String getMissingCapabilitiesParameter(String url, ServiceType type) {
        return getMissingCapabilitiesParameter( url, type.getId());
    }
    
    /**
     * Add URL parameters if necessary, so that the capabilities document can be fetched correctly!
     * The string is analyzed for the necessary parameters and if not found, they will be appended.
     * The serviceType is used to identify the service by a valid string used in IDF documents or from CSW !
     * NOTICE: If serviceType is not ISO konform (for identification) we use the string itself as SERVICE parameter !
     * 
     * @param url is the string to check for getCapabilities-parameter
     * @param serviceType srv:serviceType from IDF/CSW (INSPIRE conform is view, download, discovery ...)
     * @return a valid url string with getCapabilities-parameter.
     */
    public static String getMissingCapabilitiesParameter(String url, String serviceType) {
    	int myType = getServiceTypeFromISO(serviceType);
    	// Also deliver the serviceType passed. To use it if ISO type could not be determined (-1). 
        return getMissingCapabilitiesParameter(url, myType, serviceType);
    }
    
    /**
     * Add URL parameters if necessary, so that the capabilities document can be fetched correctly!
     * The string is analyzed for the necessary parameters and if not found, they will be appended.
     * The type is used to identify the service by the id set in the database and is default set to CSW.
     * 
     * @param url is the string to check for getCapabilities-parameter
     * @param serviceTypeId numeric type identifier used in the database (entry id of codelist 5100).<br>
     * @return a valid url string with getCapabilities-parameter.
     */
    public static String getMissingCapabilitiesParameter(String url, int serviceTypeId) {
    	return getMissingCapabilitiesParameter(url, serviceTypeId, null);
    }

    /**
     * Add URL parameters if necessary, so that the capabilities document can be fetched correctly!
     * The string is analyzed for the necessary parameters and if not found, they will be appended.
     * The type is used to identify the service by the id set in the database.
     * 
     * @param url is the string to check for getCapabilities-parameter
     * @param serviceTypeId numeric type identifier used in the database (entry id of codelist 5100).<br>
     * 		  Pass -1 if not known !  
     * @param serviceTypeNoISO service type to be used if it could not be determined from passed serviceTypeId !
     * 		  E.g. this is the type set in CSW if not ISO konform ! 
     * @return a valid url string with getCapabilities-parameter<br>
     * 		   NOTICE: default is SERVICE=CSW if service type cannot be determined !
     */
    private static String getMissingCapabilitiesParameter(String url, int serviceTypeId, String serviceTypeNoISO) {
        String mappedType = getServiceTypeFromKey(serviceTypeId);
    	if (mappedType == null) {
            mappedType = serviceTypeNoISO;
    	}
    	if (mappedType == null) {
    		// DEFAULT IS CSW !!!
            mappedType = "CSW";
    	}
        String parameters = "";
        
        if (url.toLowerCase().indexOf("request=getcapabilities") == -1) {
            if (url.indexOf("?") == -1) {
                parameters = "?";
            }
            // if url or parameters already contains a ? or & at the end then do not add another one!
            if (!(url.lastIndexOf("?") == url.length() - 1 || parameters.length() > 0)
                    && !(url.lastIndexOf("&") == url.length() - 1)) {
                parameters = "&";
            }
            
            parameters += "REQUEST=GetCapabilities";
        }
        if (url.toLowerCase().indexOf("service=") == -1) {
            parameters += "&SERVICE=" + mappedType;
        }
        return parameters;
    }

    /**
     * Determine the URL-Parameter to request a getCapabilities document.
     * @param type is the id of the codelist entry (entry id of codelist 5100).
     * @return the value for SERVICE parameter or null if not found !
     */
    private static String getServiceTypeFromKey(int type) {
        String result = null;
        if (type == 1) result = "CSW";
        else if (type == 2) result = "WMS"; 
        else if (type == 3) result = "WFS";
        else if (type == 4) result = "WCTS";
        else if (type == 6) result = "WCS";
        return result;
    }
    
    /** Returns ID of codelist entry from serviceType delivered in CSW.
     * We check against the INSPIRE values (see document "INSPIRE Metadata
     * Implementing Rules" -> "Spatial data service type").
     * @param serviceType the srv:serviceType in CSW data
     * @return the id of the codelist entry, -1 if not found
     */
    private static int getServiceTypeFromISO(String serviceType){
        int service = -1;

    	if (serviceType != null) {
    		String myType = serviceType.trim().toLowerCase();

            if (myType.equals("view")) {
                service = 2;
            } else if (myType.equals("download")) {
                service = 3;
            } else if (myType.equals("discovery")) {
                service = 1;
            } else if (myType.equals("transformation")) {
                service = 4;
            } else if (myType.equals("invoke")) {
                service = 5;
            } else if (myType.equals("other")) {
                service = 6;
            }
    	}
        
        return service;
    }
}
