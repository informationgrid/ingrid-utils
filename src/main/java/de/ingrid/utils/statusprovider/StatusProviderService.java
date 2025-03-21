/*-
 * **************************************************-
 * ingrid-iplug-se-iplug
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
package de.ingrid.utils.statusprovider;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class StatusProviderService {

    private final String DEFAULT_PROVIDER_KEY = "DEFAULT_PROVIDER";

    private ConcurrentHashMap<String, StatusProvider> statusProviders;

    public StatusProvider getStatusProvider(String logdir) {
        return getStatusProvider( logdir, "last_status.xml" );
    }

    public StatusProvider getStatusProvider(String logdir, String statusFilename) {
        if (statusProviders == null) {
            statusProviders = new ConcurrentHashMap<>();
        }
        String mapKey = logdir + statusFilename;
        if (!statusProviders.containsKey( mapKey )) {
            if (mapKey.equals( DEFAULT_PROVIDER_KEY )) {
                statusProviders.put( DEFAULT_PROVIDER_KEY, new StatusProvider() );
            } else {
                statusProviders.put( mapKey, new StatusProvider( logdir, statusFilename ) );
            }
        }
        return statusProviders.get( mapKey );
    }

    /**
     * 
     * @return the defaultStatusprovider
     */
    public StatusProvider getDefaultStatusProvider() {
        return getStatusProvider( DEFAULT_PROVIDER_KEY, "" );
    }

}
