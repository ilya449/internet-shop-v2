<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products in shopping cart:</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>All products in shopping cart:</h1>
    <table class="table table-bordered">
        <tr>
            <th>Product name</th>
            <th>Product price</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <c:out value="${product.name}"/>
                </td>
                <td>
                    <c:out value="${product.price}"/>
                </td>
                <td>
                    <a class="btn-danger btn-lg"
                       href="${pageContext.request.contextPath}/shopping-cart/delete?id=${product.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/order/create">
        <button class="btn-success btn-lg" type="submit">Create order</button>
    </form>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/">Go to the main page</a>
</div>
</body>
</html>
