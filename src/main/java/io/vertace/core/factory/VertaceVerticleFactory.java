package io.vertace.core.factory;

import io.vertace.PackageScope;
import io.vertace.Vertace;
import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceVerticle;

import java.io.IOException;

public class VertaceVerticleFactory extends AbstractFactory<VertaceVerticle> {

    //private static

    @Override
    public Class<VertaceVerticle> factoryFor() {
        return VertaceVerticle.class;
    }

    @Override
    public VertaceVerticle create(Class<VertaceVerticle> vertaceVerticleClass) {
        PackageScope psa = vertaceVerticleClass.getAnnotation(PackageScope.class);
        if(psa == null) return null;
        for(String pkg : psa.value()) {
            try {
                for(String cname : VertaceClassLoader.listOfClassNames(pkg)) {
                    Class cls = Class.forName(cname);

                }
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
