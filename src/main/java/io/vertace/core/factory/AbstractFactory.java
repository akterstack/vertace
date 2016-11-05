package io.vertace.core.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractFactory<T> implements Factory<T> {

    private Map<Class<? extends T>, T> mapOfArtifactClassAndObject = new HashMap<>();

    public abstract Class<T> factoryFor();

    @Override
    public void register(Class<? extends T> t) {
        if(! factoryFor().isAssignableFrom(t))
            System.out.println("Cannot assign artifact: " + t.getName());
        System.out.println(t);
        mapOfArtifactClassAndObject.put(t, null);
    }

    @Override
    public void initialize() {
        getAllArtifactClasses()
                .forEach(ac ->
                        mapOfArtifactClassAndObject.put(ac, initialize(ac)));
    }

    @Override
    public abstract T initialize(Class<? extends T> t);

    @Override
    public Set<Class<? extends T>> getAllArtifactClasses() {
        return mapOfArtifactClassAndObject.keySet();
    }

    @Override
    public List<T> getAllArtifactObjects() {
        return (List<T>)mapOfArtifactClassAndObject.values();
    }
}
