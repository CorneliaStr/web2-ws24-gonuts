package de.fhdo.goNuts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String description;
    private Double price;
    private String image;
    
    
    
    
}
