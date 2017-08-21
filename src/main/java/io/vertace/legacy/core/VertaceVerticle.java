package io.vertace.legacy.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public abstract class VertaceVerticle extends AbstractVerticle implements VertaceLifecycle {

    public enum LifecycleState {BOOTSTRAP, REGISTER, INITIALIZE, DEPLOY, UNDEPLOY}
    protected LifecycleState currentLifecycleState;

    @Override
    public final void start(Future<Void> future) throws VertaceException {
        _bootstrap(future);
        _register(future);
        _initialize(future);
        _deploy(future);
    }

    @Override
    public final void stop(Future<Void> future) throws VertaceException {
        undeploy(future);
    }

    protected void _bootstrap(Future<Void> future) throws VertaceException {
        currentLifecycleState = LifecycleState.BOOTSTRAP;
        bootstrap(future);
        bootstrap();
    }

    protected void bootstrap(Future<Void> future) throws VertaceException {
    }

    protected void _register(Future<Void> future) throws VertaceException {
        currentLifecycleState = LifecycleState.REGISTER;
        register(future);
        register();
    }

    protected void register(Future<Void> future) throws VertaceException {
    }

    protected void _initialize(Future<Void> future) {
        currentLifecycleState = LifecycleState.INITIALIZE;
        initialize(future);
        initialize();
    }

    protected void initialize(Future<Void> future) {
    }

    protected void _deploy(Future<Void> future) {
        currentLifecycleState = LifecycleState.DEPLOY;
        deploy(future);
        deploy();
        future.complete();
    }

    protected void deploy(Future<Void> future) {
    }

    protected void _undeploy(Future<Void> future) {
        currentLifecycleState = LifecycleState.UNDEPLOY;
        undeploy(future);
        undeploy();
        future.complete();
    }

    protected void undeploy(Future<Void> future) {
    }

}
