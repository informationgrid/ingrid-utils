package de.ingrid.utils.metadata;

import java.io.Serializable;
import java.util.Date;

public class Metadata implements Serializable {

	private static final long serialVersionUID = -882806556761084500L;

	private String _version;

	private Date _releaseDate;

	private IPlugType _plugType;


	public Metadata() {
	}
	
	public Metadata(IPlugType plugType, Date releaseDate, String version) {
		_plugType = plugType;
		_releaseDate = releaseDate;
		_version = version;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public Date getReleaseDate() {
		return _releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		_releaseDate = releaseDate;
	}

	public IPlugType getPlugType() {
		return _plugType;
	}

	public void setPlugType(IPlugType plugType) {
		_plugType = plugType;
	}


}
