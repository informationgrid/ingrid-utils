/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils.capabilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapabilitiesUtilsTest {

    private String urlWithoutParams;
    private String urlWithRequestParam;
    private String urlWithServiceParam;
    private String urlWithAllParams;

    @BeforeEach
    public void setUp() throws Exception {

        urlWithoutParams = "http://www.test.com/no-Params";
        urlWithRequestParam = "http://www.test.com/some-Params?REQUEST=getCapabilities";
        urlWithServiceParam = "http://www.test.com/some-Params?SERVICE=WMS";
        urlWithAllParams = "http://www.test.com/all-Params?REQUEST=getCapabilities&SERVICE=WFS";
    }

    @Test
    public void testGetAdditionalCapabilitiesParameterString() {
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams ));
        assertEquals( "&SERVICE=CSW",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithRequestParam ));
        assertEquals( "&REQUEST=GetCapabilities",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithServiceParam ));
        assertEquals( "",  CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithAllParams ));
    }

    @Test
    public void testGetAdditionalCapabilitiesParameterStringServiceType() {
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.CSW ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WMS ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WCS ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WCTS ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, CapabilitiesUtils.ServiceType.WFS ));

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

    @Test
    public void testGetAdditionalCapabilitiesParameterStringInt() {
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 1 ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 2 ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 6 ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 4 ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, 3 ));

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

    @Test
    public void testGetAdditionalCapabilitiesParameterStringString() {
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, "view" ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, "download" ));
        assertEquals( "", CapabilitiesUtils.getMissingCapabilitiesParameter( urlWithoutParams, "discovery" ));

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

    @Test
    public void testExtractServiceFromServiceTypeVersion() {

        // WMS
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WMS 1.1.1" ));
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WMS 1.1.1" ));
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WMS 1.3.0" ));
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WMS 1.3.0" ));
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WMS 1.1.1, OGC WMS 1.3.0" ));
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WMS 1.1.1 OGC WMS 1.3.0" ));
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "WMS 1.1.1" ));
        assertEquals( "WMS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "WMS 1.1.1, WMS 1.3.0" ));
        // CSW
        assertEquals( "CSW", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:CSW 2.0.2" ));
        assertEquals( "CSW", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC CSW 2.0.2" ));
        assertEquals( "CSW", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:CSW 2.0.2, OGC:CSW 2.0.0" ));
        assertEquals( "CSW", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC CSW 2.0.2 OGC CSW 2.0.0" ));
        assertEquals( "CSW", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "CSW 2.0.2" ));
        assertEquals( "CSW", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "CSW 2.0.2, CSW 2.0.0" ));
       // WCS
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WCS 1.1.0" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WCS 1.1.0" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WCS 1.1.1" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WCS 1.1.1" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WCS 1.1.2" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WCS 1.1.2" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WCS 2.0.1" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WCS 2.0.1" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WCS 1.1.0, OGC:WCS 1.1.1, OGC:WCS 2.0.1" ));
        assertEquals( "WCS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WCS 1.1.0 OGC WCS 1.1.1 OGC WCS 2.0.1" ));
        // WFS
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WFS 2.0" ));
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WFS 2.0" ));
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WFS" ));
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WFS 2.0, OGC:WFS 2.1" ));
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WFS 2.0 OGC WFS 2.1" ));
        // WMTS
        assertEquals( "WMTS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC:WMTS 1.0.0" ));
        assertEquals( "WMTS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WMTS 1.0.0" ));

        assertEquals( null, CapabilitiesUtils.extractServiceFromServiceTypeVersion( "1.1.1" ));
        assertEquals( null, CapabilitiesUtils.extractServiceFromServiceTypeVersion( "1.1.1, 1.3.0" ));
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WFS" ));
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WFS OGC WFS" ));
        assertEquals( "WFS", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC WFS, OGC WFS" ));

        assertEquals( "API Feature", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "API Feature" ));
        assertEquals( "Vector Tiles", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "Vector Tiles" ));
        assertEquals( "OGC-API Feature", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC-API Feature" ));
        assertEquals( "OGC-API Feature", CapabilitiesUtils.extractServiceFromServiceTypeVersion( "OGC-API Feature, API" ));
    }
}
