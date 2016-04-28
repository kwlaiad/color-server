package ken.task.Util;

import ken.task.model.ResponseError;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;

import java.util.List;

/**
 * Created by Ryan on 5/3/2016.
 */
public class ErrorResponseUtil {


    public static ResponseError errorBuilder(List<ResponseError.ErrorItem> errorItemList, int code, String message){
        ResponseError.ErrorStructure responseErrorStructure = new ResponseError.ErrorStructure();

        responseErrorStructure.setErrors(errorItemList);
        responseErrorStructure.setCode(code);
        responseErrorStructure.setMessage(message);

        ResponseError responseError = new ResponseError();
        responseError.setError(responseErrorStructure);

        return responseError;
    }

    public static void writeJson(ResponseError error, HttpResponse response){

        response.setStatusCode(error.getError().getCode());

        NStringEntity entity;

        entity = new NStringEntity(error.toJson(), ContentType.APPLICATION_JSON);
        response.setEntity(entity);

        //CONSOLE
        System.out.println(error.toJson());

    }

}
