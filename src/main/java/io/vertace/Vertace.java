package io.vertace;

import io.vertace.core.VertaceClassLoader;
import io.vertace.core.VertaceVerticle;
import io.vertace.http.HttpRestRouter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Vertace {

    private static String[] arguments;
    private static Class<? extends Vertace> vertaceAppClass;
    private static List<Class> loadedClasses = new ArrayList<>();
    private static Map<Class<?>, List<Class<?>>> registeredArtifacts = new HashMap<>();
    private static List<Class<? extends VertaceVerticle>> registeredVertaceVerticles = new ArrayList<>();
    private static List<Class<? extends HttpRestRouter>> registeredVertaceRouters = new ArrayList<>();

    public static void run(Class<? extends Vertace> vertaceClass, String[] args) {
        vertaceAppClass = vertaceClass;
        arguments = args;

        loadClasses();
        register();
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
        System.out.println("Loaded Classes:");
        loadedClasses.forEach(System.out::println);
    }

    private static void register() {
        Arrays.asList(
                VertaceVerticle.class,
                HttpRestRouter.class
        ).forEach(Vertace::register);
    }

    private static void register(Class<?> parent) {
        List<Class<?>> classes = loadedClasses.stream()
                .filter(parent::isAssignableFrom)
                .map(c -> (Class<? extends VertaceVerticle>)c)
                .collect(Collectors.toList());
        registeredArtifacts.put(parent, classes);
        System.out.println("\nRegistered " + parent.getSimpleName() + ": " + classes.size());
        classes.forEach(System.out::println);
    }

}
