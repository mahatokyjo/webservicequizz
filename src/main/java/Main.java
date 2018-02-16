
import service.*;
import static spark.Spark.before;
import static spark.Spark.options;
import static spark.Spark.port;


public class Main{

    public static void main(String[]args){

        WebServiceQuizz service = new WebServiceQuizz();

        String origin, methods, headers;
        origin = "*";
        methods = "GET,PUT,DELETE,POST,OPTIONS";
        headers = "Access-Control-Allow-Origin, " + "Content-Type,Access-Control-Allow-Headers,Authorization, X-Requested-With";

        port(getHerokuAssignedPort());

        service.inscription();
        service.verifier();
        service.question();
        service.score();


        enableCORS(origin, methods, headers);
    }
    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            response.type("application/json");
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}