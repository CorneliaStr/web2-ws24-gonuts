package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.OrderPositionDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Controller
public class GraphOrderController {

    private final OrderService orderService;

    public GraphOrderController(OrderService orderService){ this.orderService = orderService;}

    @QueryMapping("orders")
    public List<OrderDTO> getOrder() {return orderService.getAllOrders();
    }

    @QueryMapping("order")
    public OrderDTO getOrder(@Argument Long id){return orderService.getOrder(id);}

    @QueryMapping("cart")
    public OrderDTO getCart(@Argument Long id) {
        return orderService.getCart(id);
    }

    @MutationMapping("addProductToOrder")
    public OrderDTO addProductToOrder(@Argument("product") ProductDTO product, @Argument("quantity") Long quantity){
      return orderService.addProductToOrderWithoutToken(product, quantity);
    }

    @MutationMapping("updateOrder")
    public void updateOrder(@Argument("orderDTO") OrderDTO orderDTO){
        orderService.updateOrder(orderDTO);
    }

    @MutationMapping("deleteOrderposition")
    public OrderDTO deleteOrderposition(@Argument("orderPositionDTO") OrderPositionDTO orderPositionDTO, @Argument("orderID") Long orderID){
       return orderService.deleteOrderPosition(orderPositionDTO, orderID);
    }



}
