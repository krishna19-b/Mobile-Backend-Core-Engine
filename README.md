# Mobile-Backend-Core-Engine
Used Collections, Jdbc, PostgreSQL, Lambda 

# SQL Query
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    total NUMERIC(10,2) NOT NULL DEFAULT 0,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);


CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
);

INSERT INTO users
(name, email, password, phone)
VALUES
('Bala', 'bala@gmail.com', '123456', '9876543210'),
('Ravi', 'ravi@gmail.com', 'password', '9876500000');


INSERT INTO products
(name, category, price, quantity)
VALUES
('Laptop', 'Electronics', 55000, 10),
('Mobile', 'Electronics', 25000, 20),
('Keyboard', 'Accessories', 1500, 50),
('Mouse', 'Accessories', 800, 100),
('Headphones', 'Accessories', 3000, 30);

# Package Structure

com.mobilebackend
│
├── Main.java
│
├── controller
│   ├── UserController.java
│   ├── ProductController.java
│   └── OrderController.java
│
├── service
│   ├── UserService.java
│   ├── ProductService.java
│   ├── OrderService.java
│   └── AuthenticationService.java
│
├── serviceimpl
│   ├── UserServiceImpl.java
│   ├── ProductServiceImpl.java
│   ├── OrderServiceImpl.java
│   └── AuthenticationServiceImpl.java
│
├── repository
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   └── OrderRepository.java
│
├── dao
│   ├── UserDAO.java
│   ├── ProductDAO.java
│   └── OrderDAO.java
│
├── model
│   ├── User.java
│   ├── Product.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── OrderStatus.java
│
├── exception
│   ├── DataAccessException.java
│   ├── UserNotFoundException.java
│   ├── ProductNotFoundException.java
│   ├── OrderNotFoundException.java
│   ├── AuthenticationException.java
│   └── InsufficientInventoryException.java
│
└── util
    ├── DBConnectionUtil.java
    ├── CollectionUtility.java
    └── AsyncOrderProcessor.java

