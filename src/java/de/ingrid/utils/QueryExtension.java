package de.ingrid.utils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import de.ingrid.utils.query.FieldQuery;

public class QueryExtension implements Externalizable {

    private String _busUrl;

    private Set<FieldQuery> _fieldQueries = new HashSet<FieldQuery>();

    private Pattern _pattern;

    public QueryExtension() {
        // nothing todo
    }

    public String getBusUrl() {
        return _busUrl;
    }

    public void setBusUrl(String busUrl) {
        _busUrl = busUrl;
    }

    public Set<FieldQuery> getFieldQueries() {
        return _fieldQueries;
    }

    public void addFieldQuery(FieldQuery fieldQuery) {
        _fieldQueries.add(fieldQuery);
    }

    public Pattern getPattern() {
        return _pattern;
    }

    public void setPattern(Pattern pattern) {
        _pattern = pattern;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        _fieldQueries.clear();
        _busUrl = (String) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            boolean required = in.readBoolean();
            boolean prohibited = in.readBoolean();
            String fieldName = (String) in.readObject();
            String fieldValue = (String) in.readObject();
            _fieldQueries.add(new FieldQuery(required, prohibited, fieldName, fieldValue));
        }
        _pattern = Pattern.compile(((String) in.readObject()));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(_busUrl);
        out.writeInt(_fieldQueries.size());
        for (FieldQuery fieldQuery : _fieldQueries) {
            out.writeBoolean(fieldQuery.isRequred());
            out.writeBoolean(fieldQuery.isProhibited());
            out.writeObject(fieldQuery.getFieldName());
            out.writeObject(fieldQuery.getFieldValue());
        }
        out.writeObject(_pattern.pattern());
    }

}
