package io.vertace;

public class Relay<T> {

    private T t;

    Relay(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

}
