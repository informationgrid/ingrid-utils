/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils;


public class IngridHitG2k extends IngridHit {

    /***/
    private static final long serialVersionUID = 1166835275;
    
    /* Mapping for "Parameter der Suchanfrage" */
    private static final String TITLE = "title";
    
    /***/
    private static final String SUMMARY = "abstract";

    /***/
    private static final String URI = "url";
    
    /***/
    private static final String META_HITS = "no_of_hits";
    
    
    public IngridHitG2k() {
        // for serializing...
    }
    
    
    /**
     * @param plugId
     * @param documentId
     * @param dataSourceId
     * @param score
     * @param noOfHits (just for g2k-MetaResult)
     */
    public IngridHitG2k(String plugId, String title, String summary, String uri, String noOfHits) {
        super(plugId, 0, 0, (float) 1.0);
        put(SUMMARY, summary);
        put(TITLE, title);
        put(URI, uri);
        put(META_HITS, noOfHits);      
    }
     
    /**
     * @param plugId
     * @param documentId
     * @param dataSourceId
     * @param score
     */
    public IngridHitG2k(String plugId, String title, String summary, String uri) {
        super(plugId, 0, 0, (float) 1.0);
        put(SUMMARY, summary);
        put(TITLE, title);
        put(URI, uri);
        put(META_HITS, new String("1"));
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
    
    /**
     * @return a URI to content site 
     */
    public String getUri() {
        return (String) get(URI);
    }
    
    /**
     * This is only used for a g2k-MetaResult (external hit site)
     * @return Hit count for MetaResult 
     */
    public String getNoOfHits() {
        return (String) get(META_HITS);
    }    
    
}


