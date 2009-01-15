package de.ingrid.utils.metadata;

import java.io.IOException;
import java.util.Set;

public interface IPlugOperatorFinder {

    Set<String> findPartner() throws IOException;
    
    Set<String> findProvider() throws IOException;

}
