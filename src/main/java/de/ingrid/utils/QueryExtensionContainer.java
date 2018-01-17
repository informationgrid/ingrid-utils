/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2018 wemove digital solutions GmbH
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
package de.ingrid.utils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QueryExtensionContainer implements Externalizable {

    private static final long serialVersionUID = QueryExtensionContainer.class.getName().hashCode();
    
    private Map<String, QueryExtension> _queryExtensions = new HashMap<String, QueryExtension>();

    public Map<String, QueryExtension> getQueryExtensions() {
        return _queryExtensions;
    }

    public void addQueryExtension(QueryExtension queryExtension) {
        _queryExtensions.put(queryExtension.getBusUrl(), queryExtension);
    }

	public void setQueryExtension(Map<String, QueryExtension> extensions) {
		_queryExtensions.clear();
		_queryExtensions.putAll(extensions);
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
		for (QueryExtension queryExtension : values) {
            out.writeObject(queryExtension);
		}
	}
}
