/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

import java.io.File;
import java.util.ArrayList;

import de.ingrid.utils.query.IngridQuery;

/**
 * PlugDescription contains describing values of an
 * {@link de.ingrid.iplug.IPlug}. Standard values could be stored and retrieved
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

	private static final long serialVersionUID = PlugDescription.class
			.getName().hashCode();

	public static final String CONNECTION = "connection";

	private static final String IS_CRONBASED_INDEXING = "cronbasedIndexing";

	public static final String DATA_TYPE = "dataType";

	public static final String DATA_SOURCE_NAME = "dataSourceName";

	public static final String DATA_SOURCE_DESCRIPTION = "dataSourceDescription";

	public static final String MAPPING = "mapping";

	public static final String ORGANISATION = "organisation";

	public static final String ORGANISATION_ABBR = "organisationAbbr";

	public static final String PERSON_MAIL = "personMail";

	public static final String PERSON_NAME = "personName";

	public static final String PERSON_SURE_NAME = "personSureName";

	public static final String PERSON_PHONE = "personPhone";

	public static final String PERSON_TITLE = "personTitle";

	public static final String PARTNER = "partner";

	public static final String IPLUG_ID = "plugId";

	public static final String WORKING_DIRECTORY = "workingDirectory";

	public static final String FIELDS = "fields";

	public static final String PROXY_SERVICE_URL = "proxyServiceUrl";

	public static final String IPLUG_CLASS = "iPlugClass";

	public static final String MOTHER_IBUS_URL = "motherIbusUrl";

	private static final String BUSES = "busUrls";


	/**
	 * @return Returns the connection.
	 */
	public IDataSourceConnection getConnection() {
		return (IDataSourceConnection) get(CONNECTION);
	}

	/**
	 * @param connection
	 *            The connection to set.
	 */
	public void setConnection(IDataSourceConnection connection) {
		put(CONNECTION, connection);
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
		ArrayList arrayList = getArrayList(DATA_TYPE);
		if (arrayList != null) {
			return (String[]) arrayList.toArray(new String[arrayList.size()]);
		}
		return new String[0];
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
	public String getPartner() {
		return (String) get(PARTNER);
	}

	/**
	 * @param partner
	 *            The partner to set.
	 */
	public void setPartner(String partner) {
		put(PARTNER, partner);
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
	 * @param personSureName
	 *            The personTitle to set.
	 */
	public void setPersonTitle(String phone) {
		put(PERSON_TITLE, phone);
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
	 * @param personSureName
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
		return (String) get(IPLUG_ID);
	}

	/**
	 * @param plugId
	 *            The plugId to set.
	 */
	public void setPlugId(String plugId) {
		put(IPLUG_ID, plugId);
	}

	/**
	 * @return Returns the workinDirectory.
	 */
	public File getWorkinDirectory() {
		String folder = (String) get(WORKING_DIRECTORY);
		return new File(folder);
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
		ArrayList arrayList = getArrayList(FIELDS);
		if (arrayList == null) {
			return new String[0];
		}
		return (String[]) arrayList.toArray(new String[arrayList.size()]);
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
	}

	/**
	 * @return The URL of the proxy service.
	 */
	public String getProxyServiceURL() {
		return (String) get(PROXY_SERVICE_URL);
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
		ArrayList arrayList = getArrayList(BUSES);
		if (arrayList == null) {
			arrayList = new ArrayList();
		}
		return (String[]) arrayList.toArray(new String[arrayList.size()]);
	}

	public void removeBusUrl(String string) {
		ArrayList arrayList = getArrayList(BUSES);
		if (arrayList != null) {
			arrayList.remove(string);
		}
	}

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
	
	
	public String[] getRankingTypes() {
		ArrayList arrayList = getArrayList(IngridQuery.RANKED);
		if(arrayList==null){
			arrayList = new ArrayList();
		}
		return (String[]) arrayList.toArray(new String[arrayList.size()]);
		
	}

}
