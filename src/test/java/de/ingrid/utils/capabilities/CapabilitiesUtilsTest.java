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
package de.ingrid.utils.capabilities;

import junit.framework.TestCase;

public class CapabilitiesUtilsTest extends TestCase {

    private String urlWithoutParams;
    private String urlWithRequestParam;
    private String urlWithServiceParam;
    private String urlWithAllParams;

    protected void setUp() throws Exception {
        super.setUp();
        
        urlWithoutParams = "http://www.test.com/no-Params";
        urlWithRequestParam = "http://www.test.com/some-Params?REQUEST=getCapabilities";
        urlWithServiceParam = "http://www.test.com/some-Params?SERVICE=WMS";
        urlWithAllParams = "http://www.test.com/all-Params?REQUEST=getCapabilities&SERVICE=WFS";
    }

    public void testGetAdditionalCapabilitiesParameterString() {
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=CSW",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams ));
        assertEquals( "&SERVICE=CSW",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam ));
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam ));
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams ));
    }

    public void testGetAdditionalCapabilitiesParameterStringServiceType() {
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=CSW", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.CSW ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WMS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WMS ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WCS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WCS ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WCTS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WCTS ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WFS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WFS ));

        assertEquals( "&SERVICE=CSW",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, CapabilitiesUtils.ServiceType.CSW ));
        assertEquals( "&SERVICE=WMS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, CapabilitiesUtils.ServiceType.WMS ));
        assertEquals( "&SERVICE=WCS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, CapabilitiesUtils.ServiceType.WCS ));
        assertEquals( "&SERVICE=WCTS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, CapabilitiesUtils.ServiceType.WCTS ));
        assertEquals( "&SERVICE=WFS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, CapabilitiesUtils.ServiceType.WFS ));
        
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, CapabilitiesUtils.ServiceType.CSW ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, CapabilitiesUtils.ServiceType.WMS ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, CapabilitiesUtils.ServiceType.WCS ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, CapabilitiesUtils.ServiceType.WCTS ));
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam,  CapabilitiesUtils.ServiceType.WFS ));  
        
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, CapabilitiesUtils.ServiceType.CSW ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, CapabilitiesUtils.ServiceType.WMS ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, CapabilitiesUtils.ServiceType.WCS ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, CapabilitiesUtils.ServiceType.WCTS ));   
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, CapabilitiesUtils.ServiceType.WFS ));   
    }

    public void testGetAdditionalCapabilitiesParameterStringInt() {
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=CSW", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 1 ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WMS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 2 ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WCS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 6 ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WCTS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 4 ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WFS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 3 ));

        assertEquals( "&SERVICE=CSW",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, 1 ));
        assertEquals( "&SERVICE=WMS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, 2 ));
        assertEquals( "&SERVICE=WCS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, 6 ));
        assertEquals( "&SERVICE=WCTS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, 4 ));
        assertEquals( "&SERVICE=WFS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, 3 ));
        
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, 1 ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, 2 ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, 6 ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, 4 ));
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, 3 ));  
        
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, 1 ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, 2 ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, 6 ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, 4 ));   
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, 3 ));
    }

    public void testGetAdditionalCapabilitiesParameterStringString() {
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WMS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, "view" ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=WFS", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, "download" ));
        assertEquals( "?REQUEST=GetCapabilities&SERVICE=CSW", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, "discovery" ));

        assertEquals( "&SERVICE=WMS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, "view" ));
        assertEquals( "&SERVICE=WFS",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, "download" ));
        assertEquals( "&SERVICE=CSW",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam, "discovery" ));
        
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, "view" ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, "download" ));  
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam, "discovery" ));  
        
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, "view" ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, "download" ));    
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams, "discovery" ));    
    }

    
}
