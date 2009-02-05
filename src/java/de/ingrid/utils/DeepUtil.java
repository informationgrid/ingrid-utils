package de.ingrid.utils;

public class DeepUtil {

    public static final int MAX_DEEP = 50;

    public static String deepString(Object object, int deep) {
        String ret = "";
        if ((object instanceof Object[]) && deep < MAX_DEEP) {
            Object[] objects = (Object[]) object;
            for (Object object2 : objects) {
                ret = "_" + deepString(object2, deep + 1);
            }
        } else {
            ret = "_" + ((object == null) ? "" : object.toString());
        }
        return ret;
    }
}
