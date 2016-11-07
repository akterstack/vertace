package io.vertace.core.factory;

import io.vertace.Vertace;
import io.vertace.core.VertaceVerticle;
import io.vertx.core.Vertx;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VertaceVerticleFactory extends AbstractFactory<VertaceVerticle> {

    //private static

    @Override
    public Class<VertaceVerticle> factoryFor() {
        return VertaceVerticle.class;
    }

    @Override
    public VertaceVerticle initialize(Class<? extends VertaceVerticle> vertaceVerticleClass) {
        try {
            Constructor<? extends VertaceVerticle> constructor = vertaceVerticleClass.getConstructor(Vertace.class);
            VertaceVerticle vv = constructor.newInstance();
            Vertx.vertx().deployVerticle(vv);
            return vv;
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
