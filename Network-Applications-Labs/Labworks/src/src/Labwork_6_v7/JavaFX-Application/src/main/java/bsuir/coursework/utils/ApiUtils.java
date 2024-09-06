package bsuir.coursework.utils;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;
import javafx.application.Platform;

public class ApiUtils {

  public static HttpRequest loadData(String url) {
    return HttpRequest
      .newBuilder()
      .uri(URI.create(url))
      .GET()
      .header("Content-Type", "application/json")
      .build();
  }

  public static void sendHttpRequestAsync(
    HttpRequest request,
    Consumer<String> responseHandler
  ) {
    HttpClient client = HttpClient.newHttpClient();

    client
      .sendAsync(request, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenAccept(jsonResponse ->
        Platform.runLater(() -> responseHandler.accept(jsonResponse))
      );
  }

  public static void setData(Object object, String url) {
    Gson gson = new Gson();
    String json = gson.toJson(object);
    HttpRequest request = HttpRequest
      .newBuilder()
      .uri(URI.create(url))
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(json))
      .build();

    sendHttpRequestAsync(request, response -> {});
  }

  public static void updateData(Object object, String url) {
    Gson gson = new Gson();
    String json = gson.toJson(object);
    HttpRequest request = HttpRequest
      .newBuilder()
      .uri(URI.create(url))
      .header("Content-Type", "application/json")
      .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
      .build();

    sendHttpRequestAsync(request, response -> {});
  }

  public static void deleteData(String url) {
    HttpRequest request = HttpRequest
      .newBuilder()
      .uri(URI.create(url))
      .header("Content-Type", "application/json")
      .DELETE()
      .build();
    sendHttpRequestAsync(request, response -> {});
  }
}
