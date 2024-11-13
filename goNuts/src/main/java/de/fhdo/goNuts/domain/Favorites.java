package de.fhdo.goNuts.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Favorites {
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne
    private Customer customer;
    
    @ManyToMany
    @JoinColumn(name = "productId")
    private List<Product> products;
}
