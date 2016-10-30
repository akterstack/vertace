package io.vertace;

import io.vertace.core.VertaceVerticle;
import io.vertace.core.factory.Factory;
import io.vertace.core.factory.HttpRestRouterFactory;
import io.vertace.core.factory.VertaceVerticleFactory;
import io.vertace.http.HttpRestRouter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Vertace {

    private static Vertace vertaceApp;

    private String[] args;
    private Class<? extends Vertace> vertaceAppClass;

    private List<Class<? extends Factory>> listOfFactoryClasses;

    /**
     * Map of factory instance of their corresponding factory handler class
     */
    private Map<Class, Factory> mapOfFactories = new HashMap<>();
    private List<Class<? extends VertaceVerticle>> listOfRegisteredVerticles;

    public static Vertace vertace() {
        if(vertaceApp == null)
            System.out.println("Vertace not initialized."); //TODO: use vertx logger
        return vertaceApp;
    }

    public static void run(Class<? extends Vertace> vertaceAppClass, String[] args) {
        try {
            vertaceApp = vertaceAppClass.newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();//TODO: proper message
            return;
        }
        vertaceApp.vertaceAppClass = vertaceAppClass;
        vertaceApp.args = args;
        vertaceApp.listOfFactoryClasses = Arrays.asList(
                VertaceVerticleFactory.class,
                HttpRestRouterFactory.class
        );
        vertaceApp.listOfRegisteredVerticles = new LinkedList<>();
        vertaceApp.bootstrap();
        vertaceApp.initialize();
    }

    public void use(Class clazz) {
        if(Factory.class.isAssignableFrom(clazz)) {
            listOfFactoryClasses.add(clazz);
        }//TODO: more usage here
    }

    private void initialize() {
        listOfFactoryClasses.forEach(this::createFactory);
        List<Class<? extends VertaceVerticle>> loadedVerticles = loadVerticles();
        registerVerticles(loadedVerticles);
        initializeFactory();
        initializeFactoryLifecycle();
    }

    private List<Class<? extends VertaceVerticle>> loadVerticles() {
        List<Class<? extends VertaceVerticle>> loadedVerticles = new ArrayList<>();
        //TODO: load from appconf and call onLoadVerticles(loadedVerticles)
        onLoadVerticles(loadedVerticles);
        return loadedVerticles;
    }

    private void registerVerticles(List<Class<? extends VertaceVerticle>> loadedVerticles) {
        onRegisterVerticles(loadedVerticles);
        listOfRegisteredVerticles.addAll(loadedVerticles);
    }

    private void initializeFactory() {
        for(Class<? extends Factory> fc : listOfFactoryClasses) {
            createFactory(fc);
        }
    }

    public void createFactory(Class<? extends Factory> clazz) {
        try {
            Factory<?> factory = clazz.newInstance();
            mapOfFactories.put(factory.factoryFor(), factory);
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Factory factoryOf(Class clazz) {
        return vertace().mapOfFactories.get(clazz);
    }

    private void initializeFactoryLifecycle() {

    }

    public void bootstrap() {

    }

    /**
     * Lifecycle method
     * todo: should I keep this method in lifecycle?
     * @param loadedVerticles
     */
    public void onLoadVerticles(List<Class<? extends VertaceVerticle>> loadedVerticles) {

    }

    /**
     * Lifecycle method, override to change
     *
     * @param registeredVerticles
     */
    public void onRegisterVerticles(List<Class<? extends VertaceVerticle>> registeredVerticles) {

    }

}
