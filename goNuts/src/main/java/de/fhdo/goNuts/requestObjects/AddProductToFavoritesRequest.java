package de.fhdo.goNuts.requestObjects;

import de.fhdo.goNuts.dto.ProductDTO;

public class AddProductToFavoritesRequest {
    private ProductDTO product;
    private String token;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
