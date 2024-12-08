Online Marketplace Vendor Management System (OMVMS)
Project Overview
The Online Marketplace Vendor Management System (OMVMS) is a JavaFX-based desktop application designed to streamline vendor and product management for an online marketplace. It allows vendors to manage their profiles and products, customers to browse products and make orders

Features
Vendors
Vendor registration and login.
Manage products (add, update, and delete).
View payments received.
view vendor products reviews
Customers
Customer registration and login.
Browse and search products by vendor or category.
Place orders and write reviews for purchased products.


Project Architecture
Database Design
The application uses a relational database with the following tables:

Vendor: Stores vendor details.
Product: Stores product details and links to vendors.
Order: Tracks customer orders.
Customer: Customer account and contact details.
Review: Customer reviews for products.
Category: Product categorization.
Vendor Payment: Payment records for vendors.
Key Relationships
Vendors to Products: One-to-Many
Products to Orders: Many-to-Many
Customers to Orders: One-to-Many
Vendors to Payments: One-to-Many
Technology Stack
Frontend: JavaFX for the user interface.
Backend: Java with JDBC for database interaction.
Database: MySQL (or any other relational database system).
Setup Instructions
Prerequisites
Java Development Kit (JDK) 17 or above.
JavaFX libraries.
A database management system (e.g., MySQL).
A preferred IDE (e.g., IntelliJ IDEA, Eclipse).
Installation Steps
Clone this repository:
bash
git clone https://github.com/houssamWaked/omvms.git
Set up the database:
Import the database.sql file provided to create the required schema and tables.
Configure the database connection in DatabaseHandler.java:
java
private static final String DB_URL = "jdbc:mysql://localhost:3306/omvms";
private static final String DB_USER = "your_username";
private static final String DB_PASSWORD = "your_password";
Build and run the project using your IDE.
