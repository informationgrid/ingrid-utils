package de.ingrid.utils.metadata;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IIndexFinder {

    List<File> findIndices(File folder) throws IOException;
    
    Set<String> findIndexValues(File workingFolder, String indexFieldName) throws IOException;

}
