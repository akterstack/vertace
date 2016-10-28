package io.vertace;

import io.vertace.core.VertaceVerticle;

import java.util.ArrayList;
import java.util.List;

public abstract class Vertace {

    public static void run(Class<? extends Vertace> vertaceAppClass, String[] args) {

    }

    public List<Class<? extends VertaceVerticle>> registerVerticles() {
        return new ArrayList<>();
    }

}
