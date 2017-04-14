package fr.univtln.wxing869;

import fr.univtln.wxing869.entities.University;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("universityresource")
public class UniversityResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    List<University> universities;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public List<University> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresqllocal");
        EntityManager em = emf.createEntityManager();
        Query query =em.createNamedQuery("University.findAll",University.class);
        universities = query.getResultList();
        em.close();
        emf.close();
        return universities;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("university/{id}")
    public University getByID(@PathParam("id") final int ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresqllocal");
        EntityManager em = emf.createEntityManager();
        Query query =em.createNamedQuery("University.findById",University.class);
        query.setParameter("id",ID);
        universities = query.getResultList();
        em.close();
        emf.close();
        return universities.get(0);
    }
}
