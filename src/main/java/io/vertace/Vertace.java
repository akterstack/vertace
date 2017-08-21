package io.vertace;

import io.hackable.Hackable;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.function.Consumer;

import static io.vertace.VertaceHacks.*;

public interface Vertace extends Hackable {

  static void run(Vertace... vertaceApps) {
    for(Vertace vertaceApp : vertaceApps) {

    }
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
