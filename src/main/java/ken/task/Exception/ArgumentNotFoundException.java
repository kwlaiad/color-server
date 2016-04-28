package ken.task.Exception;

import ken.task.Util.ErrorResponseUtil;
import ken.task.model.ResponseError;
import org.apache.http.HttpStatus;

/**
 * Created by Ryan on 21/2/2016.
 */
public class ArgumentNotFoundException extends ServerException {

    @Override
    public ResponseError getResponseError() {
        return ErrorResponseUtil.errorBuilder(null, HttpStatus.SC_BAD_REQUEST, "Uri argument(s) not found");
    }
}
