package io.vertace.core.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractFactory<T> implements Factory<T> {

    private Map<Class<T>, T> mapOfArtifactClassAndObject = new HashMap<>();

    public abstract Class<T> factoryOf();

    @Override
    public void register(Class<T> t) {
        mapOfArtifactClassAndObject.put(t, null);
    }

    @Override
    public void initialize() {
        getAllArtifactClasses().forEach(ac -> mapOfArtifactClassAndObject.put(ac, create(ac)));
    }

    @Override
    public abstract T create(Class<T> t);

    @Override
    public T instanceOf(Class<? extends T> archClass) {
        return null;
    }

    @Override
    public Set<Class<T>> getAllArtifactClasses() {
        return null;
    }

    @Override
    public Set<T> getAllArtifactObjects() {
        return null;
    }
}
