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

import java.io.File;
import java.io.IOException;

import de.ingrid.utils.PlugDescription;

public class PlugdescriptionSerializer extends XMLSerializer {


    public void serialize(PlugDescription plugDescription, File target) throws IOException {
        plugDescription.remove(PlugDescription.DESERIALIZED_FROM_FOLDER);
        super.serialize(plugDescription, target);
    }

    public PlugDescription deSerialize(File target) throws IOException {
        PlugDescription plugDescription = (PlugDescription) super.deSerialize(target);
        plugDescription.setDeserializedFromFolder(target);
        return plugDescription;
    }
}
