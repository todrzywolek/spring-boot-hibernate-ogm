package com.example.ogm;

import com.example.ogm.provider.XMongoDBDatastoreProvider;
import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;
import java.util.Map;

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

}
