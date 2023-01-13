/*
 * **************************************************-
 * ingrid-interface-csw
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
/*
 * Copyright (c) 2012 wemove digital solutions. All rights reserved.
 */
package de.ingrid.utils.tool;

import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Node;

public class XsltUtils {

	final protected static Log log = LogFactory.getLog(XsltUtils.class);

	private Map<String, Transformer> transformers = new Hashtable<String, Transformer>();

	/**
	 * Transform the given document using the stylesheet
	 * 
	 * @param document
	 * @param stylesheet
	 * @return Node
	 * @throws Exception
	 */
	public Node transform(Node document, String styleSheet) throws Exception {

		if (!this.transformers.containsKey(styleSheet)) {
			// create transformer for the stylesheet, if it does not exist yet

			if (log.isDebugEnabled()) {
				@SuppressWarnings("resource")
                String fisString = new Scanner(new ClassPathResource(styleSheet).getInputStream(), "UTF-8")
				        .useDelimiter("\\A").next();
				log.debug("\n\nAdding styleSheet:\n\n" + fisString);
			}

			Source source = new StreamSource(new ClassPathResource(styleSheet).getInputStream());
			TransformerFactory factory = TransformerFactory.newInstance();
			this.transformers.put(styleSheet, factory.newTransformer(source));
		}

		// perform transformation
		Transformer transformer = this.transformers.get(styleSheet);
		DOMSource source = new DOMSource(document);
		DOMResult result = new DOMResult();
		transformer.transform(source, result);

		return result.getNode();
	}
}
