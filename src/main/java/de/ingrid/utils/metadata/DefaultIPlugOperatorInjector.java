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
