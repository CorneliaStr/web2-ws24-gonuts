package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    public List<OrderDTO> getAllOrders();

    public OrderDTO getOrder(Long id);
}
