package de.fhdo.goNuts.mvcController;

import de.fhdo.goNuts.interfaces.ProductService;
import de.fhdo.goNuts.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shop")
public class MvcProductController {
    private ProductService productService;
    private TagService tagService;

    @Autowired
    public MvcProductController(ProductService productService, TagService tagService) {
        this.productService = productService;
        this.tagService = tagService;
    }

    @GetMapping
    public String showShop(Model model) {
        model.addAttribute("tags", this.tagService.getTags());
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }


}
