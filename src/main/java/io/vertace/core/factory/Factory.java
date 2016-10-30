package io.vertace.core.factory;

import java.util.ArrayList;
import java.util.List;

public interface Factory<T> {

    List<Class> archClasses = new ArrayList<>();

    Class<T> factoryFor();

    void initialize();

    void createArchetypeInstance(Class<? extends T> archetypeClass);

    T instanceOf(Class<? extends T> archClass);

    default void register(Class<T> t) {
        archClasses.add(t);
    }

    default List<Class> getAllArchClasses() {
        return archClasses;
    }
}
