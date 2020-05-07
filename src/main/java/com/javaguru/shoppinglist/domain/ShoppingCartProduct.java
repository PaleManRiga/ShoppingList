package com.javaguru.shoppinglist.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_product")
@org.hibernate.annotations.Immutable
public class ShoppingCartProduct {

    @Embeddable
    public static class Id implements Serializable{

        @Column(name = "cart_id")
        private Long shoppingCartId;

        @Column(name = "product_id")
        private Long productId;

        public Id(){}

        public Id(Long shoppingCartId, Long productId){
            this.shoppingCartId = shoppingCartId;
            this.productId = productId;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.shoppingCartId.equals(that.shoppingCartId) && this.productId.equals(that.productId);
            }
            return false;
        }

        public int hashCode() {
            return shoppingCartId.hashCode() + productId.hashCode();
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @Column(name = "products_count")
    private Long productsCount;

    @ManyToOne
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    public ShoppingCartProduct(Long productsCount, ShoppingCart shoppingCart, Product product) {
        this.productsCount = productsCount;
        this.shoppingCart = shoppingCart;
        this.product = product;

        this.id.shoppingCartId = shoppingCart.getId();
        this.id.productId = product.getId();

        shoppingCart.getShoppingCartProducts().add(this);
        product.getShoppingCartProducts().add(this);
    }

    public ShoppingCartProduct() {
    }

    @Override
    public String toString() {
        return "ShoppingCartProduct{" +
                "productsCount=" + productsCount +
                ", product=" + product +
                '}';
    }
}