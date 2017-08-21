package io.vertace.legacy.http.handler;


import io.vertace.legacy.http.context.ParamContext;

public interface ParamHandler<R> extends HttpRouteHandler {

    R handle(ParamContext param);

}
