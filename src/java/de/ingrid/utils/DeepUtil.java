package de.ingrid.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

public class DeepUtil {

    public static final int MAX_DEEP = 50;

    private static final Log LOG = LogFactoryImpl.getLog(DeepUtil.class);

    public static String deepString(Object object, int deep) {
        String ret = "";
        if (deep < MAX_DEEP) {
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                for (Object object2 : objects) {
                    ret += "_" + deepString(object2, deep + 1);
                }
            } else {
                ret += "_" + ((object == null) ? "" : object.toString());
            }
        }
        return ret;
    }
    
    public static int deepHashcode(Object object, int deep) {
        final int prime = 31;
        int result = 1;
        if ((object instanceof Object[]) && deep < MAX_DEEP) {
            Object[] objects = (Object[]) object;
            for (Object object2 : objects) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("compute hashcode recursive with deep: " + deep);
                }
                result = prime * result + deepHashcode(object2, deep + 1);
            }
        } else {
            result = prime * result + ((object == null) ? 0 : object.hashCode());
        }
        return result;
    }
    
}
