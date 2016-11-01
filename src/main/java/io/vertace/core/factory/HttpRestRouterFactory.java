package io.vertace.core.factory;

import io.vertace.http.HttpRestRouter;

public class HttpRestRouterFactory extends AbstractFactory<HttpRestRouter> {

    @Override
    public Class<HttpRestRouter> factoryOf() {
        return HttpRestRouter.class;
    }

    @Override
    public HttpRestRouter create(Class<HttpRestRouter> t) {
        return null;
    }

}
