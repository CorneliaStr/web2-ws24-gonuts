package de.fhdo.goNuts.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Favorites {
    @Id
    private Long id;
    
    @OneToOne
    @MapsId
    private Customer customer;
    
    @ManyToMany
    @JoinTable(name="Favorites_Product",
            joinColumns={@JoinColumn(name="favoritesId")},
            inverseJoinColumns={@JoinColumn(name="productId")})
    private List<Product> products = new ArrayList<>();

    public Favorites() {}

    public Favorites(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorites favorites)) return false;

        return Objects.equals(id, favorites.id) && Objects.equals(customer, favorites.customer) && Objects.equals(products, favorites.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products);
    }
}
