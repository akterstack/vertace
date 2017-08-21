package io.vertace.legacy.http;

import io.vertx.ext.web.RoutingContext;

public interface RoutingCriteria {

    void criteria(RoutingContext rctx, Object val);

}
