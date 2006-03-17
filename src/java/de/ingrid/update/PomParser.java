/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */

package de.ingrid.update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.ingrid.update.Dependency;
import de.ingrid.update.Project;

/**
 * Parses maven2 pom like xmls to extract a {@link de.ingrid.update.Project} and
 * it {@link de.ingrid.update.Dependency}'s.
 * 
 * <p/>created on 14.03.2006
 * 
 * @version $Revision: $
 * @author jz
 * @author $Author: ${lastedit}
 * 
 */
public class PomParser {

    private static String DEPENDENCIES_TAG = "dependencies";

    private Project fProject;

    private List fDependencies = new ArrayList();

    /**
     * Creates an PomParse and already parse the inputSource.
     * 
     * @param inputSource
     *            of a maven like pom xml
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public PomParser(InputSource inputSource) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = parser.parse(inputSource);
        extractProject(doc);
        extractDependencies(doc);
    }

    /**
     * @return the parsed project data
     */
    public Project getProject() {
        return this.fProject;
    }

    /**
     * @return all parsed dependencies
     */
    public Dependency[] getDependencies() {
        return (Dependency[]) this.fDependencies.toArray(new Dependency[this.fDependencies.size()]);
    }

    private void extractProject(Document doc) {
        Element rootElement = doc.getDocumentElement();
        this.fProject = new Project();
        fillProject(this.fProject, rootElement.getChildNodes());
        if (!this.fProject.isValid()) {
            throw new IllegalArgumentException("could not extract project information - pom.xml seems to be invalid");
        }
    }

    private void extractDependencies(Document doc) {
        NodeList dependenciesNodes = doc.getElementsByTagName(DEPENDENCIES_TAG);
        if (dependenciesNodes.getLength() > 1) {
            throw new IllegalStateException("more then one <dependencies> tag is not allowed per pom.xml");
        }
        extractDependencies(dependenciesNodes.item(0));
    }

    private void extractDependencies(Node node) {
        NodeList dependencyNodes = node.getChildNodes();
        int nodeCount = dependencyNodes.getLength();
        for (int i = 0; i < nodeCount; i++) {
            Node dependencyNode = dependencyNodes.item(i);
            Dependency dependency = new Dependency();
            fillProject(dependency, dependencyNode.getChildNodes());
            if (dependency.isValid()) {
                this.fDependencies.add(dependency);
            }
        }
    }

    private static void fillProject(Project dependency, NodeList list) {
        int nodeCount = list.getLength();
        for (int i = 0; i < nodeCount; i++) {
            Node node = list.item(i);
            Node firstChild = node.getFirstChild();
            if (firstChild != null && firstChild.getNodeType() == Node.TEXT_NODE) {
                dependency.setElement(node.getNodeName(), node.getFirstChild().getNodeValue());
            }
        }
    }

}
