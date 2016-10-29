package io.vertace.http.handler;


import io.vertace.http.context.HttpParamContext;

public interface HttpParamHandler<R> extends HttpRouteHandler {

    R handle(HttpParamContext param);

}
