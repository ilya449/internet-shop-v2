Internet shop project.
---
## About project
This project is a simple internet shop project and shows main actions which can be performed in internet shop.
It is using N-tire architecture, implemented role-based access control and have DAO, Service, Controller and View layers.
Also, this project contains filters and security part with authorization and authentication. 
---
## What users can do, depends on their roles:
### ADMIN:
- Delete orders
- Create products
- Delete products
- Delete users
- Get access to the list of all users
- Get access to the list of all orders
- Log out

### USER:
- Register
- Log out
- Add products to the shopping cart
- Create order from list of products in shopping cart
- Delete a product from shopping cart
- See her/his orders

### Users, which are not logged in:
 - See the main menu
 - Register
 - Log in
 - Inject data
 - Check products (but when they decide to buy it they will be redirected to the registration page)
---

## Principles, used in this project:
- RBAC
- SOLID
- DRY
- KISS

## Technologies, used in this project:
- Java 8
- Servlets
- Filters
- Maven	
- Tomcat	
- MySQL	
- JDBC 
- JSTL
- JSP and Bootstrap
---
## For developers (guide how to start working with this project)
1. Download and install the JDK
2. Download and install servlet container (I recommend you to chose TomCat)
3. Download and install MySQL Server and MySQL Workbench
4. Setup connection with MySQL using default parameters
    * user: "root"
    * password: "1234"
    * url: jdbc:mysql://localhost:3306/internet_shop?serverTimezone=UTC
5. Create a schema with name "internet_shop"
6. Copy SQL script from init_db.sql in src/main/resources/ folder and run it in MySQL Workbench
- You can't register as admin, so you need to create admin in MySQL Workbench and then you can use this login and pass while logging in to get admin access.
- After that you can run this app and use button "inject data" orr log in as admin and add your own data.
---
# Author
[Illia Chernyshov](https://github.com/ilya449 "GitHub: Illia Chenryshov")
