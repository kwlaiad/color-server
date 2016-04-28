package ken.task.Util.Db;

/**
 * Created by User on 26/4/2016.
 */
public class DbConfig {
    private static final String DB_NAME = "rest_task"; //Application.prop.getProperty("mysql_dbname");

    private static final String USERNAME = "root"; //Application.prop.getProperty("mysql_username");

    private static final String PASSWORD = "ken950609"; //Application.prop.getProperty("mysql_password");

    private static final String HOST = "localhost:3306"; //Application.prop.getProperty("mysql_host");

    public static String URL = "jdbc:mysql://" + HOST + "/" + DB_NAME + "?user=" + USERNAME + "&password=" + PASSWORD + "&character_set_client=UTF-8&character_set_database=UTF-8&character_set_results=UTF8&character_set_server=UTF-8&character_set_system=UTF-8";

}
