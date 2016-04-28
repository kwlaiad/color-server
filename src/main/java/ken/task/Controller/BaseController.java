package ken.task.Controller;

import ken.task.Exception.ServerException;
import ken.task.Exception.UriParamsNotFoundException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Ryan on 21/2/2016.
 */
public abstract class BaseController implements Serializable {

    // static final boolean Application_Secure_Enable = Application.prop.getProperty("application.security", "false").equals("true");

    HttpResponse response;
    HttpRequest request;

    public BaseController(HttpRequest request, HttpResponse response)
    {
        this.request = request;
        this.response = response;
    }

    public BaseController() {

    }

    public abstract void get(String... args) throws ServerException;

    public abstract void post(String... args) throws ServerException;

    public abstract void delete(String... args) throws ServerException;

    public abstract void put(String... args) throws ServerException;

    public String getRequestBodyContent() throws IOException {
        //TODO: throw exception when get/delete reach
        return EntityUtils.toString(((HttpEntityEnclosingRequest) request).getEntity());
    }

    public List<NameValuePair> getUriParams() throws URISyntaxException {

        return URLEncodedUtils.parse(new URI(request.getRequestLine().getUri()), "UTF-8");

    }

}
