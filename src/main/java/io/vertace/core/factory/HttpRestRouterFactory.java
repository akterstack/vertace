package io.vertace.core.factory;

import io.vertace.VertaceWeb;
import io.vertace.http.HttpRestRouter;

public class HttpRestRouterFactory extends AbstractFactory<HttpRestRouter> {

    public HttpRestRouterFactory(VertaceWeb vertace) {
        super(vertace);
    }

    @Override
    public Class<HttpRestRouter> factoryFor() {
        return HttpRestRouter.class;
    }

    @Override
    public HttpRestRouter initialize(Class<? extends HttpRestRouter> c) {
        try {
            HttpRestRouter<?> hrr = c.newInstance();
            hrr.initialize();
            return hrr;
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
