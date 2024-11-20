package de.fhdo.goNuts.mvcController;

import de.fhdo.goNuts.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("favorites")
public class MvcProductController {
    private ProductService productService;

    @Autowired
    public MvcProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showFavorites(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "favorites";
    }

    @GetMapping("shop")
    public String showShop(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }


}
