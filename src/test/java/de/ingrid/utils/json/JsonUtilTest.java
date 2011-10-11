package de.ingrid.utils.json;

import java.util.List;

import org.json.simple.parser.ParseException;

import junit.framework.TestCase;
import de.ingrid.utils.IngridDocument;

public class JsonUtilTest extends TestCase {

    public void testParseJsonToIngrid() throws ParseException {
        List<IngridDocument> result = JsonUtil.parseJsonToListOfIngridDocument("[\n" + 
        		"    {\n" + 
        		"        \"id\":\"partne\",\n" + 
        		"        \"topn\":5,\n" + 
        		"        \"classes\":\n" + 
        		"        [\n" + 
        		"            {\n" + 
        		"                \"id\":\"ni\",\n" + 
        		"                \"fragment\": \"partner:ni\"\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"                \"id\":\"bw\",\n" + 
        		"                \"fragment\": \"partner:bw\"\n" + 
        		"            }\n" + 
        		"        ]\n" + 
        		"    },\n" + 
        		"    {\n" + 
        		"        \"id\":\"provider\",\n" + 
        		"    }\n" + 
        		"]");
        
        System.out.println(result);
    }

}
