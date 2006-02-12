/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils;

public class IngridHitDetail extends IngridHit {

    /**
     * 
     */
    private static final long serialVersionUID = 4L;

    private static final String SUMMARY = "abstract";

    private static final String TITLE = "title";

    public IngridHitDetail() {
        // for serializing...
    }

    /**
     * @param hit
     * @param title
     * @param summary
     */
    public IngridHitDetail(IngridHit hit, String title, String summary) {
        super(hit.getPlugId(), hit.getDocumentId(), hit.getDataSourceId(), hit.getScore());
        put(TITLE, title);
        put(SUMMARY, summary);
    }

    /**
     * @return a title
     */
    public String getTitle() {
        return (String) get(TITLE);
    }

    /**
     * @return a summarizing abstract
     */
    public String getSummary() {
        return (String) get(SUMMARY);
    }

    public void setOrganisation(String organisation) {
        put(PlugDescription.ORGANISATION, organisation);
    }

    public String getOrganisation() {
        return (String) get(PlugDescription.ORGANISATION);
    }

    public void setDataSourceName(String datasourceName) {
        put(PlugDescription.DATA_SOURCE_NAME, datasourceName);
    }

    public String getDataSourceName() {
        return (String) get(PlugDescription.DATA_SOURCE_NAME);
    }

    public void setIplugClassName(String iplugClassName) {
        put(PlugDescription.IPLUG_CLASS, iplugClassName);
    }

    public String getIplugClassName() {
        return (String) get(PlugDescription.IPLUG_CLASS);
    }

    
}
