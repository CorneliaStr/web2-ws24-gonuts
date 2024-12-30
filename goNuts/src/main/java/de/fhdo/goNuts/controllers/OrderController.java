package de.fhdo.goNuts.controllers;

import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.OrderService;
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

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @GetMapping("/cart")
    public OrderDTO getCart(){return orderService.getCart();}

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody ProductDTO productDTO) {
        this.orderService.addProductToOrder(productDTO);
    }

}
