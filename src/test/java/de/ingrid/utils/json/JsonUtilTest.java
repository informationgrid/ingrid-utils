/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
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
package de.ingrid.utils.json;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import de.ingrid.utils.IngridDocument;

public class JsonUtilTest {

    @Test
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
