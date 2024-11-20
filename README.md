# Task Management System

A robust task management application built with Spring Boot, featuring user authentication and comprehensive task management capabilities.

---

## Features

### Task Management
- Create, read, update, and delete tasks.
- Filter and sort tasks by priority.
- Track task status.
- Organize tasks based on priority.
- Categorize tasks by subject.

### User Management
- Secure user registration and authentication.
- Manage user profiles.
- Role-based access control.
- Token-based authentication for secure access.

---

## Technology Stack
- **Backend**: Java Spring Boot  
- **Security**: Spring Security  
- **Frontend**: Thymeleaf, HTML, CSS  
- **Database**: MySQL  
- **Build Tool**: Maven  

---

## Security Features
- CSRF Protection: Enabled by default.
- Password Encryption: Securely store user passwords.
- JWT Authentication: Use tokens for secure session management.

## API Endpoints

### Task Operations
| Method | Endpoint                          | Description                  |
|--------|-----------------------------------|------------------------------|
| `POST` | `/api/tasks/createTask`           | Create a new task            |
| `GET`  | `/api/tasks`                      | Retrieve all tasks           |
| `PUT`  | `/api/tasks/completeTask/{id}`    | Mark a task as complete      |
| `DELETE`| `/api/tasks/deleteTask/{id}`     | Delete a task                |
| `PUT`  | `/api/tasks/editTask/{id}`        | Edit a task                  |
| `GET`  | `/api/tasks/sortByPriority`       | Sort tasks by priority       |

### User Operations
| Method | Endpoint                          | Description                  |
|--------|-----------------------------------|------------------------------|
| `POST` | `/api/users/createUser`           | Register a new user          |
| `DELETE`| `/api/users/deleteUser/{id}`     | Delete a user                |
| `PUT`  | `/api/users/editUser/{id}`        | Edit user information        |
| `GET`  | `/api/users`                      | Retrieve all users           |
| `GET`  | `/api/users/{id}`                 | Retrieve a user by ID        |
| `POST` | `/api/users/login`                | User login                   |

---

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/yourusername/task-management.git
```
## Configure Database Properties
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```
## Build the Project
```bash
mvn clean install
```
## Run the Application
```bash
mvn clean install
```

