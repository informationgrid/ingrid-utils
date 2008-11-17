/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.ingrid.utils.query.IngridQuery;

/**
 * PlugDescription contains describing values of an
 * {@link de.ingrid.utils.IPlug}. Standard values could be stored and retrieved
 * using the PlugDescription methods. Additional values could be stored and
 * retrieved using the put and get methods derived from
 * {@link java.util.HashMap}.
 * 
 * <p/>created on 19.09.2005
 * 
 * @version $Revision: $
 * @author sg
 * @author $Author jz ${lastedit}
 * 
 */
public class PlugDescription extends IngridDocument {

    private static final long serialVersionUID = PlugDescription.class.getName().hashCode();

    /***/
    public static final String CONNECTION = "connection";

    /***/
    private static final String IS_CRONBASED_INDEXING = "cronbasedIndexing";

    /***/
    public static final String DATA_TYPE = "dataType";

    /***/
    public static final String DATA_SOURCE_NAME = "dataSourceName";

    /***/
    public static final String DATA_SOURCE_DESCRIPTION = "dataSourceDescription";

    /***/
    public static final String MAPPING = "mapping";

    /***/
    public static final String ORGANISATION = "organisation";

    /***/
    public static final String ORGANISATION_ABBR = "organisationAbbr";

    /***/
    public static final String PERSON_MAIL = "personMail";

    /***/
    public static final String PERSON_NAME = "personName";

    /***/
    public static final String PERSON_SURE_NAME = "personSureName";

    /***/
    public static final String PERSON_PHONE = "personPhone";

    /***/
    public static final String PERSON_TITLE = "personTitle";

    /***/
    public static final String PARTNER = "partner";

    /***/
    public static final String IPLUG_ID = "plugId";

    /***/
    public static final String WORKING_DIRECTORY = "workingDirectory";

    /***/
    public static final String FIELDS = "fields";

    /***/
    public static final String PROXY_SERVICE_URL = "proxyServiceUrl";

    /***/
    public static final String IPLUG_CLASS = "iPlugClass";

    /***/
    public static final String MOTHER_IBUS_URL = "motherIbusUrl";

    /***/
    public static final String IS_RECORD_LOADER = "isRecordLoader";

    /***/
    public static final String BUSES = "busUrls";

    /***/
    public static final String PROVIDER = "provider";

    /***/
    public static final String MD5_HASH = "md5Hash";

    /***/
    private boolean fIsActivated;

    private static final String IPLUG_ADMIN_GUI_URL = "IPLUG_ADMIN_GUI_URL";

    private static final String IPLUG_ADMIN_PASSWORD = "IPLUG_ADMIN_PASSWORD";

    private static final Object IPLUG_ADMIN_GUI_PORT = "IPLUG_ADMIN_GUI_PORT";

	public static final String CORRESPONDENT_PROXY_SERVICE_URL = "CORRESPONDENT_PROXY_SERVICE_URL";

    /**
     * @return Returns first connection or null
     */
    public IDataSourceConnection getConnection() {
        IDataSourceConnection[] connections = getConnections();
        if (connections.length > 0) {
            return connections[0];
        }
        return null;
    }

    /**
     * @param connection
     *            The connection to set.
     */
    public void setConnection(IDataSourceConnection connection) {
        ArrayList arrayList = getArrayList(CONNECTION);
        if (arrayList == null) {
            addToList(CONNECTION, connection);
            arrayList = getArrayList(CONNECTION);
        }
        arrayList.set(0, connection);
    }

    /**
     * @return Returns the connections.
     */
    public IDataSourceConnection[] getConnections() {
        ArrayList arrayList = getArrayList(CONNECTION);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        return (IDataSourceConnection[]) arrayList.toArray(new IDataSourceConnection[arrayList.size()]);
    }

    /**
     * @param connection
     *            connection to add
     */
    public void addConnection(IDataSourceConnection connection) {
        addToList(CONNECTION, connection);
    }

    /**
     * remove connection...
     * 
     * @param connection
     */
    public void removeConnection(IDataSourceConnection connection) {
        ArrayList arrayList = getArrayList(CONNECTION);
        if (arrayList != null) {
            arrayList.remove(connection);
        }
    }

    /**
     * @return Returns the cronBasedIndexing.
     */
    public boolean isCronBasedIndexing() {
        return getBoolean(IS_CRONBASED_INDEXING);
    }

    /**
     * @param cronBasedIndexing
     *            The cronBasedIndexing to set.
     */
    public void setCronBasedIndexing(boolean cronBasedIndexing) {
        putBoolean(IS_CRONBASED_INDEXING, cronBasedIndexing);
    }

    /**
     * @return Returns list of supported dataTypes.
     * 
     */
    public String[] getDataTypes() {
        return getArrayListAsArray(DATA_TYPE);
    }

    /**
     * @param dataType
     *            The dataType to set.
     */
    public void addDataType(String dataType) {
        addToList(DATA_TYPE, dataType);
    }

    /**
     * sets the data source name
     * 
     * @param dataSourceName
     */
    public void setDataSourceName(String dataSourceName) {
        put(DATA_SOURCE_NAME, dataSourceName);

    }

    /**
     * @return the data source description
     * 
     */
    public String getDataSourceDescription() {
        return (String) get(DATA_SOURCE_DESCRIPTION);

    }

    /**
     * sets the data source description
     * 
     * @param dataSourceDescription
     */
    public void setDataSourceDescription(String dataSourceDescription) {
        put(DATA_SOURCE_DESCRIPTION, dataSourceDescription);

    }

    /**
     * @return the data source name
     * 
     */
    public String getDataSourceName() {
        return (String) get(DATA_SOURCE_NAME);

    }

    /**
     * @return Returns the mapping.
     */
    public IDataMapping getMapping() {
        return (IDataMapping) get(MAPPING);
    }

    /**
     * @param mapping
     *            The mapping to set.
     */
    public void setMapping(IDataMapping mapping) {
        put(MAPPING, mapping);
    }

    /**
     * @return Returns the organisation.
     */
    public String getOrganisation() {
        return (String) get(ORGANISATION);
    }

    /**
     * @param organisation
     *            The organisation to set.
     */
    public void setOrganisation(String organisation) {
        put(ORGANISATION, organisation);
    }

    /**
     * @return Returns the partner.
     */
    public String[] getPartners() {
        return getArrayListAsArray(PARTNER);
    }

    /**
     * @param partner
     *            The partner to set.
     */
    public void addPartner(String partner) {
        addToList(PARTNER, partner);
    }

    /**
     * @return Returns the organisation abbreviation.
     */
    public String getOrganisationAbbr() {
        return (String) get(ORGANISATION_ABBR);
    }

    /**
     * @param organisationAbbr
     *            The organisation to set.
     */
    public void setOrganisationAbbr(String organisationAbbr) {
        put(ORGANISATION_ABBR, organisationAbbr);
    }

    /**
     * @return Returns the personTitle
     */
    public String getPersonTitle() {
        return (String) get(PERSON_TITLE);
    }

    /**
     * @param personTitle
     *            The personTitle to set.
     */
    public void setPersonTitle(String personTitle) {
        put(PERSON_TITLE, personTitle);
    }

    /**
     * @return Returns the personeMail.
     */
    public String getPersonMail() {
        return (String) get(PERSON_MAIL);
    }

    /**
     * @param personMail
     *            The personMail to set.
     */
    public void setPersonMail(String personMail) {
        put(PERSON_MAIL, personMail);
    }

    /**
     * @return Returns the personName.
     */
    public String getPersonName() {
        return (String) get(PERSON_NAME);
    }

    /**
     * @param personName
     *            The personName to set.
     */
    public void setPersonName(String personName) {
        put(PERSON_NAME, personName);
    }

    /**
     * @return Returns the personPhone
     */
    public String getPersonPhone() {
        return (String) get(PERSON_PHONE);
    }

    /**
     * @param phone
     *            The personPhone to set.
     */
    public void setPersonPhone(String phone) {
        put(PERSON_PHONE, phone);
    }

    /**
     * @return Returns the personSureName.
     */
    public String getPersonSureName() {
        return (String) get(PERSON_SURE_NAME);
    }

    /**
     * @param personSureName
     *            The personSureName to set.
     */
    public void setPersonSureName(String personSureName) {
        put(PERSON_SURE_NAME, personSureName);
    }

    /**
     * @return Returns the plugId.
     */
    public String getPlugId() {
        return (String) get(PROXY_SERVICE_URL);
    }

    /**
     * @param plugId
     *            The plugId to set.
     * @deprecated
     */
    public void setPlugId(String plugId) {
        System.out.println("setting of plugId " + plugId + " ignored, cause deprecated");
        // use proxy url
        // put(IPLUG_ID, plugId);
    }

    /**
     * @return Returns the workinDirectory.
     */
    public File getWorkinDirectory() {
        String folder = (String) get(WORKING_DIRECTORY);
        if (folder != null) {
            return new File(folder);
        }
        return null;
    }

    /**
     * @param workinDirectory
     *            The workinDirectory to set.
     */
    public void setWorkinDirectory(File workinDirectory) {
        put(WORKING_DIRECTORY, workinDirectory.getAbsolutePath());
    }

    /**
     * @return the fields of this plug
     */
    public String[] getFields() {
        return getArrayListAsArray(FIELDS);
    }

    /**
     * Adds a fieldName to the plug description
     * 
     * @param fieldName
     */
    public void addField(String fieldName) {
        addToList(FIELDS, fieldName);
    }

    /**
     * @param proxyServiceUrl
     *            The URL of the proxy service.
     */
    public void setProxyServiceURL(String proxyServiceUrl) {
        put(PROXY_SERVICE_URL, proxyServiceUrl);
        if (getMd5Hash() == null) {
            setMd5Hash(proxyServiceUrl);
        }
    }

    /**
     * @return The URL of the proxy service.
     */
    public String getProxyServiceURL() {
        return (String) get(PROXY_SERVICE_URL);
    }
    
    /**
	 * @param correspondentIPlug
	 *            The URL of the correspondent iPlug.
	 */
	public void setCorrespondentProxyServiceURL(
			String correspondentProxyServiceUrl) {
		put(CORRESPONDENT_PROXY_SERVICE_URL, correspondentProxyServiceUrl);
	}

	/**
	 * @return The URL of the correspondent iPlug.
	 */
	public String getCorrespondentProxyServiceURL() {
		return (String) get(CORRESPONDENT_PROXY_SERVICE_URL);
	}
    
    /**
     * @param motherIbusUrl
     *            The URL of the mother Ibus.
     */
    public void setMotherIBusURL(String motherIbusUrl) {
        put(MOTHER_IBUS_URL, motherIbusUrl);
    }

    /**
     * @return The URL of the proxy service.
     */
    public String getMotherIBusURL() {
        return (String) get(MOTHER_IBUS_URL);
    }

    /**
     * @param iplugAdminGuiUrl
     */
    public void setIplugAdminGuiUrl(String iplugAdminGuiUrl) {
        put(IPLUG_ADMIN_GUI_URL, iplugAdminGuiUrl);
    }

    /**
     * @return The admin url for the IPlug.
     */
    public String getIplugAdminGuiUrl() {
        return (String) get(IPLUG_ADMIN_GUI_URL);
    }

    /**
     * @param iPlugClass
     *            The class of the used IPlug.
     */
    public void setIPlugClass(String iPlugClass) {
        put(IPLUG_CLASS, iPlugClass);
    }

    /**
     * @return The class of the used IPlug.
     */
    public String getIPlugClass() {
        return (String) get(IPLUG_CLASS);
    }

    /**
     * adds bus url
     * 
     * @param url
     */
    public void addBusUrl(String url) {
        addToList(BUSES, url);
    }

    /**
     * @return a list of bus urls as string array
     */
    public String[] getBusUrls() {
        return getArrayListAsArray(BUSES);
    }

    /**
     * @param string
     */
    public void removeBusUrl(String string) {
        removeFromList(BUSES, string);
    }

    /**
     * @param score
     * @param date
     * @param notRanked
     */
    public void setRankinTypes(boolean score, boolean date, boolean notRanked) {
        if (score) {
            addToList(IngridQuery.RANKED, IngridQuery.SCORE_RANKED);
        }
        if (date) {
            addToList(IngridQuery.RANKED, IngridQuery.DATE_RANKED);
        }
        if (notRanked) {
            addToList(IngridQuery.RANKED, IngridQuery.NOT_RANKED);
        }
    }

    /**
     * @return all ranking types
     */
    public String[] getRankingTypes() {
        return getArrayListAsArray(IngridQuery.RANKED);

    }

    /**
     * activates a iplug, Attention this state is not serialized
     */
    public void activate() {
        this.fIsActivated = true;
    }

    /**
     * deactivate a iplug
     * 
     * @see PlugDescription#activate()
     */
    public void deActivate() {
        this.fIsActivated = false;
    }

    /**
     * @param activate
     */
    public void setActivate(boolean activate) {
        this.fIsActivated = activate;
    }

    /**
     * @return true if the plug is activated
     * @see PlugDescription#activate()
     */
    public boolean isActivate() {
        return this.fIsActivated;
    }

    /**
     * @return true if the plug implements {@link IRecordLoader}.
     */
    public boolean isRecordloader() {
        return getBoolean(IS_RECORD_LOADER);
    }

    /**
     * @param isRecordLoader
     */
    public void setRecordLoader(boolean isRecordLoader) {
        putBoolean(IS_RECORD_LOADER, isRecordLoader);
    }

    private String[] getArrayListAsArray(String key) {
        List list = getArrayList(key);
        if (list == null) {
            list = new ArrayList();
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * add a provider
     * 
     * @param provider
     */
    public void addProvider(String provider) {
        addToList(PROVIDER, provider);
    }

    /**
     * @return all provider
     */
    public String[] getProviders() {
        ArrayList arrayList = getArrayList(PROVIDER);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /**
     * @return md5 of pligDescription file anf plug id
     */
    public String getMd5Hash() {
        return (String) get(MD5_HASH);
    }

    /**
     * @param md5Hash
     */
    public void setMd5Hash(String md5Hash) {
        put(MD5_HASH, md5Hash);
    }

    /**
     * @return The password for the admin gui user.
     */
    public String getIplugAdminPassword() {
        return (String) get(IPLUG_ADMIN_PASSWORD);
    }

    /**
     * @param password
     */
    public void setIplugAdminPassword(String password) {
        put(IPLUG_ADMIN_PASSWORD, password);
    }

    /**
     * @return The port where the admin gui runs.
     */
    public int getIplugAdminGuiPort() {
        return getInt(IPLUG_ADMIN_GUI_PORT);
    }
    
    /**
     * @param port
     */
    public void setIplugAdminGuiPort(int port) {
        putInt(IPLUG_ADMIN_GUI_PORT, port);
    }
}
