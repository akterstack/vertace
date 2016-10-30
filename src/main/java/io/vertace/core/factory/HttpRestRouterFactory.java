package io.vertace.core.factory;

import io.vertace.http.HttpRestRouter;

public class HttpRestRouterFactory implements Factory<HttpRestRouter> {

    @Override
    public Class<HttpRestRouter> factoryFor() {
        return HttpRestRouter.class;
    }

    @Override
    public void initialize() {

    }

    public void createArchetypeInstance(Class<? extends HttpRestRouter> clazz) {

    }

    @Override
    public HttpRestRouter instanceOf(Class<? extends HttpRestRouter> archClass) {
        return null;
    }
}
