package de.fhdo.goNuts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    
    private Date date;
    
    @OneToMany
    private List<OrderPosition> orderPosition;
}
