package io.vertace.legacy.http.handler;

import io.vertx.ext.web.RoutingContext;

public interface HttpRoutingContextHandler extends HttpRouteHandler {

    void handle(RoutingContext rctx);

}
