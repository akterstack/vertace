package io.vertace.core.factory;

import java.util.List;
import java.util.Set;

public interface Factory<C> {

    Class<C> factoryFor();

    void registerComponent(Class<? extends C> c);

    void initialize();

    Set<Class<? extends C>> getAllComponentClasses();

    List<? extends C> getAllComponentObjects();

}
