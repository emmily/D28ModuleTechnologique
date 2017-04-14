package wenli.univ_tln.fr.myvolley.entities;

import java.io.Serializable;

/**
 * Created by wenlixing on 03/04/2017.
 */

public class Student implements Serializable{
    private int id;
    private String firstname;
    private String lastname;
    private University university;

    public Student() {
    }

    public Student(int id, String firstname, String lastname, University university) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.university = university;
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
