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
package de.ingrid.utils.xml;


/**
 * @author toan
 *
 */
public class IDFNamespaceContext extends Csw202NamespaceContext {

	public static String NAMESPACE_URI_IDF = "http://www.portalu.de/IDF/1.0";
	public static String NAMESPACE_URI_KML = "http://www.opengis.net/kml/2.2";
	
	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		if (prefix.equals("idf")) {
            return NAMESPACE_URI_IDF;
		} else if (prefix.equals("kml")) {
            return NAMESPACE_URI_KML;
		} else {
            return super.getNamespaceURI(prefix);
		}
	}
}
