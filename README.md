# 📚 Library Management System (LMS)

A Java-based **Library Management System** (LMS) designed to manage books, users, fines, and administrative tasks. This is a console-based application that simulates the core functionality of a real-world library system with file handling for persistence.

---

## 📂 Project Structure

lmsystem/
├── admin/
│ ├── Admin.java
│ └── Admin.class
│
├── model/
│ ├── Book.java
│ ├── Book.class
│ ├── Fine.java
│ ├── Fine.class
│ ├── Person.java
│ ├── Person.class
│ ├── User.java
│ └── User.class
│
├── operations/
│ ├── BookOperations.java
│ ├── BookOperations.class
│ ├── FineOperations.java
│ ├── FineOperations.class
│ ├── UserOperations.java
│ └── UserOperations.class
│
├── LMS.java # Main entry point
├── books.txt # Data file for books
├── users.txt # Data file for users
└── fines.txt # Data file for fines


---

## 💡 Features

- 📘 **Book Management**
  - Add, remove, search, and list books
  - Track book availability

- 👤 **User Management**
  - Register and manage library users
  - Store user info in `users.txt`

- 💸 **Fine Management**
  - Calculate and record fines for late returns
  - Manage fine data via `fines.txt`

- 🔐 **Admin Interface**
  - Admin-specific privileges to manage all data

- 💾 **File Handling**
  - Persistent storage using `.txt` files

---

## 🚀 How to Run

### 1. Compile the Project

Use your terminal or command prompt:
javac LMS.java

Run the Project
java LMS

---
## 🧪 Requirements
Java JDK 8 or above

A terminal or IDE like IntelliJ IDEA, Eclipse, or VS Code with Java support
---
## 📝 Sample Data Files
books.txt → contains list of books (title, author, ISBN, availability)

users.txt → stores registered user information

fines.txt → tracks fines for overdue books

Each text file acts as a mock database.
---
## 📄 License
This project is built for educational purposes only.
Feel free to use, modify, and enhance it as needed.
---
 ### 👨‍💻 Author
Made with 💙 by Sarth00718
