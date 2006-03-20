/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */

package de.ingrid.update;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import de.ingrid.update.Dependency;

/**
 * Provides the meta-data of a project like groupid, artefactid...
 * 
 * <p/>created on 14.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class Project {

    /***/
    public static String GROUP_ID = "groupId";

    /***/
    public static String ARTEFACT_ID = "artifactId";

    /***/
    public static String VERSION = "version";

    /***/
    public static String DESCRIPTION = "description";

    /***/
    public static String TYPE = "type";

    private Map fDependencyElement = new HashMap(4);

    /**
     * @param name
     * @param value
     */
    public void setElement(String name, String value) {
        this.fDependencyElement.put(name, value);
    }

    /**
     * @param name
     * @return the dependency element with the given name
     */
    public String getElement(String name) {
        return (String) this.fDependencyElement.get(name);
    }

    /**
     * @param name
     * @param defaultElement
     * @return the dependency element with the given name or if not existent the
     *         given default value
     */
    public String getElement(String name, String defaultElement) {
        String element = (String) this.fDependencyElement.get(name);
        if (element == null) {
            element = defaultElement;
        }
        return element;
    }

    /**
     * @return true if all needed elemnts are contained
     */
    public boolean isValid() {
        if (!this.fDependencyElement.containsKey(GROUP_ID) || !this.fDependencyElement.containsKey(ARTEFACT_ID)) {
            return false;
        }
        return true;
    }

    /**
     * @param project
     * @return the name of the jar
     */
    public static String extractResourceName(Project project) {
        StringBuffer buffer = new StringBuffer(project.getElement(Project.ARTEFACT_ID));
        String versio = project.getElement(Project.VERSION);
        if (versio != null) {
            buffer.append('-');
            buffer.append(versio);
        }
        buffer.append(".");
        buffer.append(project.getElement(Project.TYPE, "jar"));
        return buffer.toString();
    }

    /**
     * @param repositoryPath
     * @param project
     * @return the repository directory of the jar as url
     */
    public static String extractResourceDirUrl(URL repositoryPath, Project project) {
        String repoPathAsString = repositoryPath.toString();
        if (project.getElement(Dependency.URL) != null) {
            repoPathAsString = project.getElement(Dependency.URL);
        }
        StringBuffer buffer = new StringBuffer(repoPathAsString);
        if (!repoPathAsString.endsWith("/")) {
            buffer.append('/');
        }
        buffer.append(project.getElement(Project.GROUP_ID));
        buffer.append('/');
        buffer.append(project.getElement(Project.ARTEFACT_ID));

        String version = project.getElement(Project.VERSION);
        if (version != null) {
            buffer.append('/');
            buffer.append(version);
        }
        buffer.append('/');
        return buffer.toString();
    }

    /**
     * @param repositoryPath
     * @param project
     * @param jarName
     * @return the url path to the jar of the dependency
     */
    public static String constructJarUrl(URL repositoryPath, Project project, String jarName) {
        String dir = extractResourceDirUrl(repositoryPath, project);
        StringBuffer buffer = new StringBuffer(dir);
        if (!dir.endsWith("/")) {
            buffer.append('/');
        }
        buffer.append(jarName);
        return buffer.toString();
    }

}
