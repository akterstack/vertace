package io.vertace;

import io.vertace.core.Component;
import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceException;
import io.vertace.core.VertaceVerticle;
import io.vertace.core.factory.Factory;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class Vertace extends VertaceVerticle {

    private String[] args;
    private final Map<Class<? extends Component>, Factory> componentFactoriesMap = new LinkedHashMap<>();

    public static void deploy(Vertace vertaceApp, String... args) throws VertaceException {
        vertaceApp.args = args;
        deploy(vertaceApp);
    }

    public static void deploy(Vertace vertaceApp) throws VertaceException {
        Vertx.vertx().deployVerticle(vertaceApp);
    }

    @Override
    protected void register(Future<Void> future) {
        PackageScope packageScope = this.getClass().getAnnotation(PackageScope.class);
        if(packageScope == null) return;

        for(String pkg : packageScope.value()) {
            try {
                for(String cname : VertaceClassLoader.listOfClassNames(pkg)) {
                    try {
                        Class<?> c = Class.forName(cname);
                        _register(c);
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        getComponentClasses().forEach(ac -> {
            Set<Class<?>> classes = componentFactoriesMap.get(ac).getAllComponentClasses();
            System.out.println("\nRegistered " + ac.getSimpleName() + ": " + classes.size());
            classes.forEach(System.out::println);
        });
    }

    @SuppressWarnings("unchecked")
    private void _register(Class<?> clazz) {
        getComponentClasses().forEach(cc -> {
            if(cc.isAssignableFrom(clazz)) {
                Factory factory = componentFactoriesMap.get(cc);
                factory.registerComponent(clazz);
            }
        });
    }

    @Override
    protected void initialize(Future<Void> future) {
        getComponentClasses().forEach(ac -> {
            Factory factory = componentFactoriesMap.get(ac);
            factory.initialize();
        });
    }

    public <C extends Component> void registerFactory(Factory<C> factoryObject) throws VertaceException {
        if(!LifecycleState.BOOTSTRAP.equals(currentLifecycleState))
            throw new VertaceException("Register Factory is possible only in Bootstrap lifecycle");
        componentFactoriesMap.put(factoryObject.factoryFor(), factoryObject);
    }

    public Set<Class<? extends Component>> getComponentClasses() {
        return componentFactoriesMap.keySet();
    }

}
