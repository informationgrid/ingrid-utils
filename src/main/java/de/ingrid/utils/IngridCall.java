package de.ingrid.utils;

public class IngridCall extends IngridDocument {

    private static final long serialVersionUID = 20L;

    private static final String TARGET = "target";

    private static final String METHOD = "method";

    private static final String PARAMETER = "parameter";

    public String getTarget() {
        return getString( TARGET );
    }

    public void setTarget(String target) {
        put( TARGET, target );
    }

    public String getMethod() {
        return getString( METHOD );
    }

    public void setMethod(String method) {
        put( METHOD, method );
    }

    public Object getParameter() {
        return get( PARAMETER );
    }

    public void setParameter(Object parameter) {
        put( PARAMETER, parameter );
    }
}
