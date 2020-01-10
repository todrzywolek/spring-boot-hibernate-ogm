package pl.edu.agh.databases;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.edu.agh.databases.hibernate.models.Region;
import pl.edu.agh.databases.hibernate.models.Territory;
import pl.edu.agh.databases.spring.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableJpaRepositories
public class OgmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OgmApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");

            UUID regionId = null;

            {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();

                Territory territory = new Territory();
                entityManager.persist(territory);

                Region region = new Region();
                region.setRegionDescription("dupa");
                entityManager.persist(region);
                regionId = region.getId();


                entityManager.flush();
                entityManager.close();
                transaction.commit();
            }

            {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();

                Region loaded = entityManager.find(Region.class, regionId);
                System.out.println(loaded);

                entityManager.flush();
                entityManager.close();
                transaction.commit();
            }
        };
    }
}
