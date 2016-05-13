/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2016 wemove digital solutions GmbH
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
/**
 * 
 */
package de.ingrid.utils.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.ingrid.utils.IngridDocument;

/**
 * @author joachim
 * 
 */
public class JsonUtil {

    static JSONParser parser = null;
    static ContainerFactory containerFactory;

    public static IngridDocument parseJsonToIngridDocument(String jsonStr) throws ParseException {
        Object o = parseJson(jsonStr);
        if (o instanceof IngridDocument) {
            return (IngridDocument) o;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<IngridDocument> parseJsonToListOfIngridDocument(String jsonStr) throws ParseException {
        Object o = parseJson(jsonStr);
        if (o instanceof List) {
            return (List<IngridDocument>) o;
        } else {
            return null;
        }
    }

    private static Object parseJson(String jsonStr) throws ParseException {
        if (parser == null) {
            parser = new JSONParser();
            containerFactory = new ContainerFactory() {
                public List<IngridDocument> creatArrayContainer() {
                    return new ArrayList<IngridDocument>();
                }

                public Map<Object, Object> createObjectContainer() {
                    return new IngridDocument();
                }

            };
        }
        return parser.parse(jsonStr, containerFactory);

    }
}
