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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderPosition> orderPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderPosition> getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(List<OrderPosition> orderPosition) {
        this.orderPosition = orderPosition;
    }

}
