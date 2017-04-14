package fr.univtln.wxing869.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by wenlixing on 28/03/2017.
 */
public class InitDB {
    public static void createTables() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresqllocal");
        EntityManager em = emf.createEntityManager();

        Student student1 = new Student(1,"wenli","xing");
        Student student2 = new Student(2,"alex","poletti");



        University univ_tln = new University();
        univ_tln.setUnivId(1);
        univ_tln.setName("univ_toulon");

        University univ_nice = new University();
        univ_nice.setUnivId(2);
        univ_nice.setName("univ_nice");

        student1.setUniversity(univ_tln);
        student2.setUniversity(univ_tln);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(student1);
        em.persist(student2);
        em.persist(univ_tln);
        em.persist(univ_nice);
        transaction.commit();


        em.close();
        emf.close();
    }

}
