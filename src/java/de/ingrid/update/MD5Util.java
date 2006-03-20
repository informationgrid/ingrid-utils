/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */

package de.ingrid.update;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Util for handling MD5 checksums.
 * 
 * <p/>created on 15.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class MD5Util {

    /**
     * @param file
     * @return the md5 digest of the given file
     * @throws IOException
     */
    public static String getMD5(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        return getMD5(inputStream);
    }
    
    /**
     * @param inputStream
     * @return the md5 digest of the given stream resource
     * @throws IOException
     */
    public static String getMD5(InputStream inputStream) throws IOException {
        MessageDigest md = createMD5MessageDigest();
        int length = -1;
        byte[] bytes = new byte[4096];
        while ((length = inputStream.read(bytes)) != -1) {
            md.update(bytes, 0, length);
        }
        inputStream.close();
        return encode(md.digest());
    }

    /**
     * @param md5Digest 
     * @param localFile
     * @return true if the content of the md5-file athe the given url euqals the
     *         md5 checksum of the given file
     * @throws IOException
     */
    public static boolean isEqual(String md5Digest, File localFile) throws IOException {
        return new String(md5Digest).equals(getMD5(localFile));
    }

    private static MessageDigest createMD5MessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This piece of code is taken from
     * org/apache/maven/wagon/observers/ChecksumObserver.java of
     * wagon-provider-api project for compatibility own calculated and of maven
     * checksums.
     * 
     * see
     * http://svn.apache.org/viewcvs.cgi/maven/wagon/trunk/wagon-provider-api/src/main/java/org/apache/maven/wagon/observers/ChecksumObserver.java?rev=312821&view=markup
     * 
     * @param binaryData
     * @return the encoded checksum
     */
    protected static String encode(byte[] binaryData) {
        if (binaryData.length != 16 && binaryData.length != 20) {
            int bitLength = binaryData.length * 8;
            throw new IllegalArgumentException("Unrecognised length for binary data: " + bitLength + " bits");
        }
        String retValue = "";
        for (int i = 0; i < binaryData.length; i++) {
            String t = Integer.toHexString(binaryData[i] & 0xff);
            if (t.length() == 1) {
                retValue += ("0" + t);
            } else {
                retValue += t;
            }
        }

        return retValue.trim();
    }

    /**
     * @param inputStream 
     * @return the md5 digest from a md5 file at the given url
     * @throws IOException
     */
    public static String readMD5File(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4096];
        int length = inputStream.read(bytes);
        byte[] digest = new byte[length];
        System.arraycopy(bytes, 0, digest, 0, length);
        return new String(digest);
    }
}
