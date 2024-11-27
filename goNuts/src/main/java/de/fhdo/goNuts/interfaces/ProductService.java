package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProducts();

    public ProductDTO getProduct(Long id);
}
