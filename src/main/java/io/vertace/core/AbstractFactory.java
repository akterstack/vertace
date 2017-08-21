package io.vertace.core;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFactory<T extends Component> implements Factory<T> {

  private final Map<String, T> componentHolder = new HashMap<>();

  public void init(Class<T>... componentClasses) {
    for(Class<T> componentClass : componentClasses) {
      componentHolder.put(getComponentHostKey(componentClass), createComponent(componentClass));
    }
  }

  protected abstract T createComponent(Class<T> componentClass) throws IllegalAccessException, InstantiationException;

  protected String getComponentHostKey(Class<T> componentClass) {
    return componentClass.getName();
  }

  public T getComponent(Class<T> componentClass) {
    return componentHolder.get(getComponentHostKey(componentClass));
  }

}
