package pl.edu.agh.databases.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class DbConfig {

    @Bean
    public EntityManager entityManagerBean() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");
        return entityManagerFactory.createEntityManager();
    }
}
