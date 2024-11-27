package de.fhdo.goNuts.mvcController;

import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class MvcOrderController {

    private final OrderService orderService;

    @Autowired
    public MvcOrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public String showCart(Model model, @RequestParam long id){
        Order order = orderService.getOrder(id);
        List<OrderPosition> positionen = order.getOrderPosition();
        List<Product> produkte = new ArrayList<>();
        double gesamtpreis = 0;
        for(OrderPosition position : positionen){
            produkte.add(position.getProduct());
            gesamtpreis += position.getProduct().getPrice();
        }
        
        model.addAttribute("products", produkte);
        model.addAttribute("gesamtpreis",gesamtpreis);
        return "cart";
    }

}
