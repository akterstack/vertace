package io.vertace;

import io.vertace.core.VertaceVerticle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Vertace {

    private String[] args;
    private Class<? extends Vertace> vertaceClass;
    private List<Class<? extends VertaceVerticle>> listOfRegisteredVerticles;

    public static void run(Class<? extends Vertace> vertaceAppClass, String[] args) {
        Vertace appVertace = null;
        try {
            appVertace = vertaceAppClass.newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        appVertace.initialize(vertaceAppClass, args);
        List<Class<? extends VertaceVerticle>> loadedVerticles = appVertace.loadVerticles();
        appVertace.registerVerticles(loadedVerticles);
    }

    private void initialize(Class<? extends Vertace> vertaceClass, String[] args) {
        this.args = args;
        this.vertaceClass = vertaceClass;
        this.listOfRegisteredVerticles = new LinkedList<>();
    }

    private List<Class<? extends VertaceVerticle>> loadVerticles() {
        List<Class<? extends VertaceVerticle>> loadedVerticles = new ArrayList<>();
        //TODO: load from appconf and call onLoadVerticles(loadedVerticles)
        onLoadVerticles(loadedVerticles);
        return loadedVerticles;
    }

    /**
     * Lifecycle method
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
     * @param registeredVerticles
     */
    public void onRegisterVerticles(List<Class<? extends VertaceVerticle>> registeredVerticles) {

    }

}
