package io.vertace.core.factory;

import io.vertace.http.VertaceWeb;
import io.vertace.http.HttpRestRouter;

public class HttpRestRouterFactory extends AbstractFactory<HttpRestRouter> {

    public HttpRestRouterFactory(VertaceWeb vertaceWeb) {
        super(vertaceWeb);
    }

    @Override
    public Class<HttpRestRouter> factoryFor() {
        return HttpRestRouter.class;
    }

    @Override
    public HttpRestRouter initialize(Class<? extends HttpRestRouter> c) {
        try {
            HttpRestRouter<?> hrr = c.newInstance();
            hrr.initialize((VertaceWeb)vertace());
            return hrr;
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
