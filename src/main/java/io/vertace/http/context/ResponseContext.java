package io.vertace.http.context;

import io.vertx.ext.web.RoutingContext;

public class ResponseContext {

    private RoutingContext vertxRoutingContext;

    public ResponseContext(RoutingContext routingContext) {
        this.vertxRoutingContext = routingContext;
    }

    public ResponseContext header(String name, String value) {
        vertxRoutingContext.response().putHeader(name, value);
        return this;
    }

    public ResponseContext header(CharSequence name, CharSequence value) {
        vertxRoutingContext.response().putHeader(name, value);
        return this;
    }

    public ResponseContext header(String name, Iterable<String> values) {
        vertxRoutingContext.response().putHeader(name, values);
        return this;
    }

    public ResponseContext header(CharSequence name, Iterable<CharSequence> values) {
        vertxRoutingContext.response().putHeader(name, values);
        return this;
    }

    public void done(String value) {
        vertxRoutingContext.response().end(value);
    }


}
