package ken.task.Exception;

import ken.task.Util.ErrorResponseUtil;
import ken.task.model.ResponseError;
import org.apache.http.HttpStatus;
import org.apache.http.impl.nio.bootstrap.HttpServer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ryan on 26/2/2016.
 */
public class ServerShutdownException extends ServerException {

    @Override
    public ResponseError getResponseError() {
        return ErrorResponseUtil.errorBuilder(null, HttpStatus.SC_GONE, "server shutdown");
    }

    public void shutdown(HttpServer server){
        System.out.println("Server going to shutdown");
        server.shutdown(5, TimeUnit.SECONDS);
    }
}
