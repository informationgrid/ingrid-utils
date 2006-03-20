/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */

package de.ingrid.update;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public void execute(URL repositoryUrl, String updateXmlPath) throws SAXException, IOException,
            ParserConfigurationException {
        LOG.info("update started");
        URL updateXml = getUpdateXmlUrl(repositoryUrl, updateXmlPath);
        LOG.info("retrieving update informations from '" + updateXml + "'");

        PomParser pomParser = new PomParser(new InputSource(getAuthenticatedStream(updateXml)));
        Dependency[] dependencies = pomParser.getDependencies();
        LOG.info("found " + dependencies.length + " dependencies");
        downloadMissingDependencies(repositoryUrl, dependencies);
        LOG.info("update finished");
    }

    private void downloadMissingDependencies(URL repoUrl, Dependency[] dependencies) throws IOException {
        for (int i = 0; i < dependencies.length; i++) {
            String resourceName = Project.extractResourceName(dependencies[i]);
            LOG.info("checking '" + resourceName + "'");
            String resourceUrl = Project.constructJarUrl(repoUrl, dependencies[i], resourceName);
            File localResource = getLocalResourcePath(dependencies[i], resourceName);
            if (!localResource.exists()) {
                LOG.info("no local copy");
                downloadResource(resourceUrl, localResource);
            }
            String md5Url = resourceUrl + ".md5";
            String md5;
            try {
                InputStream md5FileStream = getAuthenticatedStream(new URL(md5Url));
                md5 = MD5Util.readMD5File(md5FileStream);
            } catch (FileNotFoundException e) {
                LOG.info("cannot find md5 file, calculatting own md5");
                md5 = MD5Util.getMD5(getAuthenticatedStream(new URL(resourceUrl)));
            }
            if (!MD5Util.isEqual(md5, localResource)) {
                LOG.info("new version found");
                localResource.delete();
                downloadResource(resourceUrl, localResource);
            }
        }
    }

    private File getLocalResourcePath(Dependency dependency, String resourceName) {
        String target = dependency.getElement(Dependency.TARGET);
        File localResource;
        if (target == null) {
            localResource = new File(resourceName);
            LOG.warn("no target entry found for '" + resourceName + "' - copying resource to '"
                    + localResource.getAbsolutePath() + "'");
        } else {
            localResource = new File(target, resourceName);
        }
        return localResource;
    }

    private void downloadResource(String dependencyJarUrl, File localResource) throws IOException {
        LOG.info("start download of '" + dependencyJarUrl + "'");
        URL url = new URL(dependencyJarUrl);
        InputStream inputStream = getAuthenticatedStream(url);
        if (localResource.getParentFile() != null) {
            localResource.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(localResource);
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
        LOG.info("downloaded '" + localResource.getName() + "' to " + localResource.getAbsoluteFile());
    }

    /**
     * @param args
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        if (args.length < 4) {
            printUsage();
        }
        Update update = new Update(args[2], args[3]);
        update.execute(new URL(args[0]), args[1]);
    }

    private InputStream getAuthenticatedStream(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String authentication = (new sun.misc.BASE64Encoder()).encode((this.fUserName + ":" + this.fPassword)
                .getBytes());
        connection.addRequestProperty("Authorization", "Basic " + authentication);
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

    private static void printUsage() {
        System.err.println("Usage: <repository> <path of update.xml> <user> <password>");
        System.err
                .println("Example: http://localhost:8080/maven2/ ingrid/iplugA/update.xml /home/ingrid/jars mike ekim");
        System.exit(0);
    }
}
