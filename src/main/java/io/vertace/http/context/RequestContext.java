package io.vertace.http.context;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

public class RequestContext {

    private RoutingContext vertxRoutingContext;

    public RequestContext(RoutingContext rctx) {
        this.vertxRoutingContext = rctx;
    }

    public <T> T param(String key) {
        return null;
    }

    public HttpServerRequest vertxRequest() {
        return vertxRoutingContext.request();
    }

}
