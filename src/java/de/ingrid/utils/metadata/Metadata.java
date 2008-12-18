package de.ingrid.utils.metadata;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Metadata implements Serializable {

	private static final long serialVersionUID = -882806556761084500L;

	private String _version;

	private Date _releaseDate;

	private IPlugType _plugType;
	
	private Map<String, Serializable> _otherMetadatas = new HashMap<String, Serializable>();


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

	public void addMetadata(String key, Serializable value) {
		_otherMetadatas.put(key, value);
	}
	
	public Serializable getMetadata(String key) {
		return _otherMetadatas.get(key);
	}

}
