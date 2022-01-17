/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2022 wemove digital solutions GmbH
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CoordUtil {

    public class Point {
        public String x;

        public String y;
    }

    private static CoordUtil _cache;

    private final Map<String, Point> _points = new HashMap<String, Point>();

    public CoordUtil() {
    }

    public CoordUtil(final InputStream input) throws IOException {
        if (input != null) {
            loadProperties(input);
        }
    }

    public static CoordUtil getCoords(final InputStream input) throws IOException {
        if (_cache == null) {
            _cache = new CoordUtil(input);
        }
        return _cache;
    }

    public void addPoint(final String group, final String x, final String y) {
        setValue(group, "x", x);
        setValue(group, "y", y);
    }

    public boolean isCoord(final String name) {
        for (final String group : _points.keySet()) {
            final Point p = _points.get(group);
            if (name.equals(p.x) || name.equals(p.y)) {
                return true;
            }
        }
        return false;
    }

    public boolean isXCoord(final String name) {
        for (final String group : _points.keySet()) {
            final Point p = _points.get(group);
            if (name.equals(p.x)) {
                return true;
            }
        }
        return false;
    }

    public boolean isYCoord(final String name) {
        for (final String group : _points.keySet()) {
            final Point p = _points.get(group);
            if (name.equals(p.y)) {
                return true;
            }
        }
        return false;
    }

    public String getSibling(final String name) {
        for (final String group : _points.keySet()) {
            final Point p = _points.get(group);
            if (name.equals(p.y)) {
                return p.x;
            } else if (name.equals(p.x)) {
                return p.y;
            }
        }
        return null;
    }

    private void loadProperties(final InputStream input) throws IOException {
        final Properties props = new Properties();
        props.load(input);
        final Enumeration<Object> elements = props.keys();
        while (elements.hasMoreElements()) {
            final String full = (String) elements.nextElement();
            final String name = props.getProperty(full);
            final String group = getGroup(full);
            final String coord = full.substring(group.length() + 1, full.length());
            setValue(group, coord, name);
        }
    }

    private String getGroup(final String full) {
        String group = "";
        String rest = full;
        int index = -1;
        while ((index = rest.indexOf(".")) > 0) {
            if (!"".equals(group)) {
                group += ".";
            }
            group += rest.substring(0, index);
            rest = rest.substring(index + 1, rest.length());
        }
        return group;
    }

    private void setValue(final String group, final String coord, final String name) {
        Point p;
        if (_points.containsKey(group)) {
            p = _points.get(group);
        } else {
            p = new Point();
            _points.put(group, p);
        }
        if ("x".equals(coord)) {
            p.x = name;
        } else if ("y".equals(coord)) {
            p.y = name;
        }
    }
}
