/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2019 wemove digital solutions GmbH
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
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.query;


/**
 * Term queries are queries having just one term.
 * 
 * created on 09.08.2005
 * 
 * @author sg
 * @version $Revision: 1.3 $
 */
public class TermQuery extends IngridQuery {

    private static final long serialVersionUID = 10L;

    /**
     * Constructs a term query
     */
    public TermQuery(boolean required, boolean prohibited, String term) {
        super(required, prohibited, IngridQuery.TERM, term);
    }

    /**
     * 
     */
    public TermQuery() {
        // be serializable
    }

    /**
     * @return the query term
     */
    public String getTerm() {
        return (String) getContent();
    }
    
    public int hashCode() {
        return 31 + super.hashCode() + getTerm().hashCode();
    }
}
