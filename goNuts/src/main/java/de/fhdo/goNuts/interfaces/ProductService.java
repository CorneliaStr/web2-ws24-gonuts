package de.fhdo.goNuts.interfaces;

import de.fhdo.goNuts.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProducts();

    public ProductDTO getProduct(Long id);

    public ProductDTO createProduct(ProductDTO productDTO);

    public ProductDTO updateProduct(ProductDTO productDTO);
    public boolean deleteProduct(Long productID);
}
