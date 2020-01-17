package pl.edu.agh.databases;

import org.dozer.DozerBeanMapper;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class OgmApplicationConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.ogm.datastore.provider", "mongodb");
        properties.put("hibernate.ogm.datastore.database", "Northwind");
        properties.put("hibernate.ogm.datastore.create_database", "true");
        properties.put("hibernate.ogm.datastore.host", "localhost:27017");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setPackagesToScan("pl.edu.agh.databases.entities");
        entityManager.setPersistenceUnitName("mongoPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        entityManager.setPersistenceProviderClass(HibernateOgmPersistence.class);
        entityManager.setEntityManagerFactoryInterface(OgmSessionFactory.class);
        entityManager.setEntityManagerInterface(OgmSession.class);
        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }
}
