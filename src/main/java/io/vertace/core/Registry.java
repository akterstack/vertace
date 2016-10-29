package io.vertace.core;

import io.vertace.core.factory.Factory;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private static Map<Class, Factory> mapOfFactories = new HashMap<>();

    // Suppresses default constructor, ensuring non-instantiability.
    private Registry() {
    }

    public static void createFactory(Class<? extends Factory> clazz) {
        try {
            Factory<?> factory = clazz.newInstance();
            mapOfFactories.put(factory.factoryFor(), factory);
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Factory factoryOf(Class clazz) {
        return mapOfFactories.get(clazz);
    }

}
