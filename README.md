# Academic Resource & Task Management System

A modular Command-Line Interface (CLI) application developed in Java to streamline student deadlines, user authentication, and academic resource indexing.

---

## 1. Project Overview

The Academic Resource & Task Management System is a lightweight Java console application designed for students and educators. It eliminates external database dependencies by utilizing a local CSV-based persistence layer, making it portable and simple to deploy on any system supporting Java SE.

---

## 2. System Features

* **User Authentication System**
  * Registration and login workflows for Students and Faculty members.
  * Input validation and custom exception handling for authentication and data errors.

* **Task & Assignment Manager**
  * Assign personal tasks linked directly to unique User IDs.
  * Set deadline dates, track assigned tasks, and update completion statuses.

* **Central Resource Directory**
  * Share and index study materials, reference links, and reading lists by topic category.
  * System-wide directory accessible to all authenticated users.

* **Persistent File Storage**
  * Flat-file stream handler for serializing model objects directly to local CSV files.
  * Automatic directory and data file creation on application launch.

---

## 3. Project Directory Structure

```text
vityarthi-java-project/
├── README.md                   # System documentation and usage guide
├── statement.md                # Problem statement and project scope
├── data/                       # Local file persistence (.csv)
│   ├── users.csv
│   ├── tasks.csv
│   └── resources.csv
└── src/                        # Source code root
    └── com/vityarthi/
        ├── Main.java           # Main application controller
        ├── exception/          # Custom exception handlers
        │   ├── InvalidDataException.java
        │   └── UserNotFoundException.java
        ├── model/              # Object data models
        │   ├── User.java
        │   ├── Task.java
        │   └── Resource.java
        ├── service/            # Core business modules
        │   ├── AuthManager.java
        │   ├── TaskManager.java
        │   └── ResourceManager.java
        └── util/               # File operations and validation utilities
            ├── DataHandler.java
            └── ValidationUtils.java
