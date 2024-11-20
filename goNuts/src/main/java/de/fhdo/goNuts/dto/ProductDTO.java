package de.fhdo.goNuts.dto;

import java.util.Objects;

public class ProductDTO {
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String image;

    public ProductDTO() {}

    public ProductDTO(Long id, String name, String description, Double price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO product)) return false;

        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, image);
    }
}
