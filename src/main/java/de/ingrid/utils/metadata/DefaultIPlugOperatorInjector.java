/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2015 wemove digital solutions GmbH
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
package de.ingrid.utils.metadata;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.ingrid.utils.PlugDescription;

public class DefaultIPlugOperatorInjector extends AbstractIPlugOperatorInjector {

    private PlugDescription _plugDescription;

    @Override
    public IPlugOperatorFinder createOperatorFinder() {

        IPlugOperatorFinder finder = new IPlugOperatorFinder() {

            @Override
            public Set<String> findProvider() throws IOException {
                String[] providers = _plugDescription != null ? _plugDescription.getProviders() : new String[] {};
                Set<String> set = new HashSet<String>(Arrays.asList(providers));
                return set;
            }

            @Override
            public Set<String> findPartner() throws IOException {
                String[] partners = _plugDescription != null ? _plugDescription.getPartners() : new String[] {};
                Set<String> set = new HashSet<String>(Arrays.asList(partners));
                return set;
            }

            @Override
            public void configure(PlugDescription plugDescription) {
            }
        };
        return finder;
    }

    @Override
    public void configure(PlugDescription description) {
        _plugDescription = description;
    }

}
