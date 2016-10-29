package io.vertace.core;

import io.vertace.core.factory.Factory;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private static Map<Class<? extends Factory>, Factory> mapOfFactories = new HashMap<>();

    // Suppresses default constructor, ensuring non-instantiability.
    private Registry() {
    }

    public static void createFactory(Class<? extends Factory> clazz) {
        try {
            Factory<?> factory = clazz.newInstance();
            mapOfFactories.put(clazz, factory);
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Factory factoryOf(Class<? extends Factory> clazz) {
        return mapOfFactories.get(clazz);
    }

}
