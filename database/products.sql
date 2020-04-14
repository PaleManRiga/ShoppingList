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

SET GLOBAL time_zone = '+2:00';