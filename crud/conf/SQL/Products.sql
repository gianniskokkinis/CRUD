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



-- insert products
INSERT INTO products (id, name, description, price, date_add, date_upd) VALUES
(1, 'Pizza Margherita', 'Classic Italian pizza with tomato and mozzarella', 7.0, CURRENT_DATE, CURRENT_DATE),
(2, 'Pizza Pepperoni', 'Spicy pepperoni with cheese', 8.5, CURRENT_DATE, CURRENT_DATE),
(3, 'Veggie Pizza', 'Vegetarian pizza with fresh vegetables', 7.5, CURRENT_DATE, CURRENT_DATE),
(4, 'Cheese Burger', 'Beef burger with cheddar cheese', 6.5, CURRENT_DATE, CURRENT_DATE),
(5, 'Chicken Burger', 'Grilled chicken burger', 6.8, CURRENT_DATE, CURRENT_DATE),
(6, 'French Fries', 'Crispy golden fries', 3.5, CURRENT_DATE, CURRENT_DATE),
(7, 'Caesar Salad', 'Salad with romaine, croutons, and parmesan', 5.5, CURRENT_DATE, CURRENT_DATE),
(8, 'Greek Salad', 'Tomatoes, cucumber, feta cheese, olives', 5.0, CURRENT_DATE, CURRENT_DATE),
(9, 'Spaghetti Bolognese', 'Classic spaghetti with meat sauce', 7.5, CURRENT_DATE, CURRENT_DATE),
(10, 'Lasagna', 'Layered pasta with meat and cheese', 8.0, CURRENT_DATE, CURRENT_DATE),
(11, 'Tiramisu', 'Italian coffee-flavored dessert', 4.5, CURRENT_DATE, CURRENT_DATE),
(12, 'Chocolate Cake', 'Rich chocolate layered cake', 4.0, CURRENT_DATE, CURRENT_DATE),
(13, 'Ice Cream Vanilla', 'Vanilla ice cream scoop', 2.5, CURRENT_DATE, CURRENT_DATE),
(14, 'Ice Cream Chocolate', 'Chocolate ice cream scoop', 2.5, CURRENT_DATE, CURRENT_DATE),
(15, 'Sushi Roll', 'Salmon and avocado sushi', 9.0, CURRENT_DATE, CURRENT_DATE),
(16, 'Tempura', 'Deep-fried shrimp and vegetables', 8.5, CURRENT_DATE, CURRENT_DATE),
(17, 'Ramen', 'Japanese noodle soup with pork', 7.5, CURRENT_DATE, CURRENT_DATE),
(18, 'Udon', 'Thick wheat noodles soup', 6.5, CURRENT_DATE, CURRENT_DATE),
(19, 'Pad Thai', 'Stir-fried rice noodles with shrimp', 7.8, CURRENT_DATE, CURRENT_DATE),
(20, 'Spring Rolls', 'Vegetable spring rolls', 3.5, CURRENT_DATE, CURRENT_DATE);

-- insert Categories
INSERT INTO categories (id, catName, product_id) VALUES
(1, 'Pizza', 1),
(2, 'Vegetarian', 1),
(3, 'Pizza', 2),
(4, 'Meat', 2),
(5, 'Vegetarian', 3),
(6, 'Burger', 4),
(7, 'Cheese', 4),
(8, 'Burger', 5),
(9, 'Chicken', 5),
(10, 'Sides', 6),
(11, 'Salad', 7),
(12, 'Salad', 8),
(13, 'Pasta', 9),
(14, 'Pasta', 10),
(15, 'Dessert', 11),
(16, 'Dessert', 12),
(17, 'Dessert', 13),
(18, 'Dessert', 14),
(19, 'Sushi', 15),
(20, 'Sushi', 16),
(21, 'Noodles', 17),
(22, 'Noodles', 18),
(23, 'Thai', 19),
(24, 'Vegetarian', 20);

-- insert Details
INSERT INTO details (id, detail_name, product_id) VALUES
(1, 'Extra Cheese', 1),
(2, 'Thin Crust', 1),
(3, 'Extra Pepperoni', 2),
(4, 'Gluten Free', 3),
(5, 'No Onions', 4),
(6, 'Extra Sauce', 5),
(7, 'Ketchup', 6),
(8, 'Olive Oil', 7),
(9, 'Feta Cheese', 8),
(10, 'Parmesan', 9),
(11, 'Béchamel', 10),
(12, 'Coffee Flavor', 11),
(13, 'Chocolate Chips', 12),
(14, 'Vanilla Syrup', 13),
(15, 'Chocolate Syrup', 14),
(16, 'Wasabi', 15),
(17, 'Soy Sauce', 16),
(18, 'Egg', 17),
(19, 'Tofu', 18),
(20, 'Peanuts', 19);
