/*----------------------------------------------------------------------------*
 *          @@@@@      @@@       @@@@@                                        *
 *      @@@@@@@@@@@    @@@@    @@@@@@@@        @                              *
 *     @@@@@@@@@@@@    @@@@   @@@@@@@@@     @@@@                              *
 *    @@@@@            @@@@  @@@@           @@@@                              *
 *   @@@@@             @@@@  @@@@@        @@@@@@@@@   @@@@@@@@      @@@@@@@   *
 *   @@@@    @@@@@@@   @@@@   @@@@@@@     @@@@@@@@@  @@@@@@@@@@   @@@@@@@@@   *
 *   @@@@   @@@@@@@@   @@@@    @@@@@@@@     @@@@    @@@@    @@@   @@@@        *
 *   @@@@    @@@@@@@   @@@@      @@@@@@@    @@@@    @@@@@@@@@@@@ @@@@         *
 *   @@@@@      @@@@   @@@@         @@@@    @@@@    @@@@@@@@@@@@ @@@@         *
 *    @@@@@     @@@@   @@@@   @     @@@@    @@@@    @@@@      @   @@@@        *
 *     @@@@@@@@@@@@@   @@@@   @@@@@@@@@@    @@@@@@@  @@@@@@@@@@   @@@@@@@@@   *
 *       @@@@@@@@@@@   @@@@   @@@@@@@@       @@@@@@   @@@@@@@@@     @@@@@@@   *
 *                       Ihr Partner für GIS - Technologie                    *
 *                                                                            *
 * Rundeturmstraße 12                                                         *
 * D-64283 Darmstadt                                                          *
 * info@gistec-online.de                          http://www.gistec-online.de *
 *----------------------------------------------------------------------------*
 *                                                                            *
 * Copyright © 2005/2006 GIStec GmbH                                          *
 * ALL Rights Reserved.                                                       *
 *                                                                            *
 *----------------------------------------------------------------------------*
 *                                                                            *
 * Autor            : Nico Steffen Beck                                       *
 * Erstellungsdatum : 03.01.2006                                              *
 * Version          : 1.0                                                     *
 * Beschreibung     : InGrid Utils, IngridHitG2k                              *
 *                                                                            *
 *                                                                            *
 *----------------------------------------------------------------------------*
 * Änderungen (Datum, Version, Autor, Beschreibung)                           *
 *----------------------------------------------------------------------------*
 *            |         |         |                                           *
 *            |         |         |                                           *
 *            |         |         |                                           *
 *            |         |         |                                           *
 *----------------------------------------------------------------------------*
*/


package de.ingrid.utils;

import de.ingrid.utils.IngridHit;

public class IngridHitG2k extends IngridHit {

    /***/
    private static final long serialVersionUID = 1166835275;
    
    /***/
    private static final String TITLE = "title";
    
    /***/
    private static final String SUMMARY = "abstract";

    /***/
    private static final String URI = "uri";
    
    
    public IngridHitG2k() {
     // for serializing...
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
    
}


