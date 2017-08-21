package io.vertace.legacy.http.context;

import io.vertx.ext.web.RoutingContext;

public class ParamContext {

    private RoutingContext vertxRoutingContext;

    public ParamContext(RoutingContext routingContext) {
        this.vertxRoutingContext = routingContext;
    }

}
