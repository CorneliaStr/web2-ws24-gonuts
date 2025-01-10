package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.OrderPositionDTO;
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

    public List<OrderDTO> getOrders(String token);

    public OrderDTO addProductToOrder(ProductDTO productDTO, Long quantity, String token);

    public OrderDTO addProductToOrderWithoutToken(ProductDTO productDTO, Long quantity);

    public void updateOrder(OrderDTO orderDTO);

    public OrderDTO deleteOrderPosition(OrderPositionDTO orderPositionDTO, Long orderID);
}
