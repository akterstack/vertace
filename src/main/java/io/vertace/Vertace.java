package io.vertace;

import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceVerticle;
import io.vertace.core.factory.Factory;
import io.vertace.core.factory.HttpRestRouterFactory;
import io.vertace.core.factory.VertaceVerticleFactory;
import io.vertace.http.HttpRestRouter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Vertace {

    private static String[] args;
    private static Class<? extends Vertace> vertaceAppClass;
    private static List<Class<?>> loadedClasses;
    private static Map<Class<?>, Factory> factoryOfArtifactsMap;
    private static Set<Class<?>> artifacts = new HashSet<Class<?>>() {{
        add(VertaceVerticle.class);
        add(HttpRestRouter.class);
    }};

    public static void run(Class<? extends Vertace> vertaceClass, String[] args) {
        vertaceAppClass = vertaceClass;
        Vertace.args = args;

        /* Lifecycle methods */
        bootstrap();
        register();
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

        loadedClasses = new ArrayList<>();
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
                factory.register(ac);
            }
        });
    }

    private static void initialize() {
        artifacts.forEach(Vertace::initialize);
    }

    private static void initialize(Class<?> artifact) {

    }

}
