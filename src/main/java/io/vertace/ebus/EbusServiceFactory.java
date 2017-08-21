package io.vertace.ebus;

import io.vertace.core.AbstractFactory;

public class EbusServiceFactory extends AbstractFactory<EbusService> {
  @Override
  protected EbusService createComponent(Class<EbusService> componentClass)
      throws IllegalAccessException, InstantiationException {
    return componentClass.newInstance(); //todo: require perfect initialization
  }
}
