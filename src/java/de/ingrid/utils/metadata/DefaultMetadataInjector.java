package de.ingrid.utils.metadata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultMetadataInjector implements IMetadataInjector {

	public static final String DEFAULT_METADATA = "DEFAULT_METADATA";

	private MetadataAnnotation _metaDataAnnotation;

	private DateFormat _format = new SimpleDateFormat("yyyy-MM-dd");

	private Date _releaseDate = new Date(0);

	public DefaultMetadataInjector() {
		Package package1 = Metadata.class.getPackage();
		_metaDataAnnotation = package1.getAnnotation(MetadataAnnotation.class);
	}

	public void injectMetaDatas(Metadata metadata) {
		String version = getVersion();
		IPlugType plugType = getIPlugType();
		Date releaseDate = getReleaseDate();
		metadata.setPlugType(plugType);
		metadata.setVersion(version);
		metadata.setReleaseDate(releaseDate);
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
