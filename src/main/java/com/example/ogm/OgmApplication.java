package com.example.ogm;

import com.example.ogm.spring.Customer;
import com.example.ogm.spring.CustomerRepository;
import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.spi.PersistenceProvider;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableJpaRepositories
public class OgmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OgmApplication.class, args);
    }

//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		return new JpaTransactionManager();
//	}

//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
//		entityManager.setPersistenceUnitName("mongoPersistenceUnit");
//		entityManager.setJpaVendorAdapter(new MyJpaVendorAdapter());
//		Map<String, String> properties = new HashMap<>();
//		properties.put("javax.persistence.transactionType", "resource_local");
//		properties.put("hibernate.ogm.datastore.provider", "com.example.ogm.provider.XMongoDBDatastoreProvider");
//		properties.put("hibernate.ogm.datastore.database", "test");
//		entityManager.setJpaPropertyMap(properties);
//		entityManager.setPersistenceProviderClass(HibernateOgmPersistence.class);
//
//		return entityManager;
//	}

    class MyJpaVendorAdapter extends HibernateJpaVendorAdapter {
        @Override
        public PersistenceProvider getPersistenceProvider() {
            return new HibernateOgmPersistence();
        }
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            repository.deleteAll();

            // save a couple of customers
            repository.save(new Customer("Alice", "Smith"));
            repository.save(new Customer("Bob", "Smith"));

            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (Customer customer : repository.findAll()) {
                System.out.println(customer);
            }
            System.out.println();

            // fetch an individual customer
            System.out.println("Customer found with findByFirstName('Alice'):");
            System.out.println("--------------------------------");
            System.out.println(repository.findByFirstName("Alice"));

            System.out.println("Customers found with findByLastName('Smith'):");
            System.out.println("--------------------------------");
            for (Customer customer : repository.findByLastName("Smith")) {
                System.out.println(customer);
            }
        };
    }
}
