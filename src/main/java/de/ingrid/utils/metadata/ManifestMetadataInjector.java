/*
 * **************************************************-
 * ingrid-utils
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
package de.ingrid.utils.metadata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.PlugDescription;

/**
 * This MetadataInjector tries to get the required information from the
 * MANIFEST.MF file from iPlug's jar. It tries to load the jar based on
 * the class file references in the {@see PlugDescription}.
 * 
 * If the class file is unpacked in a web application (WEB-INF/classes),
 * the MAINIFEST.MF will be obtained from the path ../../META-INF/MANIFEST.MF
 * 
 * If no MANIFEST.MF is found, the metadata is not changed.
 * 
 * It scans the following items:
 * 
 * <ul>
 * <li>Implementation-Version</li>
 * <li>Implementation-Build</li>
 * <li>iPlug-Type</li>
 * <li>Build-Timestamp</li>
 * </ul>
 * 
 * The Metadata-Version is set to "&lt;implementation-Version&gt; Build:
 * &lt;Implementation-Build&gt;".
 * 
 * 
 * @author joachim@wemove.com
 * 
 */
public class ManifestMetadataInjector implements IMetadataInjector {

	private Manifest _manifest = null;
    
	private static final Log LOG = LogFactory.getLog(ManifestMetadataInjector.class);

	public ManifestMetadataInjector() {
	}

	public void injectMetaDatas(Metadata metadata) {

		if (_manifest != null) {
			metadata.setPlugType(getIPlugType());
			metadata.setVersion(getVersion());
			metadata.setReleaseDate(getBuildDate());
		}
	}

	private String getVersion() {
		Attributes attr = _manifest.getMainAttributes();
		String version = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
		String build = attr.getValue("Implementation-Build");
		if (version == null) {
			version = "unknown";
		} else if (build != null) {
			version = version + " Build:" + build;
		}
		return version;
	}

	private IPlugType getIPlugType() {
		Attributes attr = _manifest.getMainAttributes();

		String plugTypeString = attr.getValue("iPlug-Type");
		IPlugType plugType = null;
		if (plugTypeString == null) {
			plugType = IPlugType.OTHER;
		} else {
			plugType = IPlugType.valueOf(plugTypeString);
			if (plugType == null) {
				plugType = IPlugType.OTHER;
			}
		}

		return plugType;
	}

	private Date getBuildDate() {
		Attributes attr = _manifest.getMainAttributes();

		long buildTimeStamp = 0;
		String value = attr.getValue("Build-Timestamp");
        if (value != null) {
            try {
                buildTimeStamp = Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
		}
		return new Date(buildTimeStamp);
	}

	@Override
	public void configure(PlugDescription description) {
		// check for existing manifest, do not overwrite it!!
		if (_manifest != null) {
			return;
		}
		
		// get the main class of the iPlug and try to get the Manifest.mf
		String plugClassStr = description.getIPlugClass();
		if (plugClassStr == null) {
			LOG.error("iplug class in plugdescription not set.");
		} else {
			String classContainer = null;
			URL manifestUrl;
			try {
				Class<?> plugClass = Thread.currentThread().getContextClassLoader().loadClass(plugClassStr);
				classContainer = plugClass.getProtectionDomain().getCodeSource().getLocation().toString();
				manifestUrl = new URL("jar:" + classContainer + "!/META-INF/MANIFEST.MF");
				_manifest = new Manifest(manifestUrl.openStream());
				if (LOG.isDebugEnabled()) {
					LOG.debug("Accessing manifest: " + "jar:" + classContainer + "!/META-INF/MANIFEST.MF");
				}
			} catch (MalformedURLException e) {
				LOG.error("Could not create URL for jar '" + classContainer + "'.", e);
			} catch (ClassNotFoundException e) {
				LOG.error("Could not instantiate class '" + plugClassStr + "'.", e);
			} catch (Exception e) {
			    // try to get it from the webapp (newer iPlugs)
			    getManifestFromWebapp(classContainer, plugClassStr);
			}
		}
	}

    private void getManifestFromWebapp(String classDir, String plugClassStr) {
        try {
            URL url = new URL(classDir + "../../META-INF/MANIFEST.MF");
            _manifest = new Manifest(url.openStream());
            if (LOG.isDebugEnabled()) {
                LOG.debug("Accessing manifest from webapp: " + classDir + "../../META-INF/MANIFEST.MF");
            }
        } catch (FileNotFoundException e) {
            LOG.error("MANIFEST.MF not found in jar or webapp-dir '" + classDir + "' that contains class '" + plugClassStr + "'.");
            
        } catch (IOException e) {
            LOG.error("Error accessing MANIFEST.MF in jar or webapp-dir '" + classDir + "' that contains class '" + plugClassStr + "'.", e);
        }

    }

}
