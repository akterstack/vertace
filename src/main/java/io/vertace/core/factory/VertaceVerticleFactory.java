package io.vertace.core.factory;

import io.vertace.PackageScope;
import io.vertace.Vertace;
import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceVerticle;

import java.io.IOException;

public class VertaceVerticleFactory implements Factory<VertaceVerticle> {

    //private static

    @Override
    public Class<VertaceVerticle> factoryFor() {
        return VertaceVerticle.class;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void createArchetypeInstance(Class<? extends VertaceVerticle> vertaceVerticleClass) {
        PackageScope psa = vertaceVerticleClass.getAnnotation(PackageScope.class);
        if(psa == null) return;
        for(String pkg : psa.value()) {
            try {
                for(String cname : VertaceClassLoader.listOfClassNames(pkg)) {
                    Class cls = Class.forName(cname);
                    Factory factory = Vertace.factoryOf(cls);
                    factory.register(cls);
                }
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public VertaceVerticle instanceOf(Class<? extends VertaceVerticle> archClass) {
        return null;
    }

}
