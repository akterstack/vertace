package io.vertace.core.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractFactory<T> implements Factory<T> {

    private Map<Class<T>, T> mapOfArtifactClassAndObject = new HashMap<>();

    public abstract Class<T> factoryFor();

    @Override
    public void register(Class<T> t) {
        if(! factoryFor().isAssignableFrom(t))
            System.out.println("Cannot assign artifact: " + t.getName());
        mapOfArtifactClassAndObject.put(t, null);
    }

    @Override
    public void initialize() {
        getAllArtifactClasses()
                .forEach(ac ->
                        mapOfArtifactClassAndObject.put(ac, create(ac)));
    }

    @Override
    public abstract T create(Class<T> t);

    @Override
    public T instanceOf(Class<? extends T> archClass) {
        return null;
    }

    @Override
    public Set<Class<T>> getAllArtifactClasses() {
        return mapOfArtifactClassAndObject.keySet();
    }

    @Override
    public List<T> getAllArtifactObjects() {
        return (List<T>)mapOfArtifactClassAndObject.values();
    }
}
