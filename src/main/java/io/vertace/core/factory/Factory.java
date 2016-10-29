package io.vertace.core.factory;

public interface Factory<A> {

    void createArchetypeInstance(Class<? extends A> archetypeClass);

}
