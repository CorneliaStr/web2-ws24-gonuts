package de.fhdo.goNuts.graphqlcontroller;

import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping("product")
    public ProductDTO getProduct(@Argument Long id) {return this.productService.getProduct(id);}

    @MutationMapping
    public ProductDTO createProduct(@Argument("product") ProductDTO product) {
        return this.productService.createProduct(product);
    }
}
