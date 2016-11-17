package io.vertace;

import io.vertace.core.Component;
import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceException;
import io.vertace.core.VertaceLifecycle;
import io.vertace.core.VertaceVerticle;
import io.vertace.core.factory.Factory;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Vertace extends VertaceVerticle implements VertaceLifecycle {

    public enum LifeCycleState {BOOTSTRAP, REGISTER, INITIALIZE, DEPLOY}

    private String[] args;
    protected VertaceLifecycle vertaceLifecycle;
    private LifeCycleState currentState;
    private final Map<Class<? extends Component>, Factory> componentFactoriesMap = new HashMap<>();

    public Vertace() {
        this.vertaceLifecycle = new VertaceLifecycle() {
        };
    }

    private void _bootstrap() throws VertaceException {
        currentState = LifeCycleState.BOOTSTRAP;
        onBootstrap();
    }

    private void _register() {
        currentState = LifeCycleState.REGISTER;
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
        onRegister();
    }

    @SuppressWarnings("unchecked")
    private void _register(Class<?> clazz) {
        getComponentClasses().forEach(ac -> {
            if(ac.isAssignableFrom(clazz)) {
                Factory factory = componentFactoriesMap.get(ac);
                factory.registerComponent(clazz);
            }
        });
    }

    private void _initialize() {
        currentState = LifeCycleState.INITIALIZE;
        getComponentClasses().forEach(ac -> {
            Factory factory = componentFactoriesMap.get(ac);
            factory.initialize();
        });
        onInitialize();
    }

    public <C extends Component> void registerFactory(Class<C> componentClass,
                                    Factory<C> factoryObject) throws VertaceException {
        if(!LifeCycleState.BOOTSTRAP.equals(currentState))
            throw new VertaceException("Register Factory is possible only in Bootstrap lifecycle");
        componentFactoriesMap.put(componentClass, factoryObject);
    }

    public Set<Class<? extends Component>> getComponentClasses() {
        return componentFactoriesMap.keySet();
    }

    public static void deploy(Vertace vertaceApp) throws VertaceException {
        vertaceApp.currentState = LifeCycleState.DEPLOY;
        vertaceApp._bootstrap();
        vertaceApp._register();
        vertaceApp._initialize();
        Vertx.vertx().deployVerticle(vertaceApp);
        vertaceApp.onDeploy();
    }

    public static void deploy(Vertace vertaceApp, String... args) throws VertaceException {
        vertaceApp.args = args;
        deploy(vertaceApp);
    }

}
