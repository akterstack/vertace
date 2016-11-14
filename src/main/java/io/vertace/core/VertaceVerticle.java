package io.vertace.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import static io.hackable.Hackable.*;

public abstract class VertaceVerticle extends AbstractVerticle {

    @Override
    public final void start(Future<Void> future) throws Exception {
        trigger("beforeBootstrapVerticle", this.getClass(), vertx);
        bootstrap(future);
        trigger("beforeInitializeVerticle", this.getClass(), vertx);
        initialize(future);
        trigger("beforeDeployVerticle", this.getClass(), vertx);
        deploy(future);
    }

    @Override
    public final void stop(Future<Void> future) throws Exception {
        trigger("beforeUndeployVerticle", this.getClass(), vertx);
        undeploy(future);
    }

    protected void bootstrap(Future<Void> future) {

    }

    protected void initialize(Future<Void> future) {
        initialize();
    }

    protected void initialize() {
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
