package ken.task.Util;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import ken.task.Util.Db.DbConfig;
import ken.task.model.Color;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 27/4/2016.
 */
public class ColorUtil {
    private ConnectionSource connectionSource;
    private Dao<Color, Integer> dao;

    public ColorUtil() throws SQLException{
        connectionSource = new JdbcConnectionSource(DbConfig.URL);
    }

    public Dao<Color, Integer> getDao() throws SQLException {
        if(dao == null) {
            dao = DaoManager.createDao(connectionSource, Color.class);
        }
        return dao;
    }

    public QueryBuilder<Color, Integer> getQueryBuilder() throws SQLException {
        return getDao().queryBuilder();
    }

    public Where where() throws SQLException {
        QueryBuilder<Color, Integer> queryBuilder = getDao().queryBuilder();
        return queryBuilder.where();
    }

    public List<Color> find(String key, String val) throws SQLException {
        return where().eq(key, val).query();
    }

    public Color fromJson(String json) {
        return new Gson().fromJson(json, Color.class);
    }

    public Dao.CreateOrUpdateStatus createOrUpdate(Color color) throws SQLException {
        if(color == null) {
            return new Dao.CreateOrUpdateStatus(false, false, 0);
        }
        return getDao().createOrUpdate(color);
    }

    public int delete(Color color) throws SQLException {
        if(color != null) {
            return getDao().delete(color);
        }
        return 0;
    }

    public Color findById(String id) throws SQLException {
        List<Color> result = find("id", id);
        if (!(result == null || result.isEmpty())) {
            return result.get(0);
        }
        return null;
    }

}
