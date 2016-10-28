package io.vertace.http;

import io.vertace.core.VertaceVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class HttpServerVerticle extends VertaceVerticle {

    private static final int DEFAULT_HTTP_PORT = 7100;

    private Integer port;
    private Router router;
    private HttpServer httpServer;

    public HttpServerVerticle() {
        router = Router.router(vertx);
        port = DEFAULT_HTTP_PORT;
    }

    @Override
    public void start() {
        httpServer = vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen();
        System.out.println("Server running in port: " + port);
    }

    public Router getVertxRouter() {
        return router;
    }

    public HttpServer getVertxHttpServer() {
        return httpServer;
    }

}
