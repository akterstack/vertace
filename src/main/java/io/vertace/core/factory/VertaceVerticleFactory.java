package io.vertace.core.factory;

import io.vertace.core.VertaceVerticle;
import io.vertx.core.Vertx;

public class VertaceVerticleFactory extends AbstractFactory<VertaceVerticle> {

    //private static

    @Override
    public Class<VertaceVerticle> factoryFor() {
        return VertaceVerticle.class;
    }

    @Override
    public VertaceVerticle initialize(Class<? extends VertaceVerticle> vertaceVerticleClass) {
        try {
            VertaceVerticle<?> vv = vertaceVerticleClass.newInstance();
            Vertx.vertx().deployVerticle(vv);
            return vv;
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
