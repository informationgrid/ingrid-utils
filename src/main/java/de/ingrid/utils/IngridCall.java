/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2017 wemove digital solutions GmbH
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

public class IngridCall extends IngridDocument {

    private static final long serialVersionUID = 20L;

    private static final String TARGET = "target";

    private static final String METHOD = "method";

    private static final String PARAMETER = "parameter";

    public String getTarget() {
        return getString( TARGET );
    }

    public void setTarget(String target) {
        put( TARGET, target );
    }

    public String getMethod() {
        return getString( METHOD );
    }

    public void setMethod(String method) {
        put( METHOD, method );
    }

    public Object getParameter() {
        return get( PARAMETER );
    }

    public void setParameter(Object parameter) {
        put( PARAMETER, parameter );
    }
}
