<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>HW-26</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
      a {
        text-decoration: none;
      }
      a:hover {
        text-decoration: none;
      }
    </style>
  </head>
  <body class="container-lg">
  <h1>Internet shop project</h1>
  <div class="btn-group-vertical">
  <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/data-inject">Inject test users data</a>
  <br/>
  <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/user/all">Show all users</a>
  <br/>
  <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/user/registration">Register</a>
  <br/>
  <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/product/all">Show products list</a>
  <br/>
  <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/product/add">Create new product</a>
  <br/>
  <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/shopping-cart/products">Show products in shopping cart</a>
  </div>
  </body>
</html>
