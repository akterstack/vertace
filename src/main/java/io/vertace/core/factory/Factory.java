package io.vertace.core.factory;

import java.util.List;
import java.util.Set;

public interface Factory<T> {

    Class<T> factoryFor();

    void register(Class<? extends T> t);

    void initialize();

    T initialize(Class<? extends T> t);

    Set<Class<? extends T>> getAllArtifactClasses();

    List<? extends T> getAllArtifactObjects();

}
