package pl.edu.agh.databases;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import pl.edu.agh.databases.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.spi.PersistenceProvider;
import java.util.List;

public class OgmApplication {

    public static void main(String[] args) {
        List<Category> categoryList;
        List<Customer> customerList;
        List<Employee> employeeList;
        List<Order> orderList;
        List<OrderDetail> orderDetailList;
        List<Product> productList;
        List<Region> regionList;
        List<Shipper> shipperList;
        List<Supplier> supplierList;

        {
            PersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
            EntityManagerFactory entityManagerFactory
                    = persistenceProvider.createEntityManagerFactory("sql", null);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            categoryList = entityManager
                    .createQuery("SELECT r FROM Category r")
                    .setMaxResults(3)
                    .getResultList();

            customerList = entityManager
                    .createQuery("SELECT r FROM Customer r")
                    .setMaxResults(3)
                    .getResultList();

            employeeList = entityManager
                    .createQuery("SELECT r FROM Employee r")
                    .setMaxResults(3)
                    .getResultList();

            orderList = entityManager
                    .createQuery("SELECT r FROM Order r")
                    .setMaxResults(3)
                    .getResultList();

            orderDetailList = entityManager
                    .createQuery("SELECT r FROM OrderDetail r")
                    .setMaxResults(3)
                    .getResultList();

            productList = entityManager
                    .createQuery("SELECT r FROM Product r")
                    .setMaxResults(3)
                    .getResultList();

            regionList = entityManager
                    .createQuery("SELECT r FROM Region r")
                    .setMaxResults(3)
                    .getResultList();

            shipperList = entityManager
                    .createQuery("SELECT r FROM Shipper r")
                    .setMaxResults(3)
                    .getResultList();

            supplierList = entityManager
                    .createQuery("SELECT r FROM Supplier r")
                    .setMaxResults(3)
                    .getResultList();

            entityManager.flush();
            entityManager.close();
            transaction.commit();
        }

        {
            PersistenceProvider persistenceProvider = new HibernateOgmPersistence();
            EntityManagerFactory entityManagerFactory
                    = persistenceProvider.createEntityManagerFactory("ogm", null);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            for(Category category : categoryList) {
                entityManager.persist(category);
            }

            for(Customer customer : customerList) {
                entityManager.persist(customer);
            }

            for(Employee employee : employeeList) {
                entityManager.persist(employee);
            }

            for(Order order : orderList) {
                entityManager.persist(order);
            }

            for(OrderDetail orderDetail : orderDetailList) {
                entityManager.persist(orderDetail);
            }

            for(Product product : productList) {
                entityManager.persist(product);
            }

            for(Region region : regionList) {
                entityManager.persist(region);
            }

            for(Shipper shipper : shipperList) {
                entityManager.persist(shipper);
            }

            for(Supplier supplier : supplierList) {
                entityManager.persist(supplier);
            }


            entityManager.flush();
            entityManager.close();
            transaction.commit();
        }

    }
}
