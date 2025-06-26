# User-Management-Application-


# 👥 User Management Application

A **User Management System** built using **Spring Boot**, **Thymeleaf**, **MySQL**, and **Java** that performs full **CRUD (Create, Read, Update, Delete)** operations on user data. This application demonstrates MVC architecture and is designed to be simple, intuitive, and extendable.

## ✨ Features

* 🔐 Add new users (Create)
* 📋 View all users in a table (Read)
* ✏️ Edit user details (Update)
* ❌ Delete user records (Delete)
* 🎨 Thymeleaf templates with clean and responsive UI
* 💾 Data persistence using Spring Data JPA & MySQL
* 🧠 Business logic handled through service layer

## 🛠 Tech Stack

* **Backend:** Spring Boot, Spring MVC, Spring Data JPA
* **Frontend:** HTML, CSS, Bootstrap, Thymeleaf
* **Database:** MySQL
* **Tools:** Maven, IntelliJ 

## 📁 CRUD Endpoints Overview

| Operation | Method | Endpoint       | Description               |
| --------- | ------ | -------------- | ------------------------- |
| Create    | POST   | `/addUser`     | Add a new user            |
| Read      | GET    | `/users`       | Display all users         |
| Update    | POST   | `/updateUser`  | Update existing user info |
| Delete    | GET    | `/delete/{id}` | Delete a user by ID       |

## 🚀 How to Run

1. Clone the repo:

   ```bash
   git clone https://github.com/thrivendra1/User-Management-Application-.git
   ```
2. Configure your MySQL database in `application.properties`.
3. Run the project using your IDE or:

   ```bash
   mvn spring-boot:run
   ```
4. Navigate to `http://localhost:8080/index`.


