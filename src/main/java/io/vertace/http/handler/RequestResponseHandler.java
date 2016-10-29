package io.vertace.http.handler;

import io.vertace.http.context.RequestContext;
import io.vertace.http.context.ResponseContext;

public interface RequestResponseHandler extends HttpRouteHandler {

    void handle(final RequestContext req, final ResponseContext res);

}
