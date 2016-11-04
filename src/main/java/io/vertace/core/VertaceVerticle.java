package io.vertace.core;

import io.hackable.Hackable;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public abstract class VertaceVerticle<T> extends AbstractVerticle implements Hackable {

    protected void triggerBeforeDeploy(Vertx vertx) {
        trigger("deployVerticle", this.getClass(), vertx);
    }

    protected void triggerBeforeUndeploy(Vertx vertx) {
        trigger("deployVerticle", this.getClass(), vertx);
    }

    @Override
    public void start(Future<Void> future) throws Exception {
        triggerBeforeDeploy(vertx);
        deploy(future);
    }

    @Override
    public void stop(Future<Void> future) throws Exception {
        triggerBeforeUndeploy(vertx);
        undeploy(future);
    }

    protected void deploy(Future<Void> future) {
        deploy();
        future.complete();
    }

    protected void deploy() {

    }

    protected void undeploy(Future<Void> future) {
        undeploy();
        future.complete();
    }

    protected void undeploy() {

    }

}
