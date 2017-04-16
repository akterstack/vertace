package io.vertace;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;

public class EbusClient {

    private final Vertx vertx;
    private final String address;
    private Object message;

    private EbusClient(Vertx vertx, String address) {
        this.vertx = vertx;
        this.address = address;
    }

    public static EbusClient of(String address) {
        return of(address, Vertx.vertx());
    }

    public static EbusClient of(String address, Vertx vertx) {
        return new EbusClient(vertx, address);
    }

    public EbusClient data(Object msg) {
        this.message = msg;
        return this;
    }

    public <R> void send(Handler<AsyncResult<Message<R>>> replyHandler) {
        vertx.eventBus().send(address, message, replyHandler);
    }


}
