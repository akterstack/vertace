package io.vertace.core;

public interface VertaceLifecycle {

    default void onBootstrap() throws VertaceException {}
    default void onRegister() {}
    default void onInitialize() {}
    default void onDeploy() {}

}
