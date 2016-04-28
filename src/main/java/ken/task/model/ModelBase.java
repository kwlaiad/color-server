package ken.task.model;

import com.google.gson.GsonBuilder;
import com.j256.ormlite.field.DatabaseField;

public class ModelBase {

    @DatabaseField(generatedId = true)
    private transient int id;

    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public ModelBase() {
    }

    public int getDbId() {
        return id;
    }

    public void setDbid(int id) {
        this.id = id;
    }

    public String toJson() {
        return new GsonBuilder().setDateFormat(DATE_FORMAT).create().toJson(this);
    }

}