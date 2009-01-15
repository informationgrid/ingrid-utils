package de.ingrid.utils.metadata;

import java.io.IOException;
import java.util.Set;

import de.ingrid.utils.PlugDescription;

public interface IPlugOperatorFinder {

    Set<String> findPartner() throws IOException;
    
    Set<String> findProvider() throws IOException;
    
    void configure(PlugDescription plugDescription);

}
