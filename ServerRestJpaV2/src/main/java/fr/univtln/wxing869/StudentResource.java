package fr.univtln.wxing869;

import fr.univtln.wxing869.entities.Student;
import fr.univtln.wxing869.entities.University;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("studentresource")
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresqllocal");
    EntityManager em = emf.createEntityManager();
    List<Student> students = new ArrayList<>();


    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents(){

        TypedQuery<Student> query = em.createNamedQuery("Student.findAll",Student.class);
        students = query.getResultList();
        em.close();
        emf.close();
        return students;
    }

    @GET
    @Path("student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("id") final int ID) {

        Query query = em.createNamedQuery("Student.findById");
        query.setParameter("id",ID);
        students = query.getResultList();
        em.close();
        emf.close();
        System.out.println(students.get(0));
        return students.get(0);
    }

/*    @PUT
    @Path("student")
    public void createStudent(@QueryParam("id") int id,
                              @QueryParam("firstname") String firstname,
                              @QueryParam("lastname") String lastname,
                             ){

        Student student = new Student(id,firstname,lastname);
        Adress adress = new Adress(rue,city,country,zipcode);
        University university =  new University(univName,adress);

        em.getTransaction().begin();
        em.persist(adress);
        em.persist(university);
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }*/

    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createStudent(final Student input){
        String msg="";
        Query query = em.createNamedQuery("Student.findById");
        query.setParameter("id",input.getId());
        List<Student> studentList = query.getResultList();
        if(studentList.isEmpty()){
            query = em.createNamedQuery("University.findById");
            query.setParameter("univId",input.getUniversity().getUnivId());
            List<University> universityList = query.getResultList();
            if(universityList.isEmpty()){
                em.getTransaction().begin();
                em.persist(input);
                em.getTransaction().commit();
                System.out.println("insert successfully!");
                msg="insert successfully!";

            }else {
                em.getTransaction().begin();
                query = em.createNativeQuery("INSERT INTO student (id, firstname,lastname,univId) " +
                        " VALUES(?,?,?,?)");
                query.setParameter(1, input.getId());
                query.setParameter(2, input.getFirstname());
                query.setParameter(3, input.getLastname());
                query.setParameter(4,input.getUniversity().getUnivId());
                query.executeUpdate();
                em.getTransaction().commit();
                System.out.println("insert successfully!");
                msg="insert successfully!";
            }
        }else {
            System.out.println("student already exists!");
            msg="student already exists!";
        }

        em.close();
        emf.close();
        return msg;
    }

    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent(@QueryParam("id") int id, @QueryParam("firstname") String firstname){

        Student student = em.find(Student.class,id);
        em.getTransaction().begin();
        student.setFirstname(firstname);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return "Update successfully";

    }
    @DELETE
    @Path("student/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudent(@PathParam("id") int id){

        Student student= em.find(Student.class,id);
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return "Delete successfully";
    }
}
