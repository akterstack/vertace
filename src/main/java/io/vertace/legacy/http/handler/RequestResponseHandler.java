package io.vertace.legacy.http.handler;

import io.vertace.legacy.http.context.RequestContext;
import io.vertace.legacy.http.context.ResponseContext;

public interface RequestResponseHandler extends HttpRouteHandler {

    void handle(final RequestContext req, final ResponseContext res);

}
