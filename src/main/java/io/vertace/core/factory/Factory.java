package io.vertace.core.factory;

public interface Factory<A> {

    void initialize();

    void createArchetypeInstance(Class<? extends A> archetypeClass);

    A instanceOf(Class<? extends A> archClass);

    Class<A> factoryFor();
}
