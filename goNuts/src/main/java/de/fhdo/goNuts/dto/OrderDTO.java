package de.fhdo.goNuts.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Date date;
    private List<OrderPositionDTO> orderPosition;
    private CustomerDTO customer;

    public OrderDTO() {}

    public OrderDTO(Long id, Date date, List<OrderPositionDTO> orderPosition, CustomerDTO customer) {
        this.id = id;
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

    public List<OrderPositionDTO> getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(List<OrderPositionDTO> orderPosition) {
        this.orderPosition = orderPosition;
    }

    public CustomerDTO getCustomer() { return customer; }

    public void setCustomer(CustomerDTO customer) { this.customer = customer; }
}
