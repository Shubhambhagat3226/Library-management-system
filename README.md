# Library Management System API 📚

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) <!-- Example License Badge -->
[![Java Version](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Build-Maven-orange.svg)](https://maven.apache.org/)

A comprehensive RESTful API built with Spring Boot to manage books, members, categories, borrowing records, and fines within a library system.

## ✨ Features

*   **📖 Book Management:** Add, update, delete, and retrieve book details. Search books by ISBN or title.
*   **🗂️ Category Management:** Associate books with specific categories (e.g., Fiction, Science, History).
*   **👥 Member Management:** Add, update, delete, and retrieve library member information. Search members by name.
*   **🔄 Borrowing & Returning:** Create records for borrowed books, track due dates, and handle book returns.
*   **💰 Fine Management:** Automatically calculate fines for overdue books and manage fine payments.
*   **📄 API Documentation:** Integrated Swagger UI for easy API exploration and testing.
*   **✔️ Input Validation:** Ensures data integrity through request validation.
*   **⚠️ Error Handling:** Provides clear and consistent error responses.

## 🛠️ Technology Stack

*   **Framework:** Spring Boot 3.4.3
*   **Language:** Java 17
*   **Data Persistence:** Spring Data JPA / Hibernate
*   **Database:** MySQL
*   **Build Tool:** Maven
*   **API Documentation:** Springdoc OpenAPI (Swagger UI)
*   **Utilities:** Lombok
*   **Validation:** Jakarta Bean Validation

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

*   **Java Development Kit (JDK):** Version 17 or higher.
*   **Apache Maven:** To build and manage the project dependencies.
*   **MySQL Database:** A running instance of MySQL server.

## 🚀 Getting Started

Follow these steps to get the project up and running on your local machine.

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/Shubhambhagat3226/Library-management-system.git
    cd library-management-system
    ```

2.  **Configure the Database:**
    *   Connect to your MySQL instance.
    *   Create a database named `dct`:
        ```sql
        CREATE DATABASE dct;
        ```
    *   Open the `src/main/resources/application.properties` file.
    *   Update the database connection properties with your MySQL credentials. **Important:** Note that the default configuration uses port `100` for MySQL. Change this if your MySQL runs on a different port (e.g., the standard `3306`).

        ```properties
        # MySQL configuration (Update username, password, and port if needed)
        spring.datasource.url=jdbc:mysql://localhost:100/dct
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        ```

3.  **Build the Project:**
    Use the Maven wrapper to compile the code and download dependencies.
    *   On Linux/macOS:
        ```bash
        ./mvnw clean install
        ```
    *   On Windows:
        ```bash
        .\mvnw.cmd clean install
        ```
    *(The `mvnw` script ensures you use the Maven version intended for the project)*

4.  **Run the Application:**
    You can run the application using the Maven wrapper:
    *   On Linux/macOS:
        ```bash
        ./mvnw spring-boot:run
        ```
    *   On Windows:
        ```bash
        .\mvnw.cmd spring-boot:run
        ```
    Alternatively, you can run the `LibraryManagmentSystemApplication` class directly from your IDE (like IntelliJ IDEA or Eclipse).

## 🌐 Accessing the Application

*   **API Base URL:** `http://localhost:8080`
*   **Swagger UI (API Documentation):** `http://localhost:8080/swagger-ui/index.html`

Open the Swagger UI link in your browser to explore and interact with all available API endpoints.

## 🧭 API Endpoints Overview

The API provides endpoints for managing different resources:

*   `/books`: Operations related to books (CRUD, search).
*   `/book/add`: Add a book along with its categories.
*   `/members`: Operations related to library members (CRUD, search).
*   `/borrow`: Operations for borrowing and returning books.
*   `/fine`: Operations for viewing and paying fines.

Refer to the **Swagger UI** for detailed information on request/response formats, parameters, and specific endpoint paths.

## ⚙️ Configuration Notes

Key configurations can be found in `src/main/resources/application.properties`:

*   `spring.datasource.*`: Database connection details.
*   `spring.jpa.hibernate.ddl-auto=update`: Hibernate automatically updates the database schema based on your entity definitions. For production environments, consider using migration tools like Flyway or Liquibase for more control.
*   `library.fine.rate-per-day=10`: Sets the daily fine amount for overdue books (currently 10 units of currency).

## 🙌 Contributing

Contributions are welcome! If you'd like to contribute, please feel free to fork the repository, make your changes, and submit a pull request.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details (or choose an appropriate license if MIT isn't desired).
