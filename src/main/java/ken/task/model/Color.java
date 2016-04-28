package ken.task.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by User on 26/4/2016.
 */
@DatabaseTable(tableName = "color")
public class Color extends ModelBase {
    @DatabaseField(columnName = "color")
    private String color;
    @DatabaseField(columnName = "value")
    private String value;

    public Color(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public Color() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
