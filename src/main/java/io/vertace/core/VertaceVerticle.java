package io.vertace.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public abstract class VertaceVerticle extends AbstractVerticle implements VertaceLifecycle {

    public enum LifecycleState {BOOTSTRAP, REGISTER, INITIALIZE, DEPLOY, UNDEPLOY}
    protected LifecycleState currentLifecycleState;

    @Override
    public final void start(Future<Void> future) throws VertaceException {
        bootstrap(future);
        register(future);
        initialize(future);
        deploy(future);
    }

    @Override
    public final void stop(Future<Void> future) throws VertaceException {
        undeploy(future);
    }

    protected void bootstrap(Future<Void> future) throws VertaceException {
        currentLifecycleState = LifecycleState.BOOTSTRAP;
        bootstrap();
    }

    protected void register(Future<Void> future) throws VertaceException {
        currentLifecycleState = LifecycleState.REGISTER;
        register();
    }

    protected void initialize(Future<Void> future) {
        currentLifecycleState = LifecycleState.INITIALIZE;
        initialize();
    }

    protected void deploy(Future<Void> future) {
        currentLifecycleState = LifecycleState.DEPLOY;
        deploy();
        future.complete();
    }

    protected void undeploy(Future<Void> future) {
        currentLifecycleState = LifecycleState.UNDEPLOY;
        undeploy();
        future.complete();
    }

}
