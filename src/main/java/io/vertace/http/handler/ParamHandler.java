package io.vertace.http.handler;


import io.vertace.http.context.ParamContext;

public interface ParamHandler<R> extends HttpRouteHandler {

    R handle(ParamContext param);

}
