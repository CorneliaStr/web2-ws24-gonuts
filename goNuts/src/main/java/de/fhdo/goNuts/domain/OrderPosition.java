package de.fhdo.goNuts.domain;

import jakarta.persistence.*;

@Entity
public class OrderPosition {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public OrderPosition() {}

    public OrderPosition(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
