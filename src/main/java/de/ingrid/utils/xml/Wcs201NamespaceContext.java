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
package de.ingrid.utils.xml;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.Iterator;

/**
 * @author joachim
 *
 */
public class Wcs201NamespaceContext implements NamespaceContext {

	public static String NAMESPACE_URI_WCS = "http://www.opengis.net/wcs/2.0";
	public static String NAMESPACE_URI_DEGREE = "http://www.deegree.org";
	public static String NAMESPACE_URI_XLINK = "http://www.w3.org/1999/xlink";
	public static String NAMESPACE_URI_GML = "http://www.opengis.net/gml/3.2";
	public static String NAMESPACE_URI_OWS = "http://www.opengis.net/ows/2.0";
	public static String NAMESPACE_URI_GMLCOV = "http://www.opengis.net/gmlcov/1.0";
	public static String NAMESPACE_URI_CRS = "http://www.opengis.net/wcs/crs/1.0";


	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String prefix) {
		switch (prefix) {
			case "wcs201":
				return NAMESPACE_URI_WCS;
			case "degree":
				return NAMESPACE_URI_DEGREE;
			case "xlink":
				return NAMESPACE_URI_XLINK;
			case "gml32":
				return NAMESPACE_URI_GML;
			case "ows20":
				return NAMESPACE_URI_OWS;
			case "gmlcov":
				return NAMESPACE_URI_GMLCOV;
			case "crs":
				return NAMESPACE_URI_CRS;
			default:
				return XMLConstants.NULL_NS_URI;
		}
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefix(java.lang.String)
	 */
	@Override
	public String getPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefixes(java.lang.String)
	 */
	@Override
	public Iterator<String> getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

}
