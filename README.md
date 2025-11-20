# Library Management System

A comprehensive Spring Boot REST API for managing library operations including student enrollment, book catalog management, author records, and book transaction tracking. Features include automated library card issuance with 4-year expiration, book issue/return processing with card validation, genre-based filtering, and transaction history.

## 🚀 Features

- **Student Management**: Add, update, delete, and retrieve student information
- **Author Management**: Manage author details and track their published books
- **Book Management**: Organize books by genre, track availability, and manage stock
- **Library Card System**: Automated card issuance with 4-year expiration and status tracking
- **Transaction Management**: Issue and return books with comprehensive validation
- **API Documentation**: Built-in Swagger UI for easy API exploration
- **Error Handling**: Custom exceptions for robust error management

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.3.3
- **Language**: Java 22
- **Database**: MySQL
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **Additional Libraries**: Lombok, Spring Mail

## 📋 Project Structure

```
src/main/java/com/example/LibraryManagementSystem/
├── controller/          # REST API endpoints
│   ├── StudentController.java
│   ├── BookController.java
│   ├── AuthorController.java
│   └── TransactionController.java
├── service/            # Business logic
│   ├── StudentService.java
│   ├── BookService.java
│   ├── AuthorService.java
│   ├── TransactionService.java
│   └── impl/          # Service implementations
├── repository/        # Data access layer
│   ├── StudentRepository.java
│   ├── BookRepository.java
│   ├── AuthorRepository.java
│   ├── CardRepository.java
│   └── TransactionRepository.java
├── entity/           # JPA entities
│   ├── Student.java
│   ├── Book.java
│   ├── Author.java
│   ├── Card.java
│   └── Transaction.java
├── dto/              # Data Transfer Objects
│   ├── requestdto/   # Request DTOs
│   └── responsedto/  # Response DTOs
├── enums/            # Enumerations
│   ├── Gender.java
│   ├── Department.java
│   ├── Genre.java
│   ├── CardStatus.java
│   └── TransactionStatus.java
└── expections/       # Custom exceptions
```

## 🏗️ Database Schema

### Core Relationships

```
Student (1) ←→ (1) Card
Card (1) ←→ (Many) Book
Card (1) ←→ (Many) Transaction
Book (Many) ← (1) Author
Book (1) ←→ (Many) Transaction
```

### Key Entities

- **Student**: Student information with enrollment details
- **Card**: Library card with activation/expiration tracking
- **Book**: Book details including ISBN, genre, and issue status
- **Author**: Author information and publication history
- **Transaction**: Records of book issues and returns with status

## 📚 API Endpoints

### Student Endpoints
- `GET /student` - Retrieve all students
- `POST /student` - Add new student
- `GET /student/{id}` - Get student by ID
- `PUT /student` - Update student information
- `DELETE /student/{id}` - Delete student

### Book Endpoints
- `GET /book` - Retrieve all books
- `POST /book` - Add new book
- `GET /book/{id}` - Get book by ID
- `GET /book/genre/{genre}` - Find books by genre
- `DELETE /book/{id}` - Delete book

### Author Endpoints
- `GET /author` - Retrieve all authors
- `POST /author` - Add new author
- `GET /author/{id}` - Get author by ID
- `PUT /author` - Update author information
- `DELETE /author/{id}` - Delete author

### Transaction Endpoints
- `POST /transaction/issue` - Issue a book to student
- `POST /transaction/return` - Return a book from student
- `GET /transaction` - Retrieve all transactions

## ⚙️ Setup & Installation

### Prerequisites
- Java 22 or higher
- Maven 3.6+
- MySQL 8.0+

### Configuration

1. **Update `application.properties`**:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

2. **Email Configuration** (Optional):
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
```

### Build & Run

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📖 API Documentation

Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## 🔄 Key Workflows

### Issue a Book
1. Validate student exists
2. Verify library card is active
3. Check book availability
4. Create transaction record
5. Update book status to issued

### Return a Book
1. Validate student exists
2. Verify book is currently issued
3. Update book status to available
4. Create return transaction
5. Update card fine status if applicable

## 🚨 Exception Handling

- `StudentNotFoundException` - Student not found
- `BookNotFoundException` - Book not found
- `AuthorNotFoundException` - Author not found
- `CardNotFoundException` - Card not found
- `CardExpiredException` - Library card has expired
- `BookIsAleardyIssuedException` - Book already issued
- `BookIsNotIssuedException` - Book not currently issued

## 📊 Enumerations

- **Gender**: MALE, FEMALE, OTHER
- **Department**: CSE, MECHANICAL, ELECTRICAL, CIVIL
- **Genre**: FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY
- **CardStatus**: ACTIVATED, EXPIRED, BLOCKED
- **TransactionStatus**: SUCCESS, FAILED, PENDING

## 🔐 Security Considerations

- Store sensitive credentials in environment variables
- Use Spring Security for API authentication (future enhancement)
- Implement role-based access control for different user types
- Validate all input data before processing

## 🐛 Known Issues & Future Enhancements

- Email notifications currently disabled
- Add pagination for large result sets
- Implement Spring Security for API authentication
- Add unit and integration tests
- Add fine calculation system for overdue books
- Implement book reservation system

## 👨‍💻 Developer

**Prashanth Premchand**

## 📄 License

This project is open source and available under the MIT License.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

---

For more information or support, please contact the development team.
