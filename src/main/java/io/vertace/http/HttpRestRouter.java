package io.vertace.http;

import io.vertace.http.handler.HttpParamHandler;
import io.vertace.http.handler.HttpRouteHandler;
import io.vertace.http.handler.RequestResponseHandler;
import io.vertx.core.http.HttpMethod;

import java.util.LinkedList;
import java.util.List;

public abstract class HttpRestRouter<R> {

    private String mountPoint;
    private List<HttpRoute> listOfHttpRoutes = new LinkedList<>();

    public void initialize() {
        listOfHttpRoutes.forEach(HttpRoute::register);
    }

    protected R subRouteOf(String mountPoint) {
        this.mountPoint = mountPoint;
        return (R)this;
    }

    protected R get(String path, RequestResponseHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return (R)this;
    }

    protected R get(String path, HttpParamHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return (R)this;
    }

    protected R route(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        registerHttpRoute(path, httpMethod, handler);
        return (R)this;
    }

    private void registerHttpRoute(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        listOfHttpRoutes
                .add(new HttpRoute(
                        mountPoint == null ? path : mountPoint + path,
                        httpMethod,
                        handler));
    }

}
