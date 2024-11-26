package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.interfaces.OrderService;
import de.fhdo.goNuts.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        this.orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order getOrder(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        return order.orElse(null);
    }
}
