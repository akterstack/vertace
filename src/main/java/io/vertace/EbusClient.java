package io.vertace;

import io.vertx.core.Vertx;

public class EbusClient<R> {

    private final Vertx vertx;
    private final String address;
    private Object message;

    private EbusClient(Vertx vertx, String address) {
        this.vertx = vertx;
        this.address = address;
    }

    public EbusClient of(String address) {
        return of(address, Vertx.vertx());
    }

    public EbusClient of(String address, Vertx vertx) {
        return new EbusClient(vertx, address);
    }

    public EbusClient send(Object msg) {
        this.message = msg;
        return this;
    }


}
