package io.vertace.core.factory;

import java.util.List;
import java.util.Set;

public interface Factory<T> {

    Class<T> factoryFor();

    void register(Class<T> t);

    void initialize();

    T create(Class<T> t);

    T instanceOf(Class<? extends T> archClass);

    Set<Class<T>> getAllArtifactClasses();

    List<T> getAllArtifactObjects();

}
