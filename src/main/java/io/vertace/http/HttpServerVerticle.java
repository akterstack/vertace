package io.vertace.http;

import io.vertace.core.VertaceVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import java.util.ArrayList;
import java.util.List;

public abstract class HttpServerVerticle extends VertaceVerticle<HttpServerVerticle> {

    private Integer port;
    private Router router;
    private HttpServer httpServer;
    private List<HttpRestRouter> listOfHttpRestRouters = new ArrayList<>();

    public HttpServerVerticle() {
        this.port = port();
    }

    public abstract Integer port();

    @Override
    public void start() {
        router = Router.router(vertx);
        httpServer = vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(port);
        System.out.println("Server running in port: " + port);
    }

    public HttpServer getVertxHttpServer() {
        return httpServer;
    }

}
