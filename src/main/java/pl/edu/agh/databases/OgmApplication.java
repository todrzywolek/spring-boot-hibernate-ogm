package pl.edu.agh.databases;

//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.edu.agh.databases.entities.Employee;
import pl.edu.agh.databases.entities.Region;
import pl.edu.agh.databases.entities.Territory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;
import java.util.UUID;

//@EnableJpaRepositories
public class OgmApplication {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");

//        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//            EntityTransaction transaction = entityManager.getTransaction();
//            transaction.begin();
//
//            Territory territory = new Territory();
//            entityManager.persist(territory);
//
//            Region region = new Region();
//            region.setRegionDescription("dupa");
//            entityManager.persist(region);
//
//
//            entityManager.flush();
//            entityManager.close();
//            transaction.commit();
//        }

        {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println(entityManager
                    .createQuery("SELECT r FROM Region r")
                    .setMaxResults(3)
                    .getResultList());

            System.out.println(entityManager
                    .createQuery("SELECT r FROM Territory r")
                    .setMaxResults(3)
                    .getResultList());

            List<Employee> aaa = entityManager
                    .createQuery("SELECT r FROM Employee r")
                    .setMaxResults(3)
                    .getResultList();

            System.out.println(((List<Region>) entityManager
                    .createQuery("SELECT r FROM Region r")
                    .setMaxResults(3)
                    .getResultList()).get(0).getTerritories());

            entityManager.flush();
            entityManager.close();
            transaction.commit();
        }
    }
}
