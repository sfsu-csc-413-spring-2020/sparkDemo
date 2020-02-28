import static spark.Spark.*;

import builder.ResponseBuilder;
import builder.ResponseDto;
import java.util.Date;
import java.util.Set;
import spark.Request;
import spark.Response;

public class SparkDemo {

  public static String processRoute(Request req, Response res) {
    Set<String> params = req.queryParams();
    for (String param : params) {
      // possible for query param to be an array
      System.out.println(param + " : " + req.queryParamsValues(param)[0]);
    }
    // do stuff with a mapped version http://javadoc.io/doc/com.sparkjava/spark-core/2.8.0
    // http://sparkjava.com/documentation#query-maps
    // print the id query value
    System.out.println(req.queryMap().get("id").value());
    return "done!";
  }

  public static void main(String[] args) {
    port(1234);
    // calling get will make your app start listening for the GET path with the /hello endpoint
    ResponseBuilder builder = new ResponseBuilder()
      .setDate(new Date())
      .setName("Hello world");
    ResponseDto dto = builder.build();
    get("/hello", (req, res) -> "Hello World");

    post("/post-handler", (req, res) -> {
      System.out.print(req.headers());
      return "This is a post handler";
    });

    // Slightly more advanced routing
    path("/api", () -> {
      get("/users", (req, res) -> {
        // put more stuff here
        return "This one has a block body";
      });
      get("/test", (req, res) -> {
        System.out.println(req.headers());
        System.out.println(req.userAgent());
        return "Worked!";
      });
      get("/posts", SparkDemo::processRoute);
      get("/lambda", (req, res) -> SparkDemo.processRoute(req, res));
    });
  }
}
