package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.domain.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();

    public Order getOrder(Long id);
}
