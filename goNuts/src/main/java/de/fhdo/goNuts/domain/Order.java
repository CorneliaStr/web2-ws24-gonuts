package de.fhdo.goNuts.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
// order is a keyword
@Table(name = "product_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    
    private Date date;
    
    @OneToMany
    private List<OrderPosition> orderPosition;
}
