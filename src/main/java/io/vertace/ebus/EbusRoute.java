package io.vertace.ebus;

public final class EbusRoute {

private String namespace;
private EbusRouteHandler ebusRouteHandler;

    public EbusRoute(String namespace, EbusRouteHandler ebusRouteHandler) {
        this.namespace = namespace;
        this.ebusRouteHandler = ebusRouteHandler;
    }
}
