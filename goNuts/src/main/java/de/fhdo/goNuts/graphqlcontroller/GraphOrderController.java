package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.interfaces.OrderService;
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

    @QueryMapping("cart")
    public OrderDTO getCart() {
        return orderService.getCart();
    }


}
