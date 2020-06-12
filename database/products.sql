CREATE TABLE products (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          price NUMERIC(12,2),
                          category INT,
                          discount NUMERIC(12,8),
                          description VARCHAR(100) NULL,
                          created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (id)
)
    AUTO_INCREMENT = 1;

SET GLOBAL time_zone = '+0:00';

CREATE TABLE shopping_carts (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                name VARCHAR(100) NOT NULL,
                                description VARCHAR(100) NULL,
                                created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (id)
)
    AUTO_INCREMENT = 1;

create table cart_product (
                              product_id bigint not null,
                              cart_id bigint not null,
                              products_count bigint,
                              primary key (product_id, cart_id)
)

alter table cart_product
    add constraint FK_cart
        foreign key (product_id)
            references products (id)

alter table cart_product
    add constraint FK_product
        foreign key (cart_id)
            references shopping_carts (id)