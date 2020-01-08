/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2020 wemove digital solutions GmbH
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
/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */


package de.ingrid.utils.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

/**
 * Test for {@link MD5Util}.
 * 
 * <p/>created on 14.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class MD5UtilTest extends TestCase {

    /**
     * @throws IOException
     */
    public void testGetMD5() throws IOException {
        File file1 = new File("target/file1");
        File file2 = new File("target/file2");
        file1.delete();
        file2.delete();
        file1.getParentFile().mkdirs();
        file1.createNewFile();
        file2.createNewFile();
        assertEquals(MD5Util.getMD5(file1), MD5Util.getMD5(file2));

        FileOutputStream outputStream1 = new FileOutputStream(file1);
        FileOutputStream outputStream2 = new FileOutputStream(file2);
        outputStream1.write(1);
        assertNotSame(MD5Util.getMD5(file1), MD5Util.getMD5(file2));
        outputStream2.write(1);
        assertEquals(MD5Util.getMD5(file1), MD5Util.getMD5(file2));
        outputStream1.write(2);
        assertNotSame(MD5Util.getMD5(file1), MD5Util.getMD5(file2));
        outputStream2.write(3);
        assertNotSame(MD5Util.getMD5(file1), MD5Util.getMD5(file2));
        outputStream1.close();
        outputStream2.close();
        assertTrue(file1.delete());
        assertTrue(file2.delete());
    }

}
