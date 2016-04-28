package ken.task.Util;

import com.google.gson.GsonBuilder;
import ken.task.model.ModelBase;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;

import java.util.List;

/**
 * Created by Ryan on 5/3/2016.
 */
public class ResponseUtil {

    public static void writeJson(ModelBase model, HttpResponse response){

        response.setStatusCode(HttpStatus.SC_OK); //return 200 OK

        NStringEntity entity;

        entity = new NStringEntity(model.toJson(), ContentType.APPLICATION_JSON);
        response.setEntity(entity);

        //CONSOLE
        System.out.println(model.toJson());

    }

    public static void writeJson(List<? extends ModelBase> modelList, HttpResponse response){

        response.setStatusCode(HttpStatus.SC_OK); //return 200 OK

        NStringEntity entity;

        GsonBuilder gsonBuilder = new GsonBuilder();

        String json = gsonBuilder.setDateFormat(ModelBase.DATE_FORMAT).create().toJson(modelList);
        entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
        response.setEntity(entity);

        //CONSOLE
        System.out.println(json);

    }
}
