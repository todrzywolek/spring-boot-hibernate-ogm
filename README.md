# Hibernate OGM + Spring Data Mongo + MongoDB
Projekt na przedmiot Bazy Danych, niestacjonarne 2019/2020

# Uczestnicy grupy:
- Tomasz Odrzywołek
- Albert Pawula
- Tomasz Szewczyk

# Temat:
- Java, Hibernate + Spring Data Mongo + MongoDB

# Adresy mailowe:
- tomekodr@student.agh.edu.pl
- albertpawula@gmail.com
- tomasz.szewczyk02@gmail.com

# Dokumentacja:
- https://sites.google.com/view/bazy-danych-odrzywolek-pawula


## Hibernate OGM config
MongoDB datastore configuration in resources/META-INF/persistence.xml file
Hibernate OGM pl.edu.agh.databases.entities in pl.edu.agh.databases.hibernate package

## Spring Data MongoDB config
Just for comparison with Hibernate OGM another solution based on Spring Data MongoDB has been added

# Tutorial

## Download Northwind
https://github.com/Microsoft/sql-server-samples/tree/master/samples/databases/northwind-pubs

## Install SQL Server
Deal with it

## Insert Northwind into SQL Server

## Spring Data JPA configuration
```Java
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
class DatabaseConfiguration {

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
}
```

## Spring Data JPA repositiories
Spring Data JPA repositories does't work with Hibernate OGM, because it does not implement Criteria Queries yet.