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
     * @return a valid url string with getCapabilities-parameter
     */
    public static String getMissingCapabilitiesParameter(String url) {
        return getMissingCapabilitiesParameter( url, -1 );
    }
    
    /**
     * 
     * @param url is the string to check for getCapabilities-parameter
     * @param type defines the service type
     * @return a valid url string with getCapabilities-parameter
     */
    public static String getMissingCapabilitiesParameter(String url, ServiceType type) {
        return getMissingCapabilitiesParameter( url, type.getId() );
    }
    
    /**
     * Add URL parameters if necessary, so that the capabilities document can be fetched correctly!
     * The string is analyzed for the necessary parameters and if not found, they will be appended.
     * The type is used to identify the service by the id set in the database and is default set to CSW.
     * 
     * @param url is the string to check for getCapabilities-parameter
     * @param type is the numeric identifier used in the database
     *        (1 => CSW, 2 => WMS, 3 => WFS, 4 => WCTS, 6 => WCS)  
     * @return a valid url string with getCapabilities-parameter
     */
    public static String getMissingCapabilitiesParameter(String url, int type) {
        String mappedType = getServiceTypeFromKey(type);
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
     * Determine the URL-Parameter to request a getCapabilities document
     * @param type, is the id of the codelist entry
     * @return the value of the codelist entry key of the service
     */
    private static String getServiceTypeFromKey(int type) {
        String result = "CSW";
        if (type == 1) result = "CSW";
        else if (type == 2) result = "WMS"; 
        else if (type == 3) result = "WFS";
        else if (type == 4) result = "WCTS";
        else if (type == 6) result = "WCS";
        return result;
    }
}
