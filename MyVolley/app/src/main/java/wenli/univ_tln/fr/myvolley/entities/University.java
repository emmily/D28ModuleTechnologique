package wenli.univ_tln.fr.myvolley.entities;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wenlixing on 03/04/2017.
 */
public class University implements Serializable {
    private int univId;
    private String name;

    public University() {
    }

    public University(int univId, String name) {
        this.univId = univId;
        this.name = name;
    }

    public int getUnivId() {
        return univId;
    }

    public void setUnivId(int univId) {
        this.univId = univId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "University{" +
                "univId=" + univId +
                ", name='" + name + '\'' +
                '}';
    }
}
