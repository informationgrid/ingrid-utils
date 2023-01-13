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
package de.ingrid.utils.tool;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.net.URISyntaxException;

public class UrlToolTest {

    @Test
    void getIdnUrlWithEncodedPath() throws MalformedURLException, URISyntaxException {

        assertEquals("http://www.domain.de/F%C3%B6n/Frisur?mu%C3%9F=ja&preis=gro%C3%9F#Au%C3%9Fz%C3%BCge", UrlTool.getEncodedUnicodeUrl("http://www.domain.de/Fön/Frisur?muß=ja&preis=groß#Außzüge"));

        assertEquals("http://www.xn--schnheit-p4a.de/", UrlTool.getEncodedUnicodeUrl("http://www.schönheit.de/"));

        assertEquals("http://www.xn--sigkeiten-g1a23a.de/F%C3%B6n/Frisur?mu%C3%9F=ja&preis=gro%C3%9F#Au%C3%9Fz%C3%BCge", UrlTool.getEncodedUnicodeUrl("http://www.süßigkeiten.de/Fön/Frisur?muß=ja&preis=groß#Außzüge"));

        assertEquals("http://www.geilenkirchen.de/stadtplanung/bauleitplanung/rechtskraeftige-bauleitpl%C3%A4ne/", UrlTool.getEncodedUnicodeUrl("http://www.geilenkirchen.de/stadtplanung/bauleitplanung/rechtskraeftige-bauleitpl%C3%A4ne/"));

        assertEquals("http://www.geilenkirchen.de/stadtplanung/bauleitplanung/rechtskraeftige-bauleitpl%C3%A4ne/", UrlTool.getEncodedUnicodeUrl("http://www.geilenkirchen.de/stadtplanung/bauleitplanung/rechtskraeftige-bauleitpläne/"));

        assertEquals("http://www.xn--schnheit-p4a.de:8800/", UrlTool.getEncodedUnicodeUrl("http://www.schönheit.de:8800/"));

        assertEquals("https://www.xn--schnheit-p4a.de/", UrlTool.getEncodedUnicodeUrl("https://www.schönheit.de/"));

        assertEquals("http://www.xn--sigkeiten-g1a23a.de/F%C3%B6n/Frisur?mu%C3%9F=ja&preis=gro%C3%9F#Au%C3%9Fz%C3%BCge", UrlTool.getEncodedUnicodeUrl("http://www.xn--sigkeiten-g1a23a.de/F%C3%B6n/Frisur?mu%C3%9F=ja&preis=gro%C3%9F#Au%C3%9Fz%C3%BCge"));

        // mixing of encoded/decoded URL parts are not supported
        assertNotEquals("http://www.xn--sigkeiten-g1a23a.de/F%C3%B6n/Frisur?mu%C3%9F=ja&preis=gro%C3%9F#Au%C3%9Fz%C3%BCge", UrlTool.getEncodedUnicodeUrl("http://www.xn--sigkeiten-g1a23a.de/F%C3%B6n/Frisur?mu%C3%9F=ja&preis=gro%C3%9F#Au%C3%9Fzüge"));

    }

    @Test
    void getDecodedIdnUrl() throws MalformedURLException, URISyntaxException {

        assertEquals("http://www.süßigkeiten.de/Fön/Frisur?muß=ja&preis=groß#Außzüge", UrlTool.getDecodedUnicodeUrl("http://www.xn--sigkeiten-g1a23a.de/F%C3%B6n/Frisur?mu%C3%9F=ja&preis=gro%C3%9F#Au%C3%9Fz%C3%BCge"));

        assertEquals("http://www.schönheit.de:8800/", UrlTool.getDecodedUnicodeUrl("http://www.xn--schnheit-p4a.de:8800/"));

        assertEquals("https://www.schönheit.de/", UrlTool.getDecodedUnicodeUrl("https://www.xn--schnheit-p4a.de/"));

        assertEquals("https://www.schönheit.de:80/", UrlTool.getDecodedUnicodeUrl("https://www.xn--schnheit-p4a.de:80/"));

        assertEquals("http://www.süßigkeiten.de/Fön/Frisur?muß=ja&preis=groß#Außzüge", UrlTool.getDecodedUnicodeUrl("http://www.süßigkeiten.de/Fön/Frisur?muß=ja&preis=groß#Außzüge"));

        assertEquals("http://www.süßigkeiten.de/Fön/Frisur?muß=ja&preis=groß#Außzüge", UrlTool.getDecodedUnicodeUrl("http://www.xn--sigkeiten-g1a23a.de/Fön/Frisur?muß=ja&preis=groß#Außz%C3%BCge"));

        assertEquals("http://www.süßigkeiten.de/Fön/Frisur?muß=ja&preis=groß#Außzüge", UrlTool.getDecodedUnicodeUrl("http://www.süßigkeiten.de/F%C3%B6n/Frisur?muß=ja&preis=groß#Außzüge"));
    }

}
