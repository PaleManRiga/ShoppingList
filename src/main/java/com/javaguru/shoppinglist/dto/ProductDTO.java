package com.javaguru.shoppinglist.dto;

import com.javaguru.shoppinglist.service.Category;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String stringCategory;
    private BigDecimal discount;
    private String description;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, BigDecimal price, String stringCategory, BigDecimal discount, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stringCategory = stringCategory;
        this.discount = discount;
        this.description = description;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStringCategory() {
        return stringCategory;
    }

    public void setStringCategory(String stringCategory) {
        this.stringCategory = stringCategory;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(stringCategory, that.stringCategory) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, stringCategory, discount, description);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stringCategory='" + stringCategory + '\'' +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                '}';
    }
}