package com.sweetsoft;

import io.reactivex.Single;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Route;
import io.vertx.reactivex.ext.web.Router;

public class App {

  private static Vertx vertx = Vertx.vertx();
  private static Router router = Router.router(vertx);

  private static void buildGreetRoute(Router router) {

    Route route = router.route().path("/greet");
    route.handler(routingContext -> {

      HttpServerResponse response = routingContext.response();
      response.end("Greet from zoo service!!!");

    });

  }


  public static void main(String[] args) {

    buildGreetRoute(router);

    Single<HttpServer> single = vertx
      .createHttpServer()
      .requestHandler(router)
      .rxListen(8080, "0.0.0.0");

    single.
      subscribe(
        (server) -> {
          System.out.println("The server is listen on port: " + server.actualPort());
          // Server is closed
        },
        failure -> {
          System.out.println(failure.getMessage());
          // Server closed but encoutered issue
        }
      );
  }

}
