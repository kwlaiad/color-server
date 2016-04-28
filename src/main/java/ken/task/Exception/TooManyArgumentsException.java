package ken.task.Exception;

import ken.task.Util.ErrorResponseUtil;
import ken.task.model.ResponseError;
import org.apache.http.HttpStatus;

public class TooManyArgumentsException extends ServerException {

    @Override
    public ResponseError getResponseError() {
        return ErrorResponseUtil.errorBuilder(null, HttpStatus.SC_BAD_REQUEST, "Uri too many argument(s) found");
    }
}
