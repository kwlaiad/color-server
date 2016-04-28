package ken.task.Exception;

import ken.task.Util.ErrorResponseUtil;
import ken.task.model.ResponseError;
import org.apache.http.HttpStatus;

/**
 * Created by Ryan on 11/3/2016.
 */
public class RequestBodyNotFoundException extends ServerException {

    @Override
    public ResponseError getResponseError() {
        return ErrorResponseUtil.errorBuilder(null, HttpStatus.SC_BAD_REQUEST, "Request body not found");
    }

}
