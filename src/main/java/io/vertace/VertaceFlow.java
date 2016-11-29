package io.vertace;

import java.util.function.Function;

public class VertaceFlow {

    public static VertaceFlow start(Function function) {
        return new VertaceFlow();
    }

}
