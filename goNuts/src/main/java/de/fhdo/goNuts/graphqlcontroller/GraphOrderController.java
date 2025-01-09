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
    public List<OrderDTO> getOrder() {return this.orderService.getAllOrders();
    }

    @QueryMapping("order")
    public OrderDTO getOrder(@Argument Long id){return this.orderService.getOrder(id);};

    @QueryMapping("cart")
    public OrderDTO getCart() {
        return orderService.getCart();
    }

    @MutationMapping("addProduct")
    public OrderDTO addProductToOrder(@Argument("product") ProductDTO product, @Argument("quantity") Long quantity){
      this.orderService.addProductToOrder(product, quantity );
      return this.orderService.getCart();
    }

    @MutationMapping("update")
    public void updateOrder(@Argument("orderDTO") OrderDTO orderDTO){
        this.orderService.updateOrder(orderDTO);
    }

    @MutationMapping("deleteOP")
    public OrderDTO deleteOrderposition(@Argument("orderPositionDTO") OrderPositionDTO orderPositionDTO, @Argument("orderID") Long orderID){
       return this.orderService.deleteOrderPosition(orderPositionDTO, orderID);
    }



}
