package io.vertace.core.factory;

import io.vertace.Vertace;
import io.vertace.core.VertaceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractFactory<C> implements Factory<C> {

    private Vertace vertace;
    private final Map<Class<? extends C>, C> mapOfArtifactClassAndObject = new HashMap<>();

    public AbstractFactory(Vertace vertace) {
        this.vertace = vertace;
    }

    public abstract Class<C> factoryFor();

    @Override
    public void registerComponent(Class<? extends C> c) {
        if(! factoryFor().isAssignableFrom(c))
            System.out.println("Cannot assign artifact: " + c.getName());
        System.out.println(c);
        mapOfArtifactClassAndObject.put(c, null);
    }

    @Override
    public void initialize() {
        getAllComponentClasses()
                .forEach(cc -> {
                    try {
                        mapOfArtifactClassAndObject.put(cc, initialize(cc));
                    } catch(VertaceException e) {
                        e.printStackTrace();
                    }
                });
    }

    public C initialize(Class<? extends C> c) throws VertaceException {
        //TODO change exception
        throw new VertaceException("must be implement this method or override initialize() method");
    }

    @Override
    public Set<Class<? extends C>> getAllComponentClasses() {
        return mapOfArtifactClassAndObject.keySet();
    }

    @Override
    public List<C> getAllComponentObjects() {
        return (List<C>)mapOfArtifactClassAndObject.values();
    }

    @Override
    public Vertace vertace() {
        return vertace;
    }
}
