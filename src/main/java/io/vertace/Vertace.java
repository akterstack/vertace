package io.vertace;

import io.hackable.Filter;
import io.hackable.Hackable;
import io.hackable.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Vertace implements Hackable<VertaceHooks> {

  static void run(Vertace... vertaceApps) {
    for(Vertace vertaceApp : vertaceApps) {

    }
  }

  private static final Map<String, List<Handler<?>>> actionHandlersHostMap = new HashMap<>();
  private static final Map<String, List<Filter<?>>> filterHandlersHostMap = new HashMap<>();

  @Override
  public Map<String, List<Handler<?>>> getActionHandlerHostMap() {
    return actionHandlersHostMap;
  }

  @Override
  public Map<String, List<Filter<?>>> getFilterHandlerHostMap() {
    return filterHandlersHostMap;
  }

  /*@Override
  public void start(Future<Void> startFuture) throws Exception {
    _trigger(deploy.name(), startFuture, (Consumer<Future<Void>>)Future::complete);
  }

  @Override
  public void stop(Future<Void> stopFuture) throws Exception {
    _trigger(undeploy.name(), stopFuture, (Consumer<Future<Void>>)Future::complete);
  }*/

}
