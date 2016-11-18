package io.vertace;

import io.vertace.core.VertaceException;
import io.vertace.core.factory.HttpRestRouterFactory;
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
        return 8070;
    }

    @Override
    public void bootstrap(Future<Void> future) throws VertaceException {
        currentLifecycleState = LifecycleState.BOOTSTRAP;
        registerFactory(new HttpRestRouterFactory(this));
        bootstrap();
    }

    @Override
    protected void initialize(Future<Void> future) {
        currentLifecycleState = LifecycleState.INITIALIZE;
        router = Router.router(vertx);
        httpServer = vertx.createHttpServer().requestHandler(router::accept);
        initialize();
    }

    @Override
    protected void deploy(Future<Void> future) {
        httpServer = host() == null || host().isEmpty() ?
                httpServer.listen(port()) : httpServer.listen(port(), host());
        System.out.println("Server running in port: " + port());
    }

    public HttpServer vertxHttpServer() {
        return httpServer;
    }

    public Router vertxRouter() {
        return router;
    }

}
