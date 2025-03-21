/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanFactory {

    private static final Log LOG = LogFactory.getLog(BeanFactory.class);
    
    private  Map<String, Object> _beans = new HashMap<String, Object>();

    public void addBean(String name, Object object) throws IOException {
        if (_beans.containsKey(name)) {
            throw new IOException("bean instance already exists: " + name);
        }
        LOG.debug("add bean: " + name);
        _beans.put(name, object);
    }

    public Object getBean(String name) throws IOException {
        if (!_beans.containsKey(name)) {
            throw new IOException("bean instance not exists: " + name);
        }
        LOG.debug("return bean: " + name);
        return _beans.get(name);
    }
}
