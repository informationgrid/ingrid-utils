/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2016 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils;

public class IngridHitDetail extends IngridHit {

    public final static String DETAIL_TIMING = "DETAIL_TIMING";
    
    
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
        super();
        setPlugId( hit.getPlugId() );
        setDocumentId( hit.getDocumentId() );
        setDataSourceId(hit.getDataSourceId());
        setScore(hit.getScore());
        
        put(TITLE, title);
        put(SUMMARY, summary);
    }

    @Deprecated
    public IngridHitDetail(String plugId, int documentId, int dataSourceId, float score, String title, String summary) {
        super(plugId, documentId,dataSourceId, score);
        put(TITLE, title);
        put(SUMMARY, summary);
    }
    
    public IngridHitDetail(String plugId, String documentId, int dataSourceId, float score, String title, String summary) {
        super(plugId, documentId,dataSourceId, score);
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
