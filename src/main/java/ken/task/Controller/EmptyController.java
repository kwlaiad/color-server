package ken.task.Controller;

import ken.task.Exception.ArgumentNotFoundException;
import ken.task.Exception.ServerException;

public class EmptyController extends BaseController {

    private static EmptyController instance;

    public static synchronized EmptyController getInstance(){
        if(instance==null){
            instance = new EmptyController();
        }
        return instance;
    }

    public EmptyController(){
        super();
    }

    @Override
    public void get(String... args) throws ServerException {
        throw new ArgumentNotFoundException();
    }

    @Override
    public void post(String... args) throws ServerException {
        throw new ArgumentNotFoundException();

    }

    @Override
    public void delete(String... args) throws ServerException {
        throw new ArgumentNotFoundException();

    }

    @Override
    public void put(String... args) throws ServerException {
        throw new ArgumentNotFoundException();
    }
}
