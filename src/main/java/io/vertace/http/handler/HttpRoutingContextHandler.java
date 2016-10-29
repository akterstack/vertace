package io.vertace.http.handler;

import io.vertx.ext.web.RoutingContext;

public interface HttpRoutingContextHandler extends HttpRouteHandler {

    void handle(RoutingContext rctx);

}
