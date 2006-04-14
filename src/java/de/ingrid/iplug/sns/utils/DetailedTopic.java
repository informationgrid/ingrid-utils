/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */
package de.ingrid.iplug.sns.utils;

import de.ingrid.utils.IngridHit;

/**
 * 
 * created on 21.07.2005 <p>
 *
 * @author hs
 */
public class DetailedTopic extends Topic{
   
    private static final long serialVersionUID = DetailedTopic.class.getName().hashCode();
    /**
     * Constant for administrative identifier.
     */
    public static final String ADMINISTRATIVE_ID = "adminID";
    /**
     * Constant for type.
     */
    public static final String TYPE = "type";
    /**
     * Constant for the upper value of the time range. 
     */
    public static final String TO = "to";
    /**
     * Constant for the lower value of the time range.
     */
    public static final String FROM = "from";
    
    public static final String DESCRIPTION_OCC = "descriptionOcc";

    public  static final String SAMPLE_OCC = "sampleOcc";

    public static final String ASSICIATED_OCC = "associatedTermsOcc";
    public static final String INSTANCE_OF = "href";
    
   
    public DetailedTopic(String plugId, int documentId, String topicId, String title, String summary) {
        super(plugId, documentId, topicId, title, summary);
    }
    
    /**
     * 
     */
    public DetailedTopic(){
        super();
    }
    
    /**
     * Sets the lower limit of the time range.
     * @param from
     */
    public void setFrom(String from){
        put(FROM, from);
    }
    
    /**
     * Allows getter access to the lower limit of the timerange.
     * @return The value of property from.  
     */
    public String getFrom(){
        return (String) get(FROM);
    }
    
    /**
     * Sets the upper limit of the time range.
     * @param to
     */
    public void setTo(String to){
        put(TO, to);
    }
    
    /**
     *  Allows getter access to the upper limit of the timerange.
     * @return The value of property to.
     */
    public String getTo(){
       return (String) get(TO);
    }
    /**
     * Allows setter access to the type property.
     * @param type
     */
    public void setType(String type){
        put(TYPE, type);
    }
    
    /**
     * Allows getter access to the type property.
     * @return The value of property type
     */
    public String getType(){
        return (String) get(TYPE);
    }
    
    /**
     * Allows setter access to the administrativeID property.
     * @param administrativeID
     */
    public void setAdministrativeID(String administrativeID){
        put(ADMINISTRATIVE_ID, administrativeID);
    }
    
    /**
     *  Allows getter access to the administrativeID property.
     * @return The value of property administrativeID.
     */
    public String getAdministrativeID(){
        return (String) get(ADMINISTRATIVE_ID);
    }
}
