/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: DispatcherTest.java,v $
 */
package de.ingrid.iplug.sns.utils;

/**
 * 
 * created on 21.07.2005 <p>
 *
 * @author hs
 */
public class DocumentMetaData extends Topic{
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
    
    /**
     * @param topicID
     * @param topicName
     */
    public DocumentMetaData(String topicID, String topicName) {
        super(topicID, topicName);
    }
    
    /**
     * 
     */
    public DocumentMetaData(){
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
