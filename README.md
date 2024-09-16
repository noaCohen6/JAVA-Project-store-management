# Store Management System

## Project Description
This system manages product sales for a store, including inventory management, orders, and shipments. The system supports various types of products and allows comprehensive management of store operations.

## System Requirements
- Java JDK 11 or higher

## Features
The Store Management System offers the following key features:

1. **Product Management**
   - Add new products (online, in-store, or wholesale)
   - Remove existing products
   - Update product inventory
   - Display detailed information for specific products
   - View all products in the system

2. **Order Management**
   - Add new orders for products
   - Undo (remove) recent orders
   - Automatic inventory updates upon order creation or removal

3. **Shipping and Delivery**
   - Support for multiple shipping companies (e.g., DHL, FedEx)
   - Calculation of shipping fees for standard and express shipping
   - Handling of different shipping rules based on product weight and destination

4. **Invoice Generation**
   - Create invoices in different formats (for customers and accountants)
   - Include VAT calculations for customer invoices
   - Include profit calculations for accountant invoices

5. **Financial Tracking**
   - Calculate and track profit per product and for the entire store
   - Support for different currencies (handling dollar to shekel conversion)

6. **Data Management and Backup**
   - Backup the entire system state
   - Restore the system from a previous backup
   - Load sample data for testing and demonstration purposes

7. **Flexible Architecture**
   - Support for easy addition of new product types, order types, or shipping companies
   - Use of design patterns to ensure scalability and maintainability

## Class Structure and Design Patterns

### Company (Singleton, Memento, Observer)
- Main class managing the entire system
- Implements Singleton pattern for a single point of entry
- Uses Memento pattern for system backup and restoration
- Implements Observer pattern for order and stock management
- Key methods: addProduct(), addDeliveryCompany(), addOrder(), removeOrder(), removeProduct(), updateStock(), createInvoice(), sumProfit(), saveToMemento(), restoreFromMemento()

### Products (Abstract)
- Base class for all product types
- Attributes: product_name, cost_price, selling_price, stock, catalogNumber, Orders
- Methods: addOrder(), removeOrder(), sumProfit(), compareTo()

### SoldThroughWebsite extends Products
- Represents products sold online
- Additional attributes: dest_country, product_weight

### SoldInStore extends Products
- Represents products sold in-store

### SoldToWholesalers extends Products
- Represents products sold to wholesalers

### Order (Abstract)
- Base class for all order types
- Attributes: product, customer, numOfProduct, orderCatalogNumber

### OrderInStore, OrderInWebsite, OrderInWholesalers extend Order
- Specific implementations for different order types

### Customer
- Represents a customer
- Attributes: customer_name, mobile

### DeliveryCompany (Abstract)
- Base class for delivery companies
- Abstract methods: ShippingFeesStandard(), ShippingFeesExpress()

### DHL, FedEx extend DeliveryCompany
- Specific implementations for different shipping companies
- Implement shipping fee calculations based on company-specific rules

### Interfaces
- ExpressShipping, StandardShipping: Define shipping fee calculation methods
- FormatForCustomer, FormatForAccountant: Define invoice formatting methods
- OrderStockObserver: Defines methods for updating stock on order changes
- ProfitCalculation: Defines method for calculating profit

### Factory Pattern Implementations
- DeliveryCreator: Creates DeliveryCompany objects
- OrderCreator: Creates Order objects
- ProductsCreator: Creates Products objects

## Design Patterns Used

1. **Singleton (Company class)**
   - Ensures a single instance of the Company class
   - Provides a global point of access to the system
   - Implemented in the Company class, which is the main entry point for the application

2. **Factory (DeliveryCreator, OrderCreator, ProductsCreator)**
   - Used for creating objects without specifying their exact classes
   - Allows for flexible creation of products, orders, and delivery companies
   - Implemented through the Creator interface and specific creator classes for each object type

3. **Memento (Company and CompanyMemento classes)**
   - Used for backing up and restoring the system state
   - Allows for easy implementation of the backup and restore functionality
   - Implemented in the Company class with methods saveToMemento() and restoreFromMemento()

4. **Observer (Company class and OrderStockObserver interface)**
   - Used for updating stock when orders are created or removed
   - Ensures automatic stock updates without user intervention
   - Implemented through the OrderStockObserver interface and corresponding methods in the Company class

These design patterns enhance the system's flexibility, maintainability, and scalability, allowing for easy addition of new product types, order types, or shipping companies in the future.

## Class Diagram Overview
The system's architecture includes the following key relationships:
- Company class aggregates Products, Orders, and DeliveryCompany objects
- Products are specialized into SoldThroughWebsite, SoldInStore, and SoldToWholesalers
- Orders are linked to Products and Customers
- DeliveryCompany is specialized into DHL and FedEx
- Factory pattern is used to create Products, Orders, and DeliveryCompany objects
- Observer pattern connects Company with product stock management
- Memento pattern is implemented within the Company class for system state management

This structure allows for a flexible and extensible system that can easily accommodate new features and modifications in the future.

