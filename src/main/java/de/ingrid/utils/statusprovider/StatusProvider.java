/*
 * **************************************************-
 * ingrid-iplug-se-iplug
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
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
package de.ingrid.utils.statusprovider;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Manages status messages. Messages added are sorted chronological.
 * 
 * @author joachim@wemove.com
 * 
 */
public class StatusProvider {

    final protected static Log LOG = LogFactory.getLog( StatusProvider.class );
    
    /**
     * The name of the system property that defines the last status file
     */
    protected static final String LAST_STATUS_FILE_NAME_PROPERTY = "lastStatus";

    /**
     * The name of the system property that defines the ingrid home directory
     */
    protected static final String INGRID_HOME = "ingrid_home";

    /**
     * The default name of the status file
     */
    private static final String LAST_STATUS_XML = "last_status.xml";
    
    public static enum Classification {
        INFO(1), WARN(2), ERROR(3);

        Integer level;

        Classification(Integer level) {
            this.level = level;
        }
    }

    private File lastStatusFile = null;
    
//    public StatusProvider() {
//        this( INGRID_HOME, LAST_STATUS_FILE_NAME_PROPERTY );
//    }
    
    public StatusProvider() {

        // check if the config property is set and load the appropriate
        // file if yes
        String lastStatusFilename = System.getProperty(LAST_STATUS_FILE_NAME_PROPERTY);

        if (lastStatusFilename == null) {
            // check if the ingrid home path is set and derive config file
            String ingridHome = System.getProperty(INGRID_HOME);
            if (ingridHome != null) {
                File f = new File(ingridHome, LAST_STATUS_XML);
                lastStatusFilename = f.getAbsolutePath();
            }
        }

        this.lastStatusFile = new File(lastStatusFilename != null ? lastStatusFilename : LAST_STATUS_XML);
        try {
            this.load();
        } catch (IOException e) {
            LOG.error("Error loading last status file.", e);
        }
    }

    public StatusProvider(String logDir) {
        this( logDir, LAST_STATUS_XML );
    }

    public StatusProvider(String logDir, String statusFilename) {

        if (this.lastStatusFile == null) {

            this.lastStatusFile = new File( logDir, statusFilename );
            try {
                this.load();
            } catch (IOException e) {
                LOG.error( "Error loading last status file.", e );
            }
        }
    }

    public static class State {

        public String key;

        protected String value;

        protected Date time;

        protected Classification classification;

        protected Map<String, String> properties;

        State(String key, String value, Date time, Classification classification) {
            this.key = key;
            this.value = value;
            this.time = time;
            this.classification = classification;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public Classification getClassification() {
            return classification;
        }

        public void setClassification(Classification classification) {
            this.classification = classification;
        }

        public void setProperty(String key, String value) {
            if (properties == null) {
                properties = new HashMap<String, String>();
            }
            properties.put( key, value );
        }

        public String getProperty(String key) {
            if (properties != null) {
                return properties.get( key );
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    private LinkedHashMap<String, State> states = new LinkedHashMap<String, State>();

    private String msgFormat = "%1$tF %1$tT - [%2$s] %3$s\n";

    /**
     * Add a message to the message list. If the key already exists, the message
     * is updated.
     * 
     * @param key
     * @param value
     * @param classification
     */
    public void addState(String key, String value, Classification classification) {
        if (states.containsKey( key )) {
            states.get( key ).value = value;
            states.get( key ).classification = classification;
        } else {
            synchronized (this) {
                states.put( key, new State( key, value, new Date(), classification ) );
            }
        }
        try {
            write();
        } catch (IOException e) {
            LOG.error( "Error writing last status.", e );
        }
    }

    /**
     * Returns the current message for the given state key.
     * 
     * @param key
     * @return
     */
    public String getStateMsg(String key) {
        if (states.containsKey( key )) {
            return states.get( key ).value;
        } else {
            return null;
        }
    }

    /**
     * Appends a String to a state. Does nothing if the state does not exist.
     * 
     * @param key
     * @param value
     */
    public void appendToState(String key, String value) {
        if (states.containsKey( key )) {
            states.get( key ).value = states.get( key ).value.concat( value );
            try {
                write();
            } catch (IOException e) {
                LOG.error( "Error writing last status.", e );
            }
        }
    }

    /**
     * Add a message to the message list. If the key already exists, the message
     * is updated. THis message is tagged as INFO message.
     * 
     * @param key
     * @param value
     */
    public void addState(String key, String value) {
        this.addState( key, value, Classification.INFO );
    }

    /**
     * Adds a property to a state. Creates a NULL state if necessary.
     * 
     * @param state
     * @param key
     * @param value
     */
    public void setStateProperty(String state, String key, String value) {
        if (!states.containsKey( state )) {
            synchronized (this) {
                states.put( state, new State( state, null, new Date(), Classification.INFO ) );
            }
        }
        states.get( state ).setProperty( key, value );
    }

    /**
     * Returns a property of a state. Returns NULL if it does not exist.
     * 
     * @param state
     * @param key
     * @return
     */
    public String getStateProperty(String state, String key) {
        if (states.containsKey( state )) {
            return states.get( state ).getProperty( key );
        }
        return null;
    }

    /**
     * Clear the message list.
     * 
     */
    public void clear() {
        synchronized (this) {
            states.clear();
        }
    }

    public Classification getMaxClassificationLevel() {
        Classification result = Classification.INFO;
        for (State state : states.values()) {
            if (state.classification.level > result.level) {
                result = state.classification;
            }
        }
        return result;
    }

    /**
     * Write the status to the disc. To keep the time for modifying the actual
     * status file as short as possible, the method writes the file into a
     * temporary file first and then renames this file to the original status
     * file name. Note: Since renaming a file is not atomic in Windows, if the
     * target file exists already (we need to delete and then rename), this
     * method is synchronized.
     * 
     * @throws IOException
     */
    public synchronized void write() throws IOException {
        if (this.lastStatusFile == null) {
            LOG.warn( "Log file could not be written, because it was not defined!" );
            return;
        }

        // serialize the Configuration instance to xml
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        String xml = xstream.toXML( states );

        // write the configuration to a temporary file first
        File tmpFile = File.createTempFile( "config", null );
        BufferedWriter output = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( tmpFile.getAbsolutePath() ), "UTF8" ) );
        try {
            output.write( xml );
            output.close();
            output = null;
        } finally {
            if (output != null) {
                output.close();
            }
        }

        // move the temporary file to the configuration file
        this.lastStatusFile.delete();
        Files.move(Paths.get(tmpFile.toURI()), Paths.get(this.lastStatusFile.toURI()));
    }

    /**
     * Load the last status file from disk. Creates an empty status list if the
     * file does not exist or is empty.
     */
    @SuppressWarnings("unchecked")
    public synchronized void load() throws IOException {
        if (this.lastStatusFile == null) {
            LOG.warn( "Log file could not be read, because it was not defined!" );
            return;
        }

        // create empty configuration, if not existing yet
        if (!this.lastStatusFile.exists()) {
            LOG.warn( "Status file " + this.lastStatusFile + " does not exist." );
            if (this.lastStatusFile.getParentFile() != null && !this.lastStatusFile.getParentFile().exists() && !this.lastStatusFile.getParentFile().mkdirs()) {
                LOG.error( "Unable to create directories for '" + this.lastStatusFile.getParentFile() + "'" );
            }
            LOG.info( "Creating configuration file " + this.lastStatusFile );
            this.lastStatusFile.createNewFile();
        }

        BufferedReader input = null;
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug( "Read status file: " + this.lastStatusFile );
            }
            // read the configuration file content
            StringBuilder content = new StringBuilder();
            input = new BufferedReader( new InputStreamReader( new FileInputStream( this.lastStatusFile ), "UTF-8" ) );
            String line = null;
            while ((line = input.readLine()) != null) {
                content.append( line );
                content.append( System.getProperty( "line.separator" ) );
            }
            input.close();
            input = null;

            if (content.length() == 0) {
                LOG.warn( "Last status file " + this.lastStatusFile + " is empty." );
            }

            // deserialize a temporary Configuration instance from xml
            String xml = content.toString();
            if (xml.length() > 0) {
                XStream xstream = new XStream();
                xstream.addPermission(AnyTypePermission.ANY);

                try {
                    this.states = (LinkedHashMap<String, State>) xstream.fromXML( xml );
                } catch (Exception ex) {
                    LOG.warn( "XML-file could not be read ... ignoring: " + this.lastStatusFile.getAbsolutePath() );
                    this.states = new LinkedHashMap<String, State>();
                }
            } else {
                this.states = new LinkedHashMap<String, State>();
            }
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {}
            }
        }
    }

    /**
     * Get the message list as String. Message entries are formated according to
     * the format. the default format is "%1$tF %1$tT - %2$s\n".
     * 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        synchronized (this) {
            for (State state : states.values()) {
                // do not display null states
                if (state.getValue() != null) {
                    if (state.classification.equals( Classification.ERROR )) {
                        sb.append( "<span class=\"error\">" + String.format( msgFormat, state.time, state.classification.name(), state.value ) + "</span>" );
                    } else if (state.classification.equals( Classification.WARN )) {
                        sb.append( "<span class=\"warn\">" + String.format( msgFormat, state.time, state.classification.name(), state.value ) + "</span>" );
                    } else {
                        sb.append( "<span class=\"info\">" + String.format( msgFormat, state.time, state.classification.name(), state.value ) + "</span>" );
                    }
                }
            }
        }
        return sb.toString();
    }

    public List<State> getStates() {
        return new ArrayList<State>( this.states.values() );
    }

    public String getMsgFormat() {
        return msgFormat;
    }

    /**
     * Set the message format.
     * 
     * @param msgFormat
     */
    public void setMsgFormat(String msgFormat) {
        this.msgFormat = msgFormat;
    }

}
