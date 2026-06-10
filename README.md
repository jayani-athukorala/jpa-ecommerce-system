# 🛒 E-Commerce Platform (Spring Boot + JPA) Part4

A **Spring Boot + Spring Data JPA backend system** implementing a full e-commerce workflow using a **clean layered architecture** with DTOs, services, and mappers.

---
## 🚀 Project Overview

This project demonstrates how to build a scalable backend system using Spring Boot with a strong focus on **clean architecture and separation of concerns**.

The system models a complete e-commerce flow including:

- Customer management
- Product catalog with categories
- Promotion-based pricing
- Order processing with stock validation

It emphasizes **business logic encapsulation inside service layers**, while keeping entities and repositories isolated from the API layer.

---

## 🧱 Architecture

The project follows a layered architecture:

```
Service Layer (business logic)
        ↓
Mapper Layer (DTO ↔ Entity conversion)
        ↓
Repository Layer (data access)
        ↓
Database
```
### Key Design Principles

- Entities are not exposed outside the service layer
- DTOs are used for all input/output operations
- Business logic is centralized in services
- Mappers handle all transformations
- Transactions ensure consistency in order processing

---

## ⚙️ Core Features

### 👤 Customer Management

- Register customer with validation
- Fetch and update customer data
- Email uniqueness enforcement

### 📦 Product Catalog

- Create products with category validation
- Search products by name
- Manage stock and pricing

### 🏷️ Category Management

- Organize products into categories
- Prevent duplicate categories

### 🎯 Promotion System

- Time-based active promotions
- Best discount selection per product
- Discount applied during order processing

### 🧾 Order Processing

- Place orders with multiple items
- Stock validation before purchase
- Promotion-based discount calculation
- Price snapshot at purchase time
- Fully transactional order creation

---
## 🔄 Business Flow

- Customer places order
- System validates customer and products
- Stock availability is checked
- Active promotions are evaluated
- Discount is calculated via PromotionService
- Order + OrderItems are created
- Stock is updated
- Transaction commits

---

## 🧠 Key Learning Outcomes

- Designing layered Spring Boot architecture
- Using DTOs (Java Records) for API safety
- Implementing service-based business logic
- Writing reusable mapper components
- Handling complex order workflows
- Applying `@Transactional` for consistency
- Managing `Many-to-Many` relationships (Products ↔ Promotions)

---

## 📌 Workshop Documents
[Workshop Document - Part 3](SpringBoot-DataJPA-Service-Layer-Workshop-Part3.md)

[Workshop Document - Part 2](SpringBoot-DataJPA-Workshop-Part2.md)

[Workshop Document - Part 1](SpringBoot-DataJPA-Workshop-Part1.md)

---

## 🧱 Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Maven
- Lombok

---

## 📂 Updated Project Structure

```
com.example.ecommerce
├── entity
├── controller
├── exception
├── repository
├── service
│   ├── impl
│   ├── CustomerService
│   ├── ProductService
│   ├── OrderService
│   └── PromotionService
├── dto
│   ├── request
│   └── response
├── mapper
├── runner
├── api.http
└── JpaEcommerceSystemApplication
```
---

## 🚀 How to Run

```
git clone https://github.com/jayani-athukorala/jpa-ecommerce-system.git
cd jpa-ecommerce-system

docker compose up -d
mvn clean install
mvn spring-boot:run
```

## Expected Output
```
Request body: CustomerRequest[firstName=Jayani, lastName=Athukorala, email=jayani@test.com, password=12345678, street=Teleborg, city=Vaxjo, zipCode=35257]
Response body: CustomerResponse[id=1, fullName=Jayani Athukorala, email=jayani@test.com, addressResponse=AddressResponse[id=1, street=Teleborg, city=Vaxjo, zipCode=35257]]
Request body: CustomerRequest[firstName=John, lastName=Smith, email=john@test.com, password=12345678, street=Main Street, city=Stockholm, zipCode=11122]
Response body: CustomerResponse[id=2, fullName=John Smith, email=john@test.com, addressResponse=AddressResponse[id=2, street=Main Street, city=Stockholm, zipCode=11122]]
Request body: CustomerRequest[firstName=Sara, lastName=Andersson, email=sara@test.com, password=12345678, street=Park Road, city=Gothenburg, zipCode=41111]
Response body: CustomerResponse[id=3, fullName=Sara Andersson, email=sara@test.com, addressResponse=AddressResponse[id=3, street=Park Road, city=Gothenburg, zipCode=41111]]
Id: 1
Response body: CustomerResponse[id=1, fullName=Jayani Athukorala, email=jayani@test.com, addressResponse=AddressResponse[id=1, street=Teleborg, city=Vaxjo, zipCode=35257]]
Request body: CustomerRequest[firstName=Jayani, lastName=Athukorala, email=jayani@test.com, password=456789123, street=XYZ, city=Malmo, zipCode=21120]
Response body: CustomerResponse[id=1, fullName=Jayani Athukorala, email=jayani@test.com, addressResponse=AddressResponse[id=1, street=XYZ, city=Malmo, zipCode=21120]]
Request body: CategoryRequest[name=Electronics]
Response body: CategoryResponse[id=1, name=Electronics]
Request body: CategoryRequest[name=Books]
Response body: CategoryResponse[id=2, name=Books]
Request body: ProductRequest[name=iPhone 15, stock=20, price=1200, categoryId=1]
Response body: ProductResponse[id=1, name=iPhone 15, stock=20, price=1200, categoryName=Electronics]
Request body: ProductRequest[name=Spring Boot Book, stock=15, price=40, categoryId=2]
Response body: ProductResponse[id=2, name=Spring Boot Book, stock=15, price=40, categoryName=Books]
Request body: PromotionRequest[code=IPHONE10, startDate=2026-06-01, endDate=2026-06-30, discountPercentage=10, productIds=[1]]
Response body: PromotionResponse[id=1, code=IPHONE10, startDate=2026-06-01, endDate=2026-06-30, discountPercentage=10, products=[1]]
Request body: PromotionRequest[code=BOOK20, startDate=2026-06-01, endDate=2026-07-01, discountPercentage=20, productIds=[2]]
Response body: PromotionResponse[id=2, code=BOOK20, startDate=2026-06-01, endDate=2026-07-01, discountPercentage=20, products=[2]]
Request body: PromotionRequest[code=SUMMER2026, startDate=2026-06-01, endDate=2026-08-01, discountPercentage=15, productIds=[1, 2]]
Response body: PromotionResponse[id=3, code=SUMMER2026, startDate=2026-06-01, endDate=2026-08-01, discountPercentage=15, products=[1, 2]]
Request body: OrderRequest[customerId=1, items=[OrderItemRequest[productId=1, quantity=1]]]
Response body: OrderResponse[id=1, orderDate=2026-06-10T09:41:54.072372200Z, orderStatus=null, items=[OrderItemResponse[id=1, quantity=1, priceAtPurchase=1020.0000, product=ProductResponse[id=1, name=iPhone 15, stock=19, price=1200.00, categoryName=Electronics]]]]
Request body: OrderRequest[customerId=2, items=[OrderItemRequest[productId=2, quantity=2]]]
Response body: OrderResponse[id=2, orderDate=2026-06-10T09:41:54.576911900Z, orderStatus=null, items=[OrderItemResponse[id=2, quantity=2, priceAtPurchase=32.0000, product=ProductResponse[id=2, name=Spring Boot Book, stock=13, price=40.00, categoryName=Books]]]]
Request body: OrderRequest[customerId=3, items=[OrderItemRequest[productId=1, quantity=1], OrderItemRequest[productId=2, quantity=3]]]
Response body: OrderResponse[id=3, orderDate=2026-06-10T09:41:54.846648600Z, orderStatus=null, items=[OrderItemResponse[id=3, quantity=1, priceAtPurchase=1020.0000, product=ProductResponse[id=1, name=iPhone 15, stock=18, price=1200.00, categoryName=Electronics]], OrderItemResponse[id=4, quantity=3, priceAtPurchase=32.0000, product=ProductResponse[id=2, name=Spring Boot Book, stock=10, price=40.00, categoryName=Books]]]]
Request body: OrderRequest[customerId=1, items=[OrderItemRequest[productId=1, quantity=5]]]
Response body: OrderResponse[id=4, orderDate=2026-06-10T09:41:55.132456400Z, orderStatus=null, items=[OrderItemResponse[id=5, quantity=5, priceAtPurchase=1020.0000, product=ProductResponse[id=1, name=iPhone 15, stock=13, price=1200.00, categoryName=Electronics]]]]
Request body: OrderRequest[customerId=2, items=[OrderItemRequest[productId=1, quantity=2], OrderItemRequest[productId=2, quantity=4]]]
Response body: OrderResponse[id=5, orderDate=2026-06-10T09:41:55.454673100Z, orderStatus=null, items=[OrderItemResponse[id=6, quantity=2, priceAtPurchase=1020.0000, product=ProductResponse[id=1, name=iPhone 15, stock=11, price=1200.00, categoryName=Electronics]], OrderItemResponse[id=7, quantity=4, priceAtPurchase=32.0000, product=ProductResponse[id=2, name=Spring Boot Book, stock=6, price=40.00, categoryName=Books]]]]

```