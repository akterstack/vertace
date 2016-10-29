package io.vertace;

import io.vertace.core.VertaceVerticle;

import java.util.ArrayList;
import java.util.List;

public abstract class Vertace {

    private String[] args;
    private Class<? extends Vertace> vertaceClass;

    public static void run(Class<? extends Vertace> vertaceAppClass, String[] args) {
        Vertace vertace = null;
        try {
            vertace = vertaceAppClass.newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        vertace.initialize(vertaceAppClass, args);
        vertace._registerVerticles();
    }

    private void initialize(Class<? extends Vertace> vertaceClass, String[] args) {
        this.args = args;
        this.vertaceClass = vertaceClass;
    }

    private List<Class<? extends VertaceVerticle>> _registerVerticles() {
        return registerVerticles();
    }

    public List<Class<? extends VertaceVerticle>> registerVerticles() {
        return new ArrayList<>();
    }

}
