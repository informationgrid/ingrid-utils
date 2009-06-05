package de.ingrid.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanFactory {

    private static final Log LOG = LogFactory.getLog(BeanFactory.class);
    
    private  Map _beans = new HashMap();

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
