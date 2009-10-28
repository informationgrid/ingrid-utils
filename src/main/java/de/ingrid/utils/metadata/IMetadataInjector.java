package de.ingrid.utils.metadata;

import de.ingrid.utils.IConfigurable;

public interface IMetadataInjector extends IConfigurable {

    void injectMetaDatas(Metadata metadata);

}
