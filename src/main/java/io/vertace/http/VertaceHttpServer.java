package io.vertace.http;

import io.vertace.core.VertaceVerticle;
import io.vertx.core.Future;
import io.vertx.core.VoidHandler;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import java.util.ArrayList;
import java.util.List;

public abstract class VertaceHttpServer extends VertaceVerticle<VertaceHttpServer> {

    private Integer port;
    private Router router;
    private HttpServer httpServer;
    private List<HttpRestRouter> listOfHttpRestRouters = new ArrayList<>();

    public VertaceHttpServer() {
        this.port = port();
    }

    public abstract Integer port();
    public String  host() {
        return null;
    }

    @Override
    public void deploy(Future<Void> future) {
        router = Router.router(vertx);
        httpServer = vertx.createHttpServer()
                .requestHandler(router::accept);
        httpServer = host() == null ? httpServer.listen(port()) : httpServer.listen(port(), host());
        System.out.println("Server running in port: " + port);
    }

    @Override
    public void undeploy(Future<Void> future) {
        httpServer.actualPort();
    }

    public HttpServer getVertxHttpServer() {
        return httpServer;
    }

}
