package fr.univtln.wxing869.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wenlixing on 26/03/2017.
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "University.findAll",query = "select u from University u "),
        @NamedQuery(name = "University.findByName",query = "select u from University u where u.name=:name"),
        @NamedQuery(name = "University.findById",query = "select u from University u where u.univId=:univId")
})
public class University implements Serializable{
    @Id
    private int univId;
    private String name;


    public University() {
    }
    public University(int univId,String name) {
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
                "id=" + univId +
                ", name='" + name + '\'' +
                '}';
    }
}
