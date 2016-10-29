package io.vertace.core.factory;

import io.vertace.PackageScope;
import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceVerticle;
import io.vertace.http.HttpRestRouter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VertaceVerticleFactory implements Factory<VertaceVerticle> {

    //private static Map<Class<? extends VertaceVerticle>, List<Clas>>

    @Override
    public void initialize() {

    }

    public void createArchetypeInstance(Class<? extends VertaceVerticle> vertaceVerticleClass) {
        PackageScope psa = vertaceVerticleClass.getAnnotation(PackageScope.class);
        if(psa == null) return;
        for(String pkg : psa.value()) {
            try {
                for(String cname : VertaceClassLoader.listOfClassNames(pkg)) {
                    Class cls = Class.forName(cname);
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
