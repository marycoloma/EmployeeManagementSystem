# Employee Management System (EMS)

The Employee Management System is a Java console application that allows users to manage employee records stored in a MySQL database. It solves the problem of manually tracking employee information by providing a simple system to add, view, update, and delete employee data.

## What the System Does

The system performs basic employee management operations:

- Add new employees
- Display all employees
- Update existing employee information
- Delete employees from the database

## Problem It Solves

Without a system, employee information must be tracked manually, which is time-consuming and prone to errors. This project provides a structured way to store and manage employee records using a database and object‑oriented programming principles.

## How the System Works (Brief)

The program runs in the console and uses menu options to guide the user.  
When an option is selected, the system communicates with the database through DAO (Data Access Object) classes.  
Each operation (add, view, update, delete) sends SQL queries to the MySQL database, and the results are displayed back to the user.

The system also includes input validation and custom exceptions to ensure data integrity and proper error handling.
