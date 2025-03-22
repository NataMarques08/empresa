CREATE TABLE employee
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    lastname VARCHAR(50),
    occupation VARCHAR(100),
    salary DECIMAL(10,2),
    admission DATE,
    termination DATE,
    gender char(2)
);