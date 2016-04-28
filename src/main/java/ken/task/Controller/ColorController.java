package ken.task.Controller;


import ken.task.Exception.RequestBodyErrorException;
import ken.task.Exception.ServerException;
import ken.task.Exception.TooManyArgumentsException;
import ken.task.Util.ColorUtil;
import ken.task.Util.ErrorResponseUtil;
import ken.task.Util.ResponseUtil;
import ken.task.model.Color;
import ken.task.model.ResponseError;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 27/4/2016.
 */
public class ColorController extends BaseController {

    public ColorController(HttpRequest request, HttpResponse response) {
        super(request, response);
    }

    public void get(String... args) throws ServerException {
        if(args.length == 1) {
            List<Color> colorList;
            try {
                ColorUtil colorUtil = new ColorUtil();

                colorList = colorUtil.getDao().queryForAll();

                ResponseUtil.writeJson(colorList, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(args.length == 2) {
            String name = args[1];
            List<Color> colorList;
            try {
                ColorUtil colorUtil = new ColorUtil();

                colorList = colorUtil.find("color", name);
                if(colorList == null) {
                    List<ResponseError.ErrorItem> errorItemList = new ArrayList<>();

                    errorItemList.add(new ResponseError.ErrorItem("not found", "not found"));

                    ResponseError responseError = ErrorResponseUtil.errorBuilder(errorItemList, HttpStatus.SC_BAD_REQUEST, "Color not found");

                    ErrorResponseUtil.writeJson(responseError, response);

                    return;
                }

                ResponseUtil.writeJson(colorList.get(0), response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new TooManyArgumentsException();
        }
    }

    public void post(String... args) throws ServerException {
        if(args.length == 1) {
            try {
                ColorUtil colorUtil = new ColorUtil();
                Color reqColor = colorUtil.fromJson(getRequestBodyContent());
                if(reqColor.getColor() == null || reqColor.getValue() == null) {
                    throw new RequestBodyErrorException();
                }

                if(colorUtil.createOrUpdate(reqColor).isCreated()) {
                    ResponseUtil.writeJson(reqColor, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        else {
            throw new TooManyArgumentsException();
        }
    }

    public void delete(String... args) throws ServerException {
        if(args.length == 2) {
            try {
                ColorUtil colorUtil = new ColorUtil();
                String name = args[1];
                Color color = colorUtil.find("color", name).get(0);
                if(color == null) {
                    response.setStatusCode(HttpStatus.SC_NOT_FOUND);
                    return;
                }
                colorUtil.delete(color);
                response.setStatusCode(HttpStatus.SC_OK);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new TooManyArgumentsException();
        }
    }

    public void put(String... args) throws ServerException {
        if(args.length == 2) {
            try {
                ColorUtil colorUtil = new ColorUtil();
                String name = args[1];
                Color color = colorUtil.find("color", name).get(0);
                if(color == null) {
                    response.setStatusCode(HttpStatus.SC_NOT_FOUND);
                    return;
                }
                Color updateColor = colorUtil.fromJson(getRequestBodyContent());
                updateColor.setDbid(color.getDbId());
                if(!colorUtil.createOrUpdate(updateColor).isUpdated()) {
                    response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                    return;
                }
                response.setStatusCode(HttpStatus.SC_OK);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new TooManyArgumentsException();
        }
    }
}
