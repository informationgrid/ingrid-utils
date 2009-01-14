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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_otherMetadatas == null) ? 0 : _otherMetadatas.hashCode());
		result = prime * result
				+ ((_plugType == null) ? 0 : _plugType.hashCode());
		result = prime * result
				+ ((_releaseDate == null) ? 0 : _releaseDate.hashCode());
		result = prime * result
				+ ((_version == null) ? 0 : _version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metadata other = (Metadata) obj;
		if (_otherMetadatas == null) {
			if (other._otherMetadatas != null)
				return false;
		} else if (!_otherMetadatas.equals(other._otherMetadatas))
			return false;
		if (_plugType == null) {
			if (other._plugType != null)
				return false;
		} else if (!_plugType.equals(other._plugType))
			return false;
		if (_releaseDate == null) {
			if (other._releaseDate != null)
				return false;
		} else if (!_releaseDate.equals(other._releaseDate))
			return false;
		if (_version == null) {
			if (other._version != null)
				return false;
		} else if (!_version.equals(other._version))
			return false;
		return true;
	}

}
