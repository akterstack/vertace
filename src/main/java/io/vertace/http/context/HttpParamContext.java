package io.vertace.http.context;

import io.vertx.ext.web.RoutingContext;

public class HttpParamContext {

    private RoutingContext vertxRoutingContext;

    public HttpParamContext(RoutingContext routingContext) {
        this.vertxRoutingContext = routingContext;
    }

}
