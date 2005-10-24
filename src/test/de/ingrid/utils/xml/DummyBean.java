/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.xml;

import de.ingrid.utils.config.Property;


public class DummyBean {

    private Property fPlugId;

    private Property fOraganisation;

    private Property fPersonName;

    private Property fPersonSureName;

    private Property fPersoneMail;

    private Property fConnection;

    private Property fDataType;

    private Property fWorkinDirectory;

    private Property fCronBasedIndexing;

    private Property fMapping;

    /**
     * @return Returns the connection.
     */
    public Property getConnection() {
        return this.fConnection;
    }

    /**
     * @param connection
     *            The connection to set.
     */
    public void setConnection(Property connection) {
        this.fConnection = connection;
    }

    /**
     * @return Returns the cronBasedIndexing.
     */
    public Property getCronBasedIndexing() {
        return this.fCronBasedIndexing;
    }

    /**
     * @param cronBasedIndexing
     *            The cronBasedIndexing to set.
     */
    public void setCronBasedIndexing(Property cronBasedIndexing) {
        this.fCronBasedIndexing = cronBasedIndexing;
    }

    /**
     * @return Returns the dataType.
     */
    public Property getDataType() {
        return this.fDataType;
    }

    /**
     * @param dataType
     *            The dataType to set.
     */
    public void setDataType(Property dataType) {
        this.fDataType = dataType;
    }

    /**
     * @return Returns the mapping.
     */
    public Property getMapping() {
        return this.fMapping;
    }

    /**
     * @param mapping
     *            The mapping to set.
     */
    public void setMapping(Property mapping) {
        this.fMapping = mapping;
    }

    /**
     * @return Returns the oraganisation.
     */
    public Property getOraganisation() {
        return this.fOraganisation;
    }

    /**
     * @param oraganisation
     *            The oraganisation to set.
     */
    public void setOraganisation(Property oraganisation) {
        this.fOraganisation = oraganisation;
    }

    /**
     * @return Returns the personeMail.
     */
    public Property getPersoneMail() {
        return this.fPersoneMail;
    }

    /**
     * @param personeMail
     *            The personeMail to set.
     */
    public void setPersoneMail(Property personeMail) {
        this.fPersoneMail = personeMail;
    }

    /**
     * @return Returns the personName.
     */
    public Property getPersonName() {
        return this.fPersonName;
    }

    /**
     * @param personName
     *            The personName to set.
     */
    public void setPersonName(Property personName) {
        this.fPersonName = personName;
    }

    /**
     * @return Returns the personSureName.
     */
    public Property getPersonSureName() {
        return this.fPersonSureName;
    }

    /**
     * @param personSureName
     *            The personSureName to set.
     */
    public void setPersonSureName(Property personSureName) {
        this.fPersonSureName = personSureName;
    }

    /**
     * @return Returns the plugId.
     */
    public Property getPlugId() {
        return this.fPlugId;
    }

    /**
     * @param plugId
     *            The plugId to set.
     */
    public void setPlugId(Property plugId) {
        this.fPlugId = plugId;
    }

    /**
     * @return Returns the workinDirectory.
     */
    public Property getWorkinDirectory() {
        return this.fWorkinDirectory;
    }

    /**
     * @param workinDirectory
     *            The workinDirectory to set.
     */
    public void setWorkinDirectory(Property workinDirectory) {
        this.fWorkinDirectory = workinDirectory;
    }

}
