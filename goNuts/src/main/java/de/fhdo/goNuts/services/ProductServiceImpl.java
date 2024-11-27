package de.fhdo.goNuts.services;

import de.fhdo.goNuts.domain.Product;
import de.fhdo.goNuts.dto.ProductDTO;
import de.fhdo.goNuts.interfaces.ProductService;
import de.fhdo.goNuts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return productRepository
                .findAll()
                .stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(),p.getDescription(), p.getPrice(), p.getImage()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return  product.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(),p.getDescription(), p.getPrice(), p.getImage()))
                .findAny()
                .orElse(null);
    }
}
