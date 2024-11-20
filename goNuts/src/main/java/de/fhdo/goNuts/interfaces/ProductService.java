package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProduct(Long id);
}
