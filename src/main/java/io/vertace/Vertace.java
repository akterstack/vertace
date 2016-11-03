package io.vertace;

import io.vertace.core.VertaceClassLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Vertace {

    private static String[] arguments;
    private static Class<? extends Vertace> vertaceAppClass;
    private static List<Class> loadedClasses = new ArrayList<>();

    public static void run(Class<? extends Vertace> vertaceClass, String[] args) {
        vertaceAppClass = vertaceClass;
        arguments = args;

        loadClasses();
    }

    private static void loadClasses() {
        PackageScope packageScope = vertaceAppClass.getAnnotation(PackageScope.class);
        if(packageScope == null) return;

        for(String pkg : packageScope.value()) {
            try {
                for(String cname : VertaceClassLoader.listOfClassNames(pkg)) {
                    try {
                        loadedClasses.add(Class.forName(cname));
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}
