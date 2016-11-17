package io.vertace;

import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceVerticle;
import io.vertace.core.factory.Factory;
import io.vertace.core.factory.HttpRestRouterFactory;
import io.vertace.http.HttpRestRouter;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Vertace extends VertaceVerticle {

    private String[] args;
    private Map<Class<?>, Factory> componentFactoriesMap;
    private Set<Class<?>> components = new HashSet<Class<?>>() {{
        add(HttpRestRouter.class);
    }};

    public static void deploy(Vertace vertaceApp) {
        vertaceApp._bootstrap();
        vertaceApp._register();
        vertaceApp._initialize();
        Vertx.vertx().deployVerticle(vertaceApp);
    }

    public static void deploy(Vertace vertaceApp, String... args) {
        vertaceApp.args = args;
        deploy(vertaceApp);
    }

    private void _bootstrap() {
        componentFactoriesMap = new HashMap<Class<?>, Factory>() {{
            put(HttpRestRouter.class, new HttpRestRouterFactory());
        }};
        bootstrap();
    }

    public void bootstrap() {

    }

    public <C> void registerFactory(Class<C> componentClass, Factory<C> factoryObject) {
        componentFactoriesMap.put(componentClass, factoryObject);
    }

    private void _register() {
        PackageScope packageScope = this.getClass().getAnnotation(PackageScope.class);
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

        components.forEach(ac -> {
            Set<Class<?>> classes = componentFactoriesMap.get(ac).getAllComponentClasses();
            System.out.println("\nRegistered " + ac.getSimpleName() + ": " + classes.size());
            classes.forEach(System.out::println);
        });

        register();
    }

    public void register() {

    }

    @SuppressWarnings("unchecked")
    private void register(Class<?> clazz) {
        components.forEach(ac -> {
            if(ac.isAssignableFrom(clazz)) {
                Factory factory = componentFactoriesMap.get(ac);
                factory.registerComponent(clazz);
            }
        });
    }

    private void _initialize() {
        components.forEach(ac -> {
            Factory factory = componentFactoriesMap.get(ac);
            factory.initialize();
        });
    }

}
