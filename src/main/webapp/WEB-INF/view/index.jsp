<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>HW-26</title>
  </head>
  <body>
  <h1>Internet shop project</h1>
  <a href="${pageContext.request.contextPath}/injectData">Inject test users data</a>
  <br/>
  <a href="${pageContext.request.contextPath}/user/all">Show all users</a>
  <br/>
  <a href="${pageContext.request.contextPath}/user/registration">Register</a>
  <br/>
  <a href="${pageContext.request.contextPath}/product/all">Show products list</a>
  <br/>
  <a href="${pageContext.request.contextPath}/product/addition">Create new product</a>
  <br/>
  <a href="${pageContext.request.contextPath}/shopping-cart/products">Show products in shopping cart</a>
  </body>
</html>
