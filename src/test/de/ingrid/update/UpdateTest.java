/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */

package de.ingrid.update;

import java.net.UnknownHostException;

import de.ingrid.update.Update;

import junit.framework.TestCase;

/**
 * Test for {@link de.ingrid.update.Update}.
 * 
 * <p/>created on 15.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class UpdateTest extends TestCase {

    private static final boolean ENABLED = false;

    private static final String REPOSITORY = "http://weta-3:8080/maven2/";

    private static final String USER_NAME = "m2";

    private static final String PASSWORD = "m2";

    private static final String UPDATE_XML = "ingrid/iplug.xml";

    private static final String LOCAL_JAR_DIR = "target/jarDir";

    /**
     * @throws Exception
     */
    public void testWrongRepository() throws Exception {
        try {
            Update.main(new String[] { "http://unknown:8080/maven2/", "ingrid/iplug.xml", "target/jarDir", "", "" });
            fail("wrong repository");
        } catch (UnknownHostException e) {
            //
        }
    }
    
    /**
     * @throws Exception
     */
    public void testWrongAuthenthication() throws Exception {
        if (!ENABLED) {
            System.out.println("skipping " + getName());
            return;
        }
        try {
            Update.main(new String[] { REPOSITORY, UPDATE_XML, LOCAL_JAR_DIR, USER_NAME, PASSWORD +"hihi"});
            fail("wrong repository");
        } catch (SecurityException e) {
            //
        }
    }

    /**
     * @throws Exception
     */
    public void testExecute() throws Exception {
        if (!ENABLED) {
            System.out.println("skipping " + getName());
            return;
        }
        Update.main(new String[] { REPOSITORY, UPDATE_XML, LOCAL_JAR_DIR, USER_NAME, PASSWORD });
    }

}
