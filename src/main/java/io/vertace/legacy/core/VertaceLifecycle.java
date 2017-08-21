package io.vertace.legacy.core;

public interface VertaceLifecycle {

    default void bootstrap() throws VertaceException {}
    default void register() throws VertaceException {}
    default void initialize() {}
    default void deploy() {}
    default void undeploy() {}

}
