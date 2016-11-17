package io.vertace.core;

public interface VertaceLifecycle {

    default void bootstrap() {}
    default void register() {}
    default void initialize() {}
    default void deploy() {}

}
