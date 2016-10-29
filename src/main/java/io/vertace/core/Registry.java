package io.vertace.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Registry {

    private static List<Class<? extends VertaceVerticle>> listOfvertaceVerticleClass = new ArrayList<>();

    public static void registerVertaceVerticle(Class<? extends VertaceVerticle> vertaceVerticleClass) {
        listOfvertaceVerticleClass.add(vertaceVerticleClass);
    }

    public static List<String> listOfClassNames(String packageName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<String> classNames = new ArrayList<>();
        for (File directory : dirs) {
            classNames.addAll(findClasses(directory, packageName));
        }
        return classNames;
    }

    private static List<String> findClasses(File directory, String packageName) {
        List<String> classNames = new ArrayList<>();
        if (!directory.exists()) {
            return classNames;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classNames.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classNames.add(packageName + '.' + file.getName().replace(".class", ""));
            }
        }
        return classNames;
    }

}
