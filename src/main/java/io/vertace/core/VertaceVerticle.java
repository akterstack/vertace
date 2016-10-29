package io.vertace.core;

import io.vertace.PackageScope;
import io.vertace.core.factory.VertaceVerticleFactory;
import io.vertx.core.AbstractVerticle;

import java.io.IOException;

public abstract class VertaceVerticle<T> extends AbstractVerticle {

    private VertaceVerticleFactory vertaceVerticleFactory = new VertaceVerticleFactory();

    public VertaceVerticle() {

    }

}
