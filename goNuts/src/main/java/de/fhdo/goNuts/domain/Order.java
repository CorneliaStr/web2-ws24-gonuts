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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {}

    public Order(Date date, List<OrderPosition> orderPosition, Customer customer) {
        this.date = date;
        this.orderPosition = orderPosition;
        this.customer = customer;
    }

    public Long getId() { return id; }

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

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", orderPosition=" + orderPosition +
                ", customer=" + customer +
                '}';
    }
}
