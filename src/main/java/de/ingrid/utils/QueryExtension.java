package de.ingrid.utils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import de.ingrid.utils.query.FieldQuery;

public class QueryExtension implements Externalizable {

    private static final long serialVersionUID = QueryExtension.class.getName().hashCode();

	public static final String DEFAULT_REGEX = ".*";

	public static final Pattern DEFAULT_PATTERN = Pattern.compile(DEFAULT_REGEX);

    private static final String NEW_VERSION = "NEW_QUERY_EXTENSION_VERSION";

    private String _busUrl;

	private final Map<Pattern, Set<FieldQuery>> _queryMap = new HashMap<Pattern, Set<FieldQuery>>();

    public QueryExtension() {
        // nothing todo
    }

    public String getBusUrl() {
        return _busUrl;
    }

    public void setBusUrl(String busUrl) {
        _busUrl = busUrl;
    }

	public Set<Pattern> getPatterns() {
		return _queryMap.keySet();
	}

    public Set<FieldQuery> getFieldQueries() {
		Collection<Set<FieldQuery>> values = _queryMap.values();
		final Set<FieldQuery> result = new HashSet<FieldQuery>();
		for (final Set<FieldQuery> fields : values) {
			result.addAll(fields);
		}
		return result;
	}

	public Set<FieldQuery> getFieldQueries(final Pattern pattern) {
		return _queryMap.get(pattern);
    }

	public Set<FieldQuery> getFieldQueries(final String regex) {
		return _queryMap.get(Pattern.compile(regex));
	}

    public void addFieldQuery(final FieldQuery fieldQuery) {
		addFieldQuery(DEFAULT_PATTERN, fieldQuery);
    }

	public void addFieldQuery(final String regex, final FieldQuery fieldQuery) {
		addFieldQuery(Pattern.compile(regex), fieldQuery);
	}

	public void addFieldQuery(final Pattern pattern, final FieldQuery fieldQuery) {
		Set<FieldQuery> fields = _queryMap.get(pattern);
		if (fields == null) {
			fields = new HashSet<FieldQuery>();
		}
		fields.add(fieldQuery);
		_queryMap.put(pattern, fields);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
		_queryMap.clear();
        final String first = (String) in.readObject();
        if (NEW_VERSION.equals(first)) {
            readNewExternal(in);
        } else {
            readOldExternal(first, in);
        }
    }

    public void readNewExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        _busUrl = (String) in.readObject();
        final int patternCount = in.readInt();
        for (int i = 0; i < patternCount; i++) {
            final Pattern pattern = Pattern.compile((String) in.readObject());
            final int fieldCount = in.readInt();
            for (int j = 0; j < fieldCount; j++) {
                final boolean required = in.readBoolean();
                final boolean prohibited = in.readBoolean();
                final String fieldName = (String) in.readObject();
                final String fieldValue = (String) in.readObject();
                addFieldQuery(pattern, new FieldQuery(required, prohibited, fieldName, fieldValue));
            }
        }
    }

    public void readOldExternal(final String bus, final ObjectInput in) throws IOException, ClassNotFoundException {
        _busUrl = bus;
        int count = in.readInt();
        final List<FieldQuery> fieldQueries = new ArrayList<FieldQuery>();
        for (int i = 0; i < count; i++) {
            boolean required = in.readBoolean();
            boolean prohibited = in.readBoolean();
            String fieldName = (String) in.readObject();
            String fieldValue = (String) in.readObject();
            fieldQueries.add(new FieldQuery(required, prohibited, fieldName, fieldValue));
        }
        final Pattern pattern = Pattern.compile(((String) in.readObject()));
        for (final FieldQuery fieldQuery : fieldQueries) {
            addFieldQuery(pattern, fieldQuery);
        }
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(NEW_VERSION);
        out.writeObject(_busUrl);
        out.writeInt(_queryMap.size());
        for(final Pattern pattern : _queryMap.keySet()) {
			out.writeObject(pattern.pattern());
			final Set<FieldQuery> fieldQueries = getFieldQueries(pattern);
			out.writeInt(fieldQueries.size());
			for (final FieldQuery fieldQuery : fieldQueries) {
				out.writeBoolean(fieldQuery.isRequred());
				out.writeBoolean(fieldQuery.isProhibited());
				out.writeObject(fieldQuery.getFieldName());
				out.writeObject(fieldQuery.getFieldValue());
			}
        }
    }

}
