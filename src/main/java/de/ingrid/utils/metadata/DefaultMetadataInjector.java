/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils.metadata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.ingrid.utils.PlugDescription;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultMetadataInjector implements IMetadataInjector {

	private static Log log = LogFactory.getLog(DefaultMetadataInjector.class);

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
			log.error("Error getting release date", e);
		}
		return date;
	}

	private IPlugType getIPlugType() {
		return _metaDataAnnotation != null ? _metaDataAnnotation.type()
				: IPlugType.OTHER;
	}

	@Override
	public void configure(PlugDescription description) {
		// nothing todo

	}

}
