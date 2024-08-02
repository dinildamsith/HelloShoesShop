# Hello Shoes Management System

## Overview

Hello Shoes Management System is a comprehensive application designed to manage the operations of a shoe store. The system allows different types of users (regular and admin) to log in and perform specific actions based on their roles.

## Features

### 1. User Authentication
- **Log In**: Users can log in as either a regular user or an admin user.

### 2. Inventory Management
- **View Inventory**: All users can check the inventory and view data according to each status.
- **Edit Inventory**: Only admin users can edit the inventory data.

### 3. Sales Processing
- **Enter Item Code**: Regular users can enter the item code to proceed with a sale.
- **Confirm Purchase**: Users can confirm the purchase after adding all the items.
- **Payment Processing**:
    - **Card Payment**: Enter the last four digits of the card and bank name to process payment via the incorporated payment gateway.
    - **Cash Payment**: Enter the amount paid by the customer and return the balance if available.
- **Update and Delete Items**: Admin users can update and delete shoe data, except after the payment is confirmed.

### 4. Refund Processing
- **Eligibility**: Items purchased within three days, including tags, are eligible for a refund.
- **Inspection**: A cashier or allocated staff member checks for any damage.
- **Process Refund**: Enter admin credentials to complete the refund process after inspection.

### 5. Customer Management
- **Loyalty Card Details**: Save customer details if they have a loyalty card. Load data to the payment page when valid.
- **Edit Details**: Only admin users can edit customer details.

### 6. Employee and Supplier Management
- **View Details**: All users can view employee and supplier details.
- **Edit Details**: Only admin users can edit employee and supplier details.

## System Requirements

### Primary Services
1. **Sale Service**: Manages the sales operations.
2. **Suppliers Service**: Handles supplier information.
3. **Customer Service**: Manages customer details.
4. **Employee Service**: Handles employee information.
5. **Inventory Service**: Manages inventory data.
6. **Admin Panel Service**: Provides admin functionalities.
7. **User Service**: Manages user authentication and roles.

### Back End Technologies
- **Spring Boot**: Framework for building the application.
- **Spring Data**: For data access and management.
- **Spring Web MVC**: For web-based interactions.
- **Spring Validation**: For validating user inputs.
- **Spring Security**: For securing the application.
- **Lombok**: To reduce boilerplate code.
- **Model Mapper**: For object mapping.
- **Jackson**: For JSON processing.
- **MySQL**: As the database.
- **JWT**: For handling JSON Web Tokens for authentication.

## Getting Started

To get started with the Hello Shoes Management System, follow these steps:

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/dinildamsith/Hello_Shoes_Pvt._Ltd_BackEnd.git
    ```

2. **Navigate to the Project Directory**:
    ```sh
    cd Hello_Shoes_Pvt._Ltd_BackEnd
    ```

3. **Build the Project**:
    ```sh
    ./mvnw clean install
    ```

4. **Run the Application**:
    ```sh
    ./mvnw spring-boot:run
    ```

## Configuration

Configure the application by editing the `application.properties` file located in the `src/main/resources` directory.

```properties
# Database Configuration
spring.datasource.url=your_database_url
spring.datasource.username=your_username
spring.datasource.password=your_password

# JWT Configuration
token.key==your_jwt_secret


## License
This project is licensed under the MIT License. See the LICENSE file for more details.




