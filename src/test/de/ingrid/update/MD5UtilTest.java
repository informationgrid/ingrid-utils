/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */


package de.ingrid.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.ingrid.update.MD5Util;

import junit.framework.TestCase;

/**
 * Test for {@link de.ingrid.update.MD5Util}.
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
