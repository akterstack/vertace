package io.vertace;

import io.vertace.core.Registry;
import io.vertace.core.VertaceVerticle;
import io.vertace.core.factory.Factory;
import io.vertace.core.factory.HttpRestRouterFactory;
import io.vertace.core.factory.VertaceVerticleFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Vertace {

    private String[] args;
    private Class<? extends Vertace> vertaceAppClass;
    private List<Class<? extends Factory>> listOfFactoryClasses;
    private List<Class<? extends VertaceVerticle>> listOfRegisteredVerticles;

    public static void run(Class<? extends Vertace> vertaceAppClass, String[] args) {
        Vertace appVertace = null;
        try {
            appVertace = vertaceAppClass.newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        appVertace.vertaceAppClass = vertaceAppClass;
        appVertace.args = args;
        appVertace.listOfFactoryClasses = Arrays.asList(
                VertaceVerticleFactory.class,
                HttpRestRouterFactory.class
        );
        appVertace.listOfRegisteredVerticles = new LinkedList<>();
        appVertace.bootstrap();
        appVertace.initialize();
    }

    public void use(Class clazz) {
        if(Factory.class.isAssignableFrom(clazz)) {
            listOfFactoryClasses.add(clazz);
        }//TODO: more usage here
    }

    public void bootstrap() {

    }

    private void initialize() {
        listOfFactoryClasses.forEach(Registry::createFactory);
        List<Class<? extends VertaceVerticle>> loadedVerticles = loadVerticles();
        registerVerticles(loadedVerticles);
        initializeVerticles();
    }

    private List<Class<? extends VertaceVerticle>> loadVerticles() {
        List<Class<? extends VertaceVerticle>> loadedVerticles = new ArrayList<>();
        //TODO: load from appconf and call onLoadVerticles(loadedVerticles)
        onLoadVerticles(loadedVerticles);
        return loadedVerticles;
    }

    /**
     * Lifecycle method
     * todo: should I keep this method in lifecycle?
     * @param loadedVerticles
     */
    public void onLoadVerticles(List<Class<? extends VertaceVerticle>> loadedVerticles) {

    }

    private void registerVerticles(List<Class<? extends VertaceVerticle>> loadedVerticles) {
        onRegisterVerticles(loadedVerticles);
        listOfRegisteredVerticles.addAll(loadedVerticles);
    }

    /**
     * Lifecycle method, override to change
     *
     * @param registeredVerticles
     */
    public void onRegisterVerticles(List<Class<? extends VertaceVerticle>> registeredVerticles) {

    }

    private void initializeVerticles() {
        for(Class<? extends VertaceVerticle> vc : listOfRegisteredVerticles) {
            try {
                vc.newInstance();
            } catch(InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
