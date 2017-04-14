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
        @NamedQuery(name = "Student.findAll",query = "select s from Student s "),
        @NamedQuery(name = "Student.findById",query = "select s from Student s where s.id=:id")
})
public class Student implements Serializable {
    @Id
    @Column(name = "ID")
    private int id;
    private String firstname;
    private String lastname;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "univId")
    private University university;


    public Student() {
    }

    public Student(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", university=" + university +
                '}';
    }
}
