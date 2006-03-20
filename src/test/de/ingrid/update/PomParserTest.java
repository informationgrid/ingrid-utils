/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */

package de.ingrid.update;

import java.io.StringReader;
import java.net.URL;

import junit.framework.TestCase;

import org.xml.sax.InputSource;

import de.ingrid.update.Dependency;
import de.ingrid.update.PomParser;
import de.ingrid.update.Project;

/**
 * Test for {@link de.ingrid.update.PomParser}.
 * 
 * <p/>created on 14.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class PomParserTest extends TestCase {

    private PomParser fParser;

    protected void setUp() throws Exception {
        String testPom = "<project><modelVersion>4.0.0</modelVersion><groupId>group</groupId><artifactId>artefact</artifactId><version>0.0.1</version><description>This is a test pom</description><packaging>jar</packaging><dependencies><dependency><groupId>junit</groupId><artifactId>junit</artifactId><version>3.8.1</version><scope>test</scope><url>http://www.url.net</url></dependency><dependency><groupId>javax.servlet</groupId><artifactId>servlet-api</artifactId><version>2.3</version><scope>provided</scope></dependency></dependencies></project>";
        StringReader reader = new StringReader(testPom);
        this.fParser = new PomParser(new InputSource(reader));
    }

    /**
     * @throws Exception
     */
    public void testProject() throws Exception {
        Project project = this.fParser.getProject();
        assertEquals("group", project.getElement(Project.GROUP_ID));
        assertEquals("artefact", project.getElement(Project.ARTEFACT_ID));
        assertEquals("0.0.1", project.getElement(Project.VERSION));
        assertEquals("This is a test pom", project.getElement(Project.DESCRIPTION));
    }

    /**
     * @throws Exception
     */
    public void testDependencies() throws Exception {
        Dependency[] dependencies = this.fParser.getDependencies();
        assertEquals(2, dependencies.length);

        assertEquals("junit", dependencies[0].getElement(Project.GROUP_ID));
        assertEquals("junit", dependencies[0].getElement(Project.ARTEFACT_ID));
        assertEquals("3.8.1", dependencies[0].getElement(Project.VERSION));
        assertEquals("http://www.url.net", dependencies[0].getElement(Dependency.URL));
        assertEquals("javax.servlet", dependencies[1].getElement(Project.GROUP_ID));
        assertEquals("servlet-api", dependencies[1].getElement(Project.ARTEFACT_ID));
        assertEquals("2.3", dependencies[1].getElement(Project.VERSION));
        assertEquals(null, dependencies[1].getElement(Dependency.URL));
    }

    /**
     * @throws Exception
     */
    public void testExtractJarName() throws Exception {
        Dependency[] dependencies = this.fParser.getDependencies();
        assertEquals("junit-3.8.1.jar", Project.extractResourceName(dependencies[0]));
        assertEquals("servlet-api-2.3.jar", Project.extractResourceName(dependencies[1]));
    }

    /**
     * @throws Exception
     */
    public void testExtractJarDir() throws Exception {
        Dependency[] dependencies = this.fParser.getDependencies();
        assertEquals("http://www.url.net/junit/junit/3.8.1/", Project.extractResourceDirUrl(new URL("http://www.web.net/maven2"),
                dependencies[0]));
        assertEquals("http://www.web.net/maven2/javax.servlet/servlet-api/2.3/", Project.extractResourceDirUrl(new URL(
                "http://www.web.net/maven2"), dependencies[1]));
    }

    /**
     * @throws Exception
     */
    public void testExtractJarPath() throws Exception {
        Dependency[] dependencies = this.fParser.getDependencies();
        assertEquals("http://www.url.net/junit/junit/3.8.1/junit-3.8.1.jar", Project.constructJarUrl(
                new URL("http://www.web.net/maven2"), dependencies[0], Project.extractResourceName(dependencies[0])));
        assertEquals("http://www.web.net/maven2/javax.servlet/servlet-api/2.3/servlet-api-2.3.jar", Project
                .constructJarUrl(new URL("http://www.web.net/maven2"), dependencies[1], Project
                        .extractResourceName(dependencies[1])));
    }
}
