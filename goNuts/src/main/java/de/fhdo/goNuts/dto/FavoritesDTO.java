package de.fhdo.goNuts.dto;

import java.util.List;
import java.util.Objects;

public class FavoritesDTO {
    private Long id;
    private CustomerDTO customer;
    private List<ProductDTO> products;

    public FavoritesDTO() {
    }

    public FavoritesDTO(Long id, CustomerDTO customer, List<ProductDTO> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoritesDTO favorites)) return false;

        return Objects.equals(id, favorites.id) && Objects.equals(customer, favorites.customer) && Objects.equals(products, favorites.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products);
    }
}
