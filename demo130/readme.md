 
Online Marketplace Vendor Management System (OMVMS)
A JavaFX-based desktop application that streamlines vendor and product management for an online marketplace. It allows vendors to manage their profiles and products, while customers can browse products, place orders, and leave review

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [API Endpoints](#api-endpoints)
  - [Environment Variables](#environment-variables)



---
Getting Started
This project provides an application to manage vendors, products, and customers within an online marketplace. Follow the steps below to set up, run, and deploy the OMVMS.


Prerequisites
Java Development Kit (JDK) 17 or above
JavaFX libraries
MySQL Database (or any other relational database system)

Installation
Clone the repository:

bash
git clone https://github.com/houssamWaked/omvms.git
Navigate to the project directory:

bash
cd omvms

Set up the database:

Import the database.sql file provided in the repository to create the necessary schema and tables.
Modify the database connection settings in DatabaseHandler.java:
java
private static final String DB_URL = "jdbc:mysql://localhost:3306/omvms";
private static final String DB_USER = "your_username";
private static final String DB_PASSWORD = "your_password";

Build and run the project:

Open your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
Import the project and build it.
Run the application to start the OMVMS.




Usage
This section outlines how to use the application and its functionality.

api endpoints:
CustomerServices:
(api/customers)

addCustomer post (/add)
json body{
  "name": "John Dose",
  "email": "johnddoe@example.com",
  "password": "passdword123",
  "phone": "1234567890",
  "address": "123 Main Street, City, Country"
}


getAllcustomers GET (/all)

getCustomerById GET({customer_id})

authenicateCustomer POST(/log/In)
json body{
  "email": "johndoe@example.com",
  "password": "password123"
}

updateCustomer PUT(/update/{customer_id})
json body{
  "name": "John Updated",
  "email": "johnupdated@example.com",
  "password": "newpassword123",
  "phone": "9876543210",
  "address": "789 Updated Street, City, Country"
}


Vendor Services:
(api/vendors)

addVendor POST (/add)
json body {
  "name": "Vendor A",
  "email": "vendora@example.com",
  "password": "vendorpassword123",
  "phone": "1234567890",
  "address": "Vendor Address"
}

getAllVendors GET(/all)

getVenodrById GET(/{vendor_id})

authenicateVendor POST(/log/In)
json body {
  "email": "vendora@example.com",
  "password": "vendorpassword123"
}


getVendorProductsByVendorId GET (products/{vendor_id})

updateVendor PUT ({vendorId})
json body {
  "name": "Vendor A Updated",
  "email": "vendora_updated@example.com",
  "password": "newpassword123",
  "phone": "1122334455",
  "address": "Updated Vendor Address"
}



CategoryServices:
(api/categories)
getAllCategories GET(/all)
getCategoryByID GET (/{categoryId})
addCategory POST (/add)
json body{
  "categoryName": "New Category Name"
}

UpdateCategory PUT (/{categoryId})
json body{
  "categoryName": "New Category Name"
}

order Services:
(api/orders)
getAllOrders GET(/all)
getOrderByID GET (/{orderId})
addorder POST (/add)
json body{
{
    "customer": {
        "customerId": 1,
        "name": "John Doe",
        "email": "johndoe@example.com"
    },
     "vendor": {
        "vendorId": 1,
        "name": "Vendor Name",
        "email": "vendor@example.com"
    },
    "productId": 4,
    "quantity": 1,
    "totalPrice": 100.0,
   "orderDate": "2024-12-09T12:30:00"

}
}
UpdateOrder PUT (/{OrderId})
json body {
  "orderId": 1,
  "productId": 101,
  "quantity": 3,
  "totalPrice": 750,
  "orderDate": "2024-12-09T12:30:00"
}



Product Services
(api/Products)
getAllProducts GET(/all)
getProductByID GET (/{productId})
getByproductName GET (/name/{product_name})
getProductsByCategoryName GET (/Category/{categoryName)
applyDiscount PUT (/discount/{product_id}?discount={discount percantage})
addProduct POST (/add)
json body{
  "productName": "Product1",
  "price": 25.5,
  "description": "A new product",
  "stock": 100


}

UpdateProduct PUT (/{ProductId})
json body{
  "productName": "Updated Product",
  "price": 30.0,
  "description": "Updated description",
  "stock": 150
  

}

deleteProduct DELETE (/{ProductId})


Review Services
(api/reviews)

getAllReviews GET(/all)
getReviewByProductId GET (/product/{productId})
getReviewsByCustomerId GET (/customer/{customer_id}
getAverageRatingByProduct GET(/product/{productId}/average-rating)
getReviewByID GET (/{reviewId})
addReview POST (/add)
json body{
  "customerId": 1,
  "productId": 2,
  "rating": 5,
  "comment": "Updated review: Excellent product!",
  "reviewDate": "2024-12-09T00:37:02.924"
}

UpdateReview PUT (/{reviewId})
json body{
  "customerId": 1,
  "productId": 2,
  "rating": 5,
  "comment": "Updated review: Excellent product!",
  "reviewDate": "2024-12-09T00:37:02.924"
}




VendorPayment Services
(api/vendor-payments)
getAllVendorPayment GET(/all)
getVendorPaymentByID GET (/{VendorPaymentId})
getVendorPaymentByVendorID GET(/vendors/{vendor_id})





Environment Variables
DB_URL: The URL to connect to your database (e.g., jdbc:mysql://localhost:3306/omvms).
DB_USER: Database username.
DB_PASSWORD: Database password
