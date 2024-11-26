package de.fhdo.goNuts.mvcController;

import de.fhdo.goNuts.domain.Order;
import de.fhdo.goNuts.domain.OrderPosition;
import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.interfaces.OrderService;
import de.fhdo.goNuts.repository.OrderRepository;
import de.fhdo.goNuts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cart")
public class MvcOrderController {

    private OrderService orderService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public MvcOrderController(OrderService orderService,
                              ProductRepository productRepository,
                              OrderRepository orderRepository){
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String showCart(Model model, @RequestParam long id){
        Optional<Order> order = orderRepository.findById(id);
        List<OrderPosition> positionen = order.get().getOrderPosition();
        List<Product> produkte = new ArrayList<Product>();
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
