CREATE TABLE employee
(
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(30),
    lastname VARCHAR(50),
    occupation VARCHAR(100),
    salary DECIMAL(10,2),
    admission DATE,
    termination DATE,
    gender char(2),
);