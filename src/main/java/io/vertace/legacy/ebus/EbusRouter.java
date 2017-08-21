package io.vertace.legacy.ebus;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class EbusRouter {

    private String mountPoint = ":";
    private List<EbusRoute> ebusRouteList = new LinkedList<>();

    public EbusRouter subRouteOf(String subRoute) {
        Objects.requireNonNull(subRoute, "Sub-route cannot be null");
        if(subRoute.contains(":")) {
            throw new RuntimeException("Namespace should not contain ':'");
        }
        mountPoint = subRoute + ":";
        return this;
    }

    public EbusRouter route(String namespace, EbusRouteHandler handler) {
        registerRoute(namespace, handler);
        return this;
    }

    private void registerRoute(String namespace, EbusRouteHandler handler) {
        if(namespace.contains(":")) {
            throw new RuntimeException("Namespace should not contain ':'");
        }
        ebusRouteList.add(new EbusRoute(mountPoint + namespace, handler));
    }

}
