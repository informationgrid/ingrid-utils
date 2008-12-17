package de.ingrid.utils.metadata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.ingrid.utils.PlugDescription;

public class DefaultMetadataInjector implements IMetadataInjector {

	public static final String DEFAULT_METADATA = "DEFAULT_METADATA";

	private MetadataAnnotation _metaDataAnnotation;

	private DateFormat _format = new SimpleDateFormat("yyyy-MM-dd");

	private Date _releaseDate = new Date(0);

	public DefaultMetadataInjector() {
		Package package1 = Metadata.class.getPackage();
		System.out.println(Package.getPackage("de.ingrid.utils.metadata"));
		System.out.println(DefaultMetadataInjector.class.getPackage());
		Class clazz = de.ingrid.utils.metadata.Metadata.class;
		Package package2 = clazz.getPackage();
		System.out.println(package2);
		_metaDataAnnotation = package1.getAnnotation(MetadataAnnotation.class);
	}

	public void injectMetaDatas(PlugDescription plugDescription) {
		String version = getVersion();
		IPlugType plugType = getIPlugType();
		Date releaseDate = getReleaseDate();
		Metadata metadata = new Metadata(plugType, releaseDate, version);
		plugDescription.put(DEFAULT_METADATA, metadata);
	}

	private String getVersion() {
		return _metaDataAnnotation != null ? _metaDataAnnotation.version()
				: "unknown";
	}

	private Date getReleaseDate() {
		Date date = _releaseDate;
		try {
			date = _metaDataAnnotation != null ? _format
					.parse(_metaDataAnnotation.date()) : _releaseDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	private IPlugType getIPlugType() {
		return _metaDataAnnotation != null ? _metaDataAnnotation.type()
				: IPlugType.OTHER;
	}

}
