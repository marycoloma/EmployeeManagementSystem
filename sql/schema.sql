-- Database creation
CREATE DATABASE IF NOT EXISTS employee_management;
USE employee_management;

-- Table for Departments
CREATE TABLE IF NOT EXISTS departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table for Employees (includes Person attributes via inheritance mapping)
CREATE TABLE IF NOT EXISTS employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    access_level VARCHAR(50),
    salary DOUBLE,
    department_id INT,
    hire_date DATETIME,
    termination_date DATETIME,
    position VARCHAR(100),
    FOREIGN KEY (department_id) REFERENCES departments(department_id) ON DELETE SET NULL
);

-- Sample Data for testing
INSERT INTO departments (name) VALUES ('HR'), ('IT'), ('Finance');
