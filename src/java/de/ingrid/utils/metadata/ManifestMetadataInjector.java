package de.ingrid.utils.metadata;

import java.net.URL;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import de.ingrid.utils.PlugDescription;

/**
 * This MetadataInjector tries to get the required information from the
 * MANIFEST.MF file from iPlug's jar.
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

	private Manifest _manifest;

	public ManifestMetadataInjector() {
	}

	public void injectMetaDatas(Metadata metadata) {

		metadata.setPlugType(getIPlugType());
		metadata.setVersion(getVersion());
		metadata.setReleaseDate(getBuildDate());
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
	public void configure(PlugDescription description) throws Exception {
		// get the main class of the iPlug and try to get the Manifest.mf
		String plugClassStr = description.getIPlugClass();
		if (plugClassStr == null) {
			throw new NullPointerException("iplug class in plugdescription not set");
		}
		Class<?> plugClass = Thread.currentThread().getContextClassLoader().loadClass(plugClassStr);
		String classContainer = plugClass.getProtectionDomain().getCodeSource().getLocation().toString();
		URL manifestUrl = new URL("jar:" + classContainer + "!/META-INF/MANIFEST.MF");
		_manifest = new Manifest(manifestUrl.openStream());
	}

}
