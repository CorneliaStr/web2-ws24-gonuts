package de.fhdo.goNuts.requestObjects;

import de.fhdo.goNuts.dto.ProductDTO;

public class AddProductToOrderRequest {
    private ProductDTO product;
    private Long quantity;
    private String token;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
