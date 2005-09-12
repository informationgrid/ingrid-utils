/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.xml;

import de.ingrid.utils.DescribableValue;

public class DummyBean {

    private DescribableValue fPlugId;

    private DescribableValue fOraganisation;

    private DescribableValue fPersonName;

    private DescribableValue fPersonSureName;

    private DescribableValue fPersoneMail;

    private DescribableValue fConnection;

    private DescribableValue fDataType;

    private DescribableValue fWorkinDirectory;

    private DescribableValue fCronBasedIndexing;

    private DescribableValue fMapping;

    /**
     * @return Returns the connection.
     */
    public DescribableValue getConnection() {
        return this.fConnection;
    }

    /**
     * @param connection
     *            The connection to set.
     */
    public void setConnection(DescribableValue connection) {
        this.fConnection = connection;
    }

    /**
     * @return Returns the cronBasedIndexing.
     */
    public DescribableValue getCronBasedIndexing() {
        return this.fCronBasedIndexing;
    }

    /**
     * @param cronBasedIndexing
     *            The cronBasedIndexing to set.
     */
    public void setCronBasedIndexing(DescribableValue cronBasedIndexing) {
        this.fCronBasedIndexing = cronBasedIndexing;
    }

    /**
     * @return Returns the dataType.
     */
    public DescribableValue getDataType() {
        return this.fDataType;
    }

    /**
     * @param dataType
     *            The dataType to set.
     */
    public void setDataType(DescribableValue dataType) {
        this.fDataType = dataType;
    }

    /**
     * @return Returns the mapping.
     */
    public DescribableValue getMapping() {
        return this.fMapping;
    }

    /**
     * @param mapping
     *            The mapping to set.
     */
    public void setMapping(DescribableValue mapping) {
        this.fMapping = mapping;
    }

    /**
     * @return Returns the oraganisation.
     */
    public DescribableValue getOraganisation() {
        return this.fOraganisation;
    }

    /**
     * @param oraganisation
     *            The oraganisation to set.
     */
    public void setOraganisation(DescribableValue oraganisation) {
        this.fOraganisation = oraganisation;
    }

    /**
     * @return Returns the personeMail.
     */
    public DescribableValue getPersoneMail() {
        return this.fPersoneMail;
    }

    /**
     * @param personeMail
     *            The personeMail to set.
     */
    public void setPersoneMail(DescribableValue personeMail) {
        this.fPersoneMail = personeMail;
    }

    /**
     * @return Returns the personName.
     */
    public DescribableValue getPersonName() {
        return this.fPersonName;
    }

    /**
     * @param personName
     *            The personName to set.
     */
    public void setPersonName(DescribableValue personName) {
        this.fPersonName = personName;
    }

    /**
     * @return Returns the personSureName.
     */
    public DescribableValue getPersonSureName() {
        return this.fPersonSureName;
    }

    /**
     * @param personSureName
     *            The personSureName to set.
     */
    public void setPersonSureName(DescribableValue personSureName) {
        this.fPersonSureName = personSureName;
    }

    /**
     * @return Returns the plugId.
     */
    public DescribableValue getPlugId() {
        return this.fPlugId;
    }

    /**
     * @param plugId
     *            The plugId to set.
     */
    public void setPlugId(DescribableValue plugId) {
        this.fPlugId = plugId;
    }

    /**
     * @return Returns the workinDirectory.
     */
    public DescribableValue getWorkinDirectory() {
        return this.fWorkinDirectory;
    }

    /**
     * @param workinDirectory
     *            The workinDirectory to set.
     */
    public void setWorkinDirectory(DescribableValue workinDirectory) {
        this.fWorkinDirectory = workinDirectory;
    }

}
