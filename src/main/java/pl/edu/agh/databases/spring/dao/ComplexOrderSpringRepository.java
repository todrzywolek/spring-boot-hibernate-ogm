package pl.edu.agh.databases.spring.dao;

import org.springframework.stereotype.Repository;
import pl.edu.agh.databases.spring.controllers.OrderSpringController;
import pl.edu.agh.databases.spring.entities.Customer;
import pl.edu.agh.databases.spring.entities.Employee;
import pl.edu.agh.databases.spring.entities.Order;

import java.util.Optional;

@Repository
public class ComplexOrderSpringRepository {

    private EmployeeSpringRepository employeeSpringRepository;
    private CustomerSpringRepository customerSpringRepository;
    private OrderSpringRepository orderSpringRepository;

    public ComplexOrderSpringRepository(EmployeeSpringRepository employeeSpringRepository,
                                        CustomerSpringRepository customerSpringRepository,
                                        OrderSpringRepository orderSpringRepository) {
        this.employeeSpringRepository = employeeSpringRepository;
        this.customerSpringRepository = customerSpringRepository;
        this.orderSpringRepository = orderSpringRepository;
    }

    public void createOrder(OrderSpringController.ComplexOrderDTO newOrder) {
        Order order = newOrder.getOrder();
        Optional<Employee> employeeOptional = employeeSpringRepository.findById(order.getEmployeeId());
        Optional<Customer> customerOptional = customerSpringRepository.findById(order.getCustomerId());

        if (!employeeOptional.isPresent()) {
            if (newOrder.getEmployee() != null) {
                Employee persistedEmployee = employeeSpringRepository.save(newOrder.getEmployee());
                order.setEmployeeId(persistedEmployee.getEmployeeID());
            } else {
                throw new RuntimeException("Employee not exist and data to insert is not provided");
            }
        }

        if (!customerOptional.isPresent()) {
            if (newOrder.getCustomer() != null) {
                Customer persistedCustomer = customerSpringRepository.save(newOrder.getCustomer());
                order.setCustomerId(persistedCustomer.getCustomerId());
            } else {
                throw new RuntimeException("Customer not exist and data to insert is not provided");
            }
        }

        orderSpringRepository.save(order);
    }
}
