package ken.task.Exception;


import ken.task.Util.ErrorResponseUtil;
import ken.task.model.ResponseError;
import org.apache.http.HttpStatus;

/**
 * Created by Ryan on 26/2/2016.
 */
public class ApiNotAvailableException extends ServerException {

    @Override
    public ResponseError getResponseError() {
        return ErrorResponseUtil.errorBuilder(null, HttpStatus.SC_METHOD_NOT_ALLOWED, "method not found");
    }
}
