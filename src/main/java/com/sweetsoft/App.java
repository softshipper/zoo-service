package com.sweetsoft;

import io.reactivex.Single;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;

public class App {

  private static Vertx vertx = Vertx.vertx();


  public static void main(String[] args) {

    Single<HttpServer> single = vertx
      .createHttpServer()
      .requestHandler(req -> {
        req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
      })
      .rxListen(8000, "0.0.0.0");

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
