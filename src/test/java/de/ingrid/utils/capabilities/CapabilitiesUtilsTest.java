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


}
