package io.vertace.core;

import io.hackable.Hackable;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public abstract class VertaceVerticle<T> extends AbstractVerticle implements Hackable {

    @Override
    public void start(Future<Void> future) {
        trigger("deployVerticle", this.getClass(), vertx);

        //if(future.)
    }

}
