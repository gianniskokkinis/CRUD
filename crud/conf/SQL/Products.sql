-- Table: products
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    price DOUBLE NOT NULL,
    date_add DATE,
    date_upd DATE
);


-- Table: categories
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    catName VARCHAR(255) NOT NULL,
    product_id INT,
    CONSTRAINT fk_category_product FOREIGN KEY (product_id) REFERENCES products(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Table: details (μένει όπως ήταν)
CREATE TABLE details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detail_name VARCHAR(255) NOT NULL,
    product_id INT,
    CONSTRAINT fk_detail_product FOREIGN KEY (product_id) REFERENCES products(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
