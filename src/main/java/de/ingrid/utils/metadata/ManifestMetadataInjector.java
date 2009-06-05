package de.ingrid.utils.metadata;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import de.ingrid.utils.PlugDescription;
import de.ingrid.utils.processor.impl.QueryExtensionPreProcessor;

/**
 * This MetadataInjector tries to get the required information from the
 * MANIFEST.MF file from iPlug's jar. If no MANIFEST.MF is found, the
 * metadata is not changed.
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
    
	private static final Log LOG = LogFactoryImpl.getLog(QueryExtensionPreProcessor.class);

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
		try {
			buildTimeStamp = Long.parseLong(attr.getValue("Build-Timestamp"));
		} catch (NumberFormatException e) {
		}
		return new Date(buildTimeStamp);
	}

	@Override
	public void configure(PlugDescription description) {
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
			} catch (MalformedURLException e) {
				LOG.error("Could not create URL for jar '" + classContainer + "'.", e);
			} catch (ClassNotFoundException e) {
				LOG.error("Could not instantiate class '" + plugClassStr + "'.", e);
			} catch (Exception e) {
				LOG.error("Error accessing MANIFEST.MF in jar '" + classContainer + "' that contains class '" + plugClassStr + "'.", e);
			}
		}
	}

}
