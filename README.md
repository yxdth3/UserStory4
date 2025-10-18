# Mini-Store 

Mini application with layered architecture (UI / Service / DAO / Domain) and custom exception handling.

## Structure
```
mini-store/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  ├─ com.example.mycompany/
│  │  │  │  ├─ UI/
│  │  │  │  │  └─ App.java
│  │  │  │  ├─ Domain/
│  │  │  │  │  └─ Product.java
│  │  │  │  ├─ DAO/
│  │  │  │  │  ├─ ProductDAO.java
│  │  │  │  │  └─ ProductDAOImpl.java
│  │  │  │  ├─ Service/
│  │  │  │  │  ├─ InventoryServiceLocal.java
│  │  │  │  │  └─ InventoryServiceImpl.java
│  │  │  │  ├─ Exceptions/
│  │  │  │  │  ├─ InvalidDataException.java
│  │  │  │  │  ├─ DuplicateException.java
│  │  │  │  │  └─ PersistenceException.java
│  │  │  │  └─ Util/
│  │  │  │     └─ DBConnection.java

```

## Database setup (MySQL)
Run these SQL statements to create the DB and table:

```sql
CREATE DATABASE IF NOT EXISTS ministore;
USE ministore;

CREATE TABLE IF NOT EXISTS products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL
);
```

## Configuration
```
db.url=jdbc:mysql://localhost:3306/ministore?useSSL=false&serverTimezone=UTC
db.user=root
db.password=Qwe.123*
db.driver=com.mysql.cj.jdbc.Driver
```

## How to run (from IDE - Netbeans)

- Import the project as a Java/Maven project into your IDE.
- Make sure you have the JDBC driver in the classpath.
- Create the database with the structure shown above.
- Run the com.example.ministore.app.MainApp class.
- A menu with JOptionPane will open where you can add/list/update/delete products.

## Architecture 

- UI (UI/App.java): Interface with JOptionPane. It only interacts with the Service.
- Service (InventoryService): Contains business logic and validations.
- DAO (ProductDAO): Accesses the database via JDBC. Translates errors to PersistenceException.
- Domain (Product): Domain entity.
- Exceptions:
InvalidDataException → invalid data.
DuplicateException → duplicate record.
PersistenceException → persistence layer errors.


## Implemented use cases
- Add product (validation and duplicate control))
- List inventory
- Update price
- Update stock
- Delete product
- Find product by name
  
