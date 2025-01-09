package de.fhdo.goNuts.controllers;

import de.fhdo.goNuts.dto.OrderDTO;
import de.fhdo.goNuts.dto.OrderPositionDTO;
import de.fhdo.goNuts.interfaces.OrderService;
import de.fhdo.goNuts.requestObjects.AddProductToOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("api/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping(value = "/cart",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OrderDTO getCart(@RequestHeader("Authorization") String token) {
        return orderService.getCart(token);
    }

    @PostMapping(value = "/addProduct",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OrderDTO addProductToOrder(@RequestBody AddProductToOrderRequest request) {
        return orderService.addProductToOrder(request.getProduct(), request.getQuantity(), request.getToken());
    }

    @PostMapping(value = "/update",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void updateOrder(@RequestBody OrderDTO orderDTO) {
        this.orderService.updateOrder(orderDTO);
    }

    @DeleteMapping(value = "/deleteOP/{orderId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OrderDTO deleteOrderposition(@RequestBody OrderPositionDTO orderPositionDTO, @PathVariable Long orderId) {
        return this.orderService.deleteOrderPosition(orderPositionDTO, orderId);
    }

}
