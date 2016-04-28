package ken.task;

import ken.task.Controller.BaseController;
import ken.task.Controller.ColorController;
import ken.task.Controller.EmptyController;
import ken.task.Exception.ServerException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.protocol.HttpContext;

import java.util.Locale;

public class Route {

    public static void route(final HttpRequest request, final HttpResponse response, final HttpContext context) throws MethodNotSupportedException, ServerException {

        String method = request.getRequestLine().getMethod().toUpperCase(Locale.ENGLISH);

        //Remove Heading / in URL
        //Regex:
        //Rule 1: remove first /
        //Rule 2: Discard everything after ?
        //Rule 3: Split by /

        String[] requestUri = request.getRequestLine().getUri().toLowerCase().replaceFirst("^/","").replaceFirst("[?].*$","").split("/");

        BaseController resource;

        System.out.println("Request> " + request.getRequestLine().getMethod() + " " + request.getRequestLine().getUri());

        switch(requestUri[0].toLowerCase()){
            case "color":
                resource = new ColorController(request, response);
                break;
            default:
                resource = EmptyController.getInstance();
                break;
        }


        switch (method){
            case "GET":
                resource.get(requestUri);
                break;
            case "POST":
                resource.post(requestUri);
                break;
            case "PUT":
                resource.put(requestUri);
                break;
            case "DELETE":
                resource.delete(requestUri);
                break;
            default:
                throw new MethodNotSupportedException(method + " method not supported");
        }



    }
}
