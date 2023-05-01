
DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age INT,
    city VARCHAR(50)
);

INSERT INTO customer(name, age, city)
VALUES
    ('Katherine', 27, 'Columbus'),
    ('Alex', 34, 'Columbus'),
    ('Jake', 20, 'Miami'),
    ('John', 30, 'Las Vegas');