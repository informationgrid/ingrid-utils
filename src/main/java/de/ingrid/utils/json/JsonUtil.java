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

                @SuppressWarnings("unchecked")
                public Map createObjectContainer() {
                    return new IngridDocument();
                }

            };
        }
        return parser.parse(jsonStr, containerFactory);

    }
}
