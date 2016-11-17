package io.vertace;

import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public abstract class VertaceWeb extends Vertace {

    private String[] args;
    private Router router;
    private HttpServer httpServer;

    public String host() {
        return null;
    }

    public int port() {
        return 7007;
    }

    @Override
    protected void deploy(Future<Void> future) {
        router = Router.router(vertx);
        httpServer = vertx.createHttpServer()
                .requestHandler(router::accept);
        httpServer = host() == null || host().isEmpty() ?
                httpServer.listen(port()) : httpServer.listen(port(), host());
        System.out.println("Server running in port: " + port());
    }

    public HttpServer getVertxHttpServer() {
        return httpServer;
    }

}
