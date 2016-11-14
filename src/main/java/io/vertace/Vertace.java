package io.vertace;

import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceVerticle;
import io.vertace.core.factory.Factory;
import io.vertace.core.factory.HttpRestRouterFactory;
import io.vertace.core.factory.VertaceVerticleFactory;
import io.vertace.http.HttpRestRouter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Vertace extends VertaceVerticle {

    private String[] args;
    private static Class<? extends Vertace> vertaceAppClass;
    private static Map<Class<?>, Factory> factoryOfArtifactsMap;
    private static Set<Class<?>> artifacts = new HashSet<Class<?>>() {{
        add(VertaceVerticle.class);
        add(HttpRestRouter.class);
    }};

    public void run(String... args) {
        this.args = args;

        /* Lifecycle methods */
        bootstrap();
        register();
        _initialize();
    }

    private static void bootstrap() {
        factoryOfArtifactsMap = new HashMap<Class<?>, Factory>() {{
            put(VertaceVerticle.class, new VertaceVerticleFactory());
            put(HttpRestRouter.class, new HttpRestRouterFactory());
        }};

    }

    private static void register() {
        PackageScope packageScope = vertaceAppClass.getAnnotation(PackageScope.class);
        if(packageScope == null) return;

        for(String pkg : packageScope.value()) {
            try {
                for(String cname : VertaceClassLoader.listOfClassNames(pkg)) {
                    try {
                        Class<?> c = Class.forName(cname);
                        register(c);
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        artifacts.forEach(ac -> {
            Set<Class<?>> classes = factoryOfArtifactsMap.get(ac).getAllArtifactClasses();
            System.out.println("\nRegistered " + ac.getSimpleName() + ": " + classes.size());
            classes.forEach(System.out::println);
        });
    }

    @SuppressWarnings("unchecked")
    private static void register(Class<?> clazz) {
        artifacts.forEach(ac -> {
            if(ac.isAssignableFrom(clazz)) {
                Factory factory = factoryOfArtifactsMap.get(ac);
                factory.register(clazz);
            }
        });
    }

    private static void _initialize() {
        artifacts.forEach(ac -> {
            Factory factory = factoryOfArtifactsMap.get(ac);
            factory.initialize();
        });
    }

}
