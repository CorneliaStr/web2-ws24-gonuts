package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.interfaces.ProductService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphProductController {
    private final ProductService productService;

    public GraphProductController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping("products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

}
