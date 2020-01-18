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

# Maszyna wirtualna
Maszyna wirtualna z działającym projektem dostępna do pobrania pod adresem

https://drive.google.com/file/d/1a5f2PA-6XZe2aLEPhp8B-Namegosdsn2/view?usp=sharing

Uwaga!
Rozmiar obrazu dysku maszyny wirtualnej to około 6 GB.

Do uruchomienia maszyny wirtualnej wymagany jest program Oracle VirtualBox.

## Hibernate OGM config
Konfiguracja MongoDB z Hibernate OGM dla JPA znaduje się w pliku resources/META-INF/persistence.xml w paczce
pl.edu.agh.databases.hibernate. Konfiguracja persistence.xml jest używana tylko w aplikacji konwertującej bazę SQL do Mongo.
Niestety z powodu konfliktów występujących przy użyciu Hibernate ORM, Hibernate OGM konfiguracja jest cześciowo wykonywana manualnie.

## Spring Data MongoDB config
Dla porównania z Hibernate OGM inne rozwiązanie bazujące na Spring Data MongoDB zostało dodane do projektu.

# Tutorial

## Pobierz Northwind
Pobierz przykładową bazę danych Nortwind z Githuba Microsoft.
https://github.com/Microsoft/sql-server-samples/tree/master/samples/databases/northwind-pubs
Niestety Northwind jest dostępny tylko w wersji SQL, więc będziemy musieli przekonwertowac ją samodzielnie.

## Instalajca SQL Server i import Northwind.
Zainstaluj SQL Server i zaimportuj Northwind. My zrobiliśmy to za pomocą InteeliJ i wbudowanego klienta bazy danych.
Polecamy ten sposób, ponieważ na pewno działa, ale można to również zrobi innymi narzędziami Microsoft.

## Tworzenie Entities
Kolejnym krokiem jest utworzenie Entities wraz z relacjami tak aby odzwierciedlały one relacje skonfiguraowane w bazie danych.
Utworzone przez nas entities znajdują się w pl.edu.agh.databases.entities

## Instalacja MongoDB
Kolejnym krokiem jest zainstalowanie MongoDB. Polecamy skorzystanie z obrazu dockerowego.

## Uruchomienie konwertera
Aplikacja pl.edu.agh.databases.converter.ConverterApplication po uruchomieniu wczyta wszystkie entitties z bazy SQL,
a następnie zapisze je w Mongo, po uruchomieniu tej operacji powinieneś miec kompletną bazę Mongo i możesz przejśc,
do tworzenia aplikacji używającej tej bazy.

## Spring Data JPA configuration
Aby wygodnie korzystac z Java JPA API potrzebujemy Springa, który zapewni implemetację transakcji. Z tego powodu
w aplikacji konfigurujemy Hibernate OGM za pośrednictwem Springa.

Oto przykładowa konfiguracja:
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

## Magiczne repozytoria Spring Data JPA
Repozytoria Spring Data JPA takie jak JpaRepository lub CrudRepository wymagają do działania Criteria Queries,
które nie są jeszcze wspierane przez Hibernate OGM. Z tego powodu musieliśmy zaimplementowac repozystria samodzielnie.

## Tworzenie repozytoriów
Oto przykładowe repozytorium, używające zarówno queries napisanych w języlku HQL jak i natywnych MongoDB.
Ponieważ Hibernate OGM jest jeszcze niedokończony tylko niektóre elementy składni HQL są obsługiwane:
    - proste porównania "<", "<=", "=", ">=" i ">"
    - IS NULL oraz IS NOT NULL
    - operatory logiczne AND, OR, NOT
    - LIKE, IN oraz BETWEEN
    - ORDER BY
    - JOIN na zagnieżdżonych kolekcjach
Wszystkie pozostałe operacje mogą zostac wykonane przez natywne query MongoDB.

```java
@Repository
public class OrderDetailsRepository extends BaseRepository<OrderDetail> {
    public List<OrderDetail> findAll() {
        return query("select x from OrderDetail x");
    }

    public List<OrderDetail> findByOrder(int id) {
        return entityManager
                .createNativeQuery(String.format("{\"OrderID\": %d}", id), OrderDetail.class)
                .getResultList();
    }

    public OrderDetail findByID(ObjectId id) {
        return entityManager.find(OrderDetail.class, id);
    }

    public OrderDetail save(OrderDetail order) {
        entityManager.persist(order);
        return order;
    }

    public void delete(ObjectId id) {
        entityManager.remove(findByID(id));
    }
}
```