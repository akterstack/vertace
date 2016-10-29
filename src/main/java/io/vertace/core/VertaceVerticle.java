package io.vertace.core;

import io.vertace.PackageScope;
import io.vertace.core.factory.VertaceVerticleFactory;
import io.vertx.core.AbstractVerticle;

import java.io.IOException;

public abstract class VertaceVerticle<T> extends AbstractVerticle {

    private VertaceVerticleFactory vertaceVerticleFactory = new VertaceVerticleFactory();

    public VertaceVerticle() {
        PackageScope psa = getClass().getAnnotation(PackageScope.class);
        if(psa == null) return;
        for(String pkg : psa.value()) {
            try {
                for(String clsnm : VertaceClassLoader.listOfClassNames(pkg)) {
                    Class cls = Class.forName(clsnm);
                    vertaceVerticleFactory.create(cls);
                }
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
