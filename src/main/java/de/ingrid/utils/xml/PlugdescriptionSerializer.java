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
