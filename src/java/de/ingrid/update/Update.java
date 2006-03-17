/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */

package de.ingrid.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.ingrid.update.Dependency;
import de.ingrid.update.MD5Util;
import de.ingrid.update.PomParser;
import de.ingrid.update.Project;

/**
 * Tool for updating a local directory of jars from a maven2 repository with
 * jars specified at
 * 
 * <p/>created on 15.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class Update {

    private static Log LOG = LogFactory.getLog(Update.class);

    private String fUserName;

    private String fPassword;

    /**
     * @param username
     * @param password
     */
    public Update(String username, String password) {
        this.fUserName = username;
        this.fPassword = password;
    }

    /**
     * @param repositoryUrl
     * @param updateXmlPath
     * @param jarDir
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public void execute(URL repositoryUrl, String updateXmlPath, File jarDir) throws SAXException, IOException,
            ParserConfigurationException {
        LOG.info("update started");
        jarDir.mkdirs();
        URL updateXml = getUpdateXmlUrl(repositoryUrl, updateXmlPath);
        LOG.info("local jar directory: '" + jarDir.getAbsolutePath() + "'");
        LOG.info("retrieving update informations from '" + updateXml + "'");

        PomParser pomParser = new PomParser(new InputSource(getAuthenticatedStream(updateXml)));
        Dependency[] dependencies = pomParser.getDependencies();
        LOG.info("found " + dependencies.length + " dependencies");
        downloadMissingDependencies(jarDir, repositoryUrl, dependencies);
        LOG.info("update finished");
    }

    private void downloadMissingDependencies(File jarDir, URL repoUrl, Dependency[] dependencies) throws IOException {
        List localJarNames = getLocalJarsNames(jarDir);
        for (int i = 0; i < dependencies.length; i++) {
            String jarName = Project.extractJarName(dependencies[i]);
            LOG.info("checking '" + jarName + "'");
            String dependencyJarUrl = Project.constructJarUrl(repoUrl, dependencies[i], jarName);
            String md5Url = dependencyJarUrl + ".md5";
            File localJar = new File(jarDir, jarName);
            if (!localJarNames.contains(jarName)) {
                LOG.info("no local copy");
                downloadJar(dependencyJarUrl, localJar);
            }
            if (!MD5Util.isEqual(getAuthenticatedStream(new URL(md5Url)), new File(jarDir, jarName))) {
                LOG.info("new version found");
                localJar.delete();
                downloadJar(dependencyJarUrl, localJar);
            }
        }
    }

    private void downloadJar(String dependencyJarUrl, File localJar) throws IOException {
        LOG.info("start download of '" + dependencyJarUrl + "'");
        URL url = new URL(dependencyJarUrl);
        InputStream inputStream = getAuthenticatedStream(url);
        FileOutputStream outputStream = new FileOutputStream(localJar);
        int length = -1;
        byte[] bytes = new byte[4096];
        int readBytes = 0;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
            readBytes += length;
            System.out.print(".");
        }
        System.out.println(" downloaded (" + (readBytes / 1024) + " kB)");
        outputStream.close();
        LOG.info("finished download of '" + localJar.getName() + "'");
    }

    /**
     * @param args
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        if (args.length < 5) {
            printUsage();
        }
        Update update = new Update(args[3], args[4]);
        update.execute(new URL(args[0]), args[1], new File(args[2]));
    }

    private InputStream getAuthenticatedStream(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String authentication = (new sun.misc.BASE64Encoder()).encode((this.fUserName + ":" + this.fPassword)
                .getBytes());
        connection.addRequestProperty("Authorization", "Basic " + authentication);
        // TODO catch 401 exc
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            if (!(e instanceof UnknownHostException) && connection.getResponseCode() == 401) {
                throw new SecurityException("authentication failed - check username & password");
            }
            throw e;
        }
        return inputStream;
    }

    private static URL getUpdateXmlUrl(URL repositoryUrl, String updateXmlPath) throws MalformedURLException {
        return new URL(repositoryUrl.getProtocol(), repositoryUrl.getHost(), repositoryUrl.getPort(), repositoryUrl
                .getPath()
                + "/" + updateXmlPath);
    }

    private static List getLocalJarsNames(File jarDir) {
        String[] jarNames = jarDir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.endsWith(".jar")) {
                    return true;
                }
                return false;
            }
        });
        List localJarList = new ArrayList();
        if (jarNames != null) {
            localJarList.addAll(Arrays.asList(jarNames));
        }
        return localJarList;
    }

    private static void printUsage() {
        System.err.println("Usage: <repository> <path of update.xml> <jarDir> <user> <password>");
        System.err
                .println("Example: http://localhost:8080/maven2/ ingrid/iplugA/update.xml /home/ingrid/jars mike ekim");
        System.exit(0);
    }
}
