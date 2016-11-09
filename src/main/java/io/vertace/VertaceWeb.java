package io.vertace;

import io.vertace.core.VertaceVerticle;

public abstract class VertaceWeb extends VertaceVerticle {

    private String[] args;

    public VertaceWeb run(String... args) {
        this.args = args;
        return this;
    }

    public void bootstrap() {

    }

}
