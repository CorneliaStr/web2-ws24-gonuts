package de.fhdo.goNuts.dto;

public class OrderPositionDTO {
    private Long id;
    private OrderDTO order;
    private ProductDTO product;

    public OrderPositionDTO() {}

    public OrderPositionDTO(Long id, OrderDTO order, ProductDTO product) {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
