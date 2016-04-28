package ken.task.Exception;


import ken.task.Util.ErrorResponseUtil;
import ken.task.model.ResponseError;
import org.apache.http.HttpResponse;

/**
 * Created by Ryan on 21/2/2016.
 */
public abstract class ServerException extends Exception {

    public void handleResponseToClientForError(HttpResponse response){
        ErrorResponseUtil.writeJson(getResponseError(), response);
    }

    public abstract ResponseError getResponseError();

}
