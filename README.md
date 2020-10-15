Internet shop project.
This project is a simple internet shop and show main actions which can be performed in internet shop.

Before running this project, you have to run SQL script in resource folder (init_db.sql) in your MuSQl workbench.
Then you need to set your name and password in `ConnectionUtil`.
After that it is important to configure Tomcat.
Also, you need to import all the dependencies and plugins, used in this project.


Access depends on roles in this project:
ADMIN:
- Delete orders
- Create products
- Delete products
- Delete users
- Get access to the list of all users
- Get access to the list of all orders
- Log out

USER:
- Register
- Log out
- Add products to the shopping cart
- Create order from list of products in shopping cart
- Delete a product from shopping cart
- See her/his orders

Users, which are not logged in can see the main menu, register, log in, inject data and check products, but when they decide to buy it they will be redirected to the registration page.


Principles, used in this project:
-RBAC
-SOLID
-DRY
-KISS

Other technologies, used in this project:
- Java 8
- Servlets
- Filters
- Maven	
- Tomcat	
- MySQL	
- JDBC 
- JSTL
- JSP and Bootstrap
