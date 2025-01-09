package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.ProductDTO;

import java.util.List;

public interface OrderService {

    public List<OrderDTO> getAllOrders();

    public OrderDTO getOrder(Long id);

    public OrderDTO getCart(String token);

    /**
     * GetCart für GraphQL ohne den Token.
     */
    public OrderDTO getCart();

    public OrderDTO addProductToOrder(ProductDTO productDTO, Long quantity, String token);

    void updateOrder(OrderDTO orderDTO);
}
