# Library Management System

This is a Library Management System built with Spring Boot. It allows you to manage books, members, borrowing records, and fines.

## Features

*   **Book Management:**
    *   Add, update, delete, and retrieve book information.
    *   Search books by ISBN or title.
    *   Associate books with categories.
*   **Member Management:**
    *   Add, update, delete, and retrieve member information.
    *   Search members by name.
*   **Borrowing Records:**
    *   Create and manage records of borrowed books.
    *   Track due dates and return dates.
*   **Fine Management:**
    *   Calculate and manage fines for overdue books.
    *   Record fine payments.
*   **API Documentation:** Swagger integration for easy API exploration and testing.

## Technologies Used

*   **Spring Boot:**  Java-based framework for building microservices and web applications.
*   **Spring Data JPA:** Simplifies data access with repositories.
*   **MySQL:** Relational database for storing application data.
*   **Lombok:** Reduces boilerplate code with annotations.
*   **Swagger (Springdoc-openapi):**  For API documentation and testing.
*   **Maven:** Build automation tool.
*   **Validation:**  Data validation using Jakarta Bean Validation.

## Prerequisites

*   **Java 17 or higher:**  Make sure you have Java 17 or a later version installed.
*   **Maven:**  You'll need Maven to build and run the project.
*   **MySQL Database:**  You'll need a running MySQL database instance.

## Setup and Installation

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/Shubhambhagat3226/Library-management-system.git
    cd library-management-system
    ```

2.  **Configure the Database:**

    *   Create a database named `dct` in your MySQL instance.
    *   Update the `src/main/resources/application.properties` file with your MySQL database credentials (username, password, and port if necessary):

        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/dct
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        ```

3.  **Build the project:**

    ```bash
    ./mvnw clean install
    ```

## Running the Application

1.  **Run the Spring Boot application:**

    ```bash
    ./mvnw spring-boot:run
    ```

    Alternatively, you can run the application from your IDE (e.g., IntelliJ IDEA, Eclipse).

2.  **Access the Application:**

    *   The application will be running at `http://localhost:8080`.

3.  **Access Swagger UI:**

    *   API documentation is available at `http://localhost:8080/swagger-ui/index.html`.

## API Endpoints

Refer to the Swagger UI for a complete list of API endpoints and their usage.  Some key endpoints include:

*   `/books`:  For managing book information.
*   `/members`: For managing member information.
*   `/borrow`: For managing borrowing records.
*   `/fine`: For managing fines.
*   `/book`:  For adding book with categories

## Important Notes

*   The `mvnw` and `mvnw.cmd` files are Maven wrappers, which ensure that the project is built with the correct Maven version.
*   The `spring.jpa.hibernate.ddl-auto=update` property in `application.properties` automatically updates the database schema based on your entity definitions.  In a production environment, you might want to use a more controlled migration strategy.
*   Error handling is implemented using `@RestControllerAdvice` to provide consistent error responses.

