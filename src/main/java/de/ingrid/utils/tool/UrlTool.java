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
/**
 * 
 */
package de.ingrid.utils.tool;

import com.ibm.icu.text.IDNA;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author joachim@wemove.com
 * 
 */
public class UrlTool {

    private static final Log LOG = LogFactory.getLog(UrlTool.class);

    /**
     * @return Returns the URL with
     *    <ul>
     *      <li>the urls domain is IDN encoded (using IDNA 2008, nontransitional processing)</li>
     *      <li>the path, query and anchor is US-ASCII encoded</li>
     *    </ul>
     *
     *    <p>If the url was already encoded, return the URL as is.</p>
     *    <br>
     *    <p><strong>CAUTION: Mixing encoded and decoded url parts is not supported!</strong></p>
     *
     *
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public static String getEncodedUnicodeUrl(String urlStr) throws MalformedURLException, URISyntaxException {

        // return if ascii only url
        // this also returns for already encoded urls
        if (Charset.forName("US-ASCII").newEncoder().canEncode(urlStr)) {
            return urlStr;
        }

        URL u = new URL(urlStr);

        final IDNA.Info idnaInfo = new IDNA.Info();
        final StringBuilder idnaOutput = new StringBuilder();
        IDNA.getUTS46Instance(IDNA.NONTRANSITIONAL_TO_ASCII).nameToASCII(u.getHost(), idnaOutput, idnaInfo);

        URI uri = new URI(
                u.getProtocol(),
                null,
                idnaOutput.toString(),
                u.getPort(),
                u.getPath(),
                u.getQuery(),
                u.getRef());

        return uri.toASCIIString();
    }


    /**
     * @return Returns the URL with
     *    <ul>
     *      <li>the urls domain is IDN decoded (using IDNA 2008, nontransitional processing)</li>
     *      <li>the path, query and anchor is URI decoded</li>
     *    </ul>
     *
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public static String getDecodedUnicodeUrl(String urlStr) throws MalformedURLException, URISyntaxException {

        URI uri = new URI(urlStr);

        final IDNA.Info idnaInfo = new IDNA.Info();
        final StringBuilder idnaOutput = new StringBuilder();

        try {
            IDNA.getUTS46Instance(IDNA.NONTRANSITIONAL_TO_UNICODE).nameToUnicode(uri.getAuthority(), idnaOutput, idnaInfo);
        } catch (Exception e) {
            // use original host name as the hostname cannot be encoded
            idnaOutput.append(uri.getAuthority());
        }

        String s = uri.getScheme() + "://"
                + (uri.getUserInfo() != null ? uri.getUserInfo() : "")
                + idnaOutput.toString()
                + uri.getPath()
                + (uri.getQuery() != null ? "?" + uri.getQuery() : "")
                + (uri.getFragment() != null ? "#" + uri.getFragment() : "");

        return s;
    }


}
