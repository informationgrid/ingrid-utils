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
package de.ingrid.utils.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author joachim@wemove.com
 * 
 */
public class GZipTool {

    private static final Log LOG = LogFactory.getLog(GZipTool.class);

    /**
     * Compress a String using GZIP compression. Returns a BASE64 encoded string
     * representation of the GZIP byte stream.
     * 
     * @param str
     * @return
     */
    public static String gzip(String str) {

        String result = null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BufferedOutputStream bufos = null;
        try {
            bufos = new BufferedOutputStream(new GZIPOutputStream(bos));
            bufos.write(str.getBytes());
            bufos.close();
            result = new String(Base64.encodeBase64String(bos.toByteArray()));
            bos.close();
        } catch (IOException e) {
            LOG.error("Unable to GZIP String. Returning original string.", e);
        }
        return result;
    }

    /**
     * Decompress a String that has been compressed with GZIP and BASE64
     * encoded. {@see GZipTool.gzip}. The uncompressed string is returned.
     * 
     * 
     * @param str
     * @return
     */
    public static String ungzip(String str) {

        String result = null;

        ByteArrayOutputStream bos;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64
                    .decodeBase64(str));
            BufferedInputStream bufis = new BufferedInputStream(
                    new GZIPInputStream(bis));
            bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = bufis.read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
            result = bos.toString();
            bis.close();
            bufis.close();
            bos.close();
        } catch (Exception e) {
            LOG.error("Unable to UNGZIP String. Returning the original string", e);
            return str;
        }
        return result;

    }

}
