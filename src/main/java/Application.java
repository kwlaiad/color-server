import ken.task.Server;

/**
 * Created by User on 27/4/2016.
 */
public class Application {
    public static void main(String args[]) {
        switch (args[0]) {
            case "start":
                serverStart();
                break;
            case "shutdown":
                serverShut();
                break;
            default:
                System.err.println("Unknown Argument(s)");
                break;
        }
    }

    public static void serverStart() {
        System.out.println("Running Server");

        try {
            Server.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void serverShut() {
        Server.serverShutdown();
    }
}
