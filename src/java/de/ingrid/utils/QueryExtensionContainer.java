package de.ingrid.utils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QueryExtensionContainer implements Externalizable {

    private static final long serialVersionUID = QueryExtension.class.getName().hashCode();
    
    private Map<String, QueryExtension> _queryExtensions = new HashMap<String, QueryExtension>();

    public Map<String, QueryExtension> getQueryExtensions() {
        return _queryExtensions;
    }

    public void addQueryExtension(QueryExtension queryExtension) {
        _queryExtensions.put(queryExtension.getBusUrl(), queryExtension);
    }

    public QueryExtension getQueryExtension(String busUrl) {
        return _queryExtensions.get(busUrl);
    }

    public QueryExtension removeQueryExtension(String busUrl) {
        return _queryExtensions.remove(busUrl);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        _queryExtensions.clear();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            QueryExtension extension = (QueryExtension) in.readObject();
            _queryExtensions.put(extension.getBusUrl(), extension);
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(_queryExtensions.size());
        Collection<QueryExtension> values = _queryExtensions.values();
        for (Iterator<QueryExtension> iterator = values.iterator(); iterator.hasNext();) {
            QueryExtension queryExtension = (QueryExtension) iterator.next();
            out.writeObject(queryExtension);
        }
    }
}
