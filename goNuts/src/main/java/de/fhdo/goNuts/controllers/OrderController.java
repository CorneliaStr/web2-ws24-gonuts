package de.fhdo.goNuts.controllers;

import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.interfaces.OrderService;
import de.fhdo.goNuts.requestObjects.AddProductToOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("api/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    public OrderDTO getOrderById(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @GetMapping("/cart")
    public OrderDTO getCart(@RequestHeader("Authorization") String token){return orderService.getCart(token);}

    @PostMapping("/addProduct")
    public OrderDTO addProductToOrder(@RequestBody AddProductToOrderRequest request) {
        return orderService.addProductToOrder(request.getProduct(), request.getQuantity(), request.getToken());
    }

    @PostMapping("/update")
    public void updateOrder(@RequestBody OrderDTO orderDTO){
        this.orderService.updateOrder(orderDTO);
    }

}
