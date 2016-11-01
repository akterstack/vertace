package io.vertace;

import io.vertace.core.VertaceVerticle;

public abstract class Vertace {

    private static String[] arguments;
    private static Class<? extends VertaceVerticle> vertaceAppClass;

    public static void run(Class<? extends VertaceVerticle> vertaceVerticleClass, String[] args) {
        vertaceAppClass = vertaceVerticleClass;
        arguments = args;
    }

}
