package pl.edu.agh.databases.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.spring.dao.OrderSpringRepository;
import pl.edu.agh.databases.spring.entities.Order;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spring/orders")
public class OrderSpringController {

    private OrderSpringRepository orderSpringRepository;

    public OrderSpringController(OrderSpringRepository orderSpringRepository) {
        this.orderSpringRepository = orderSpringRepository;
    }

    @GetMapping
    public ResponseEntity<?> getOrders() {
        List<Order> orders = orderSpringRepository.findAll();

        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody @Valid Order order) {
        Order persistedOrder = orderSpringRepository.save(order);
        return ResponseEntity.created(URI.create("/spring/orders/" + persistedOrder.getOrderID())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") String id) {
        Optional<Order> orderOptional = orderSpringRepository.findById(id);

        return orderOptional.isPresent() ? ResponseEntity.ok(orderOptional.get()) : ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") String id, @RequestBody @Valid Order newOrder) {
        Optional<Order> oldOrderOptional = orderSpringRepository.findById(id);
        if (oldOrderOptional.isPresent()) {
            Order oldOrder = oldOrderOptional.get();
            newOrder.setOrderID(oldOrder.getOrderID());
            orderSpringRepository.save(newOrder);
            return ResponseEntity.ok().build();
        }
        return addOrder(newOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeOrder(@PathVariable("id") String id) {
        Optional<Order> orderOptional = orderSpringRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            orderSpringRepository.delete(order);
        }
        return ResponseEntity.ok().build();
    }
}
