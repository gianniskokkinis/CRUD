-- Table: categories
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    catName VARCHAR(255) NOT NULL
);

-- Table: products
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    price DOUBLE NOT NULL,
    date_add DATE,
    date_upd DATE,
    category_id INT,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- Table: details
CREATE TABLE details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detail_name VARCHAR(255) NOT NULL,
    product_id INT,
    CONSTRAINT fk_detail_product FOREIGN KEY (product_id) REFERENCES products(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

 
INSERT INTO categories (catName) VALUES ("Pizza");
INSERT INTO categories (catName) VALUES ("Coffee");
