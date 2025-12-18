# OTT Service Programme Catalogue

This project implements the **database layer of an OTT (Over-The-Top) service programme catalogue** using **MySQL and Java (JDBC)**.
It demonstrates database schema design, sample data insertion, schema alterations, and executing queries through SQL scripts run via a Java program.

---

## 📌 Project Description

The project simulates the catalogue layer of an OTT platform, managing:

- Movies, TV Shows, and Documentaries
- Programme genres
- Cast members and their roles
- Episodes for series-based programmes

All SQL operations are executed using the Java program `RunSQLScript.java` and the MySQL JDBC driver.

---

## 📂 Project Structure

```
OTT-Service-Programme-Catalogue/
│
├── src/
│   ├── RunSQLScript.java          # Executes SQL scripts via JDBC
│   ├── ott_db_create.sql          # Creates database tables
│   ├── ott_db_insert.sql          # Inserts sample data
│   ├── ott_db_alter.sql           # Alters schema if required
│   ├── ott_db_demo_1.sql          # Demo queries and use cases
│   ├── ott_db_demo_2.sql
│   ├── ott_db_demo_3.sql
│   ├── ott_db_demo_4.sql
│   ├── ott_db_demo_5.sql
│
├── lib/
│   └── mysql-connector-j-8.0.32.jar  # MySQL JDBC driver
│
└── README.md
```

---

## 🚀 How to Run

### 1️⃣ Prerequisites
- Java JDK 9 or higher
- MySQL Server
- MySQL Connector/J (included in `lib/`)

### 2️⃣ Compile Java Program
From inside the `src/` directory:
```bash
javac -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript.java
```

### 3️⃣ Execute SQL Scripts
Execute the scripts in the following order:

```bash
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_create.sql
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_insert.sql
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_alter.sql
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_demo_1.sql
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_demo_2.sql
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_demo_3.sql
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_demo_4.sql
java -cp .:../lib/mysql-connector-j-8.0.32.jar RunSQLScript ott_db_demo_5.sql
```

> **Note:** Scripts must be executed in this order to ensure proper database setup, insertion, alteration, and demo queries.

---

## 🧪 Purpose of SQL Files

- **ott_db_create.sql** – Creates all tables (Genres, Programme, Cast_Details, Programme_Cast, Episodes)
- **ott_db_insert.sql** – Inserts sample data into tables
- **ott_db_alter.sql** – Alters schema if needed
- **ott_db_demo_*.sql** – Demonstrates queries and use cases on the database

---

## 🧠 Learning Outcomes

- Design and implement relational database schemas
- Use primary keys, foreign keys, and ENUM types
- Model many-to-many relationships (Programme ↔ Cast)
- Execute SQL scripts using Java JDBC
- Structure an academic database project cleanly