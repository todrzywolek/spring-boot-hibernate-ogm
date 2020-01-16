package pl.edu.agh.databases;

import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

@Configuration
public class DatabaseConfiguration {
    @Bean
    EntityManager entityManager() {
        PersistenceProvider persistenceProvider = new HibernateOgmPersistence();
        EntityManagerFactory entityManagerFactory
                = persistenceProvider.createEntityManagerFactory("ogm", null);
        return entityManagerFactory.createEntityManager();
    }
}
