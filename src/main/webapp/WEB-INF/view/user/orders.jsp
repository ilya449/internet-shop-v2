<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>All ${user} orders:</h1>
    <table class="table table-bordered">
        <tr>
            <th>Order ID</th>
            <th>Products</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    <c:out value="${order.id}"/>
                </td>
                <td>
                    <c:forEach var="product" items="${order.products}">
                        <c:out value="Product: ${product.name} costs: ${product.price}"/>
                        <br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/">Go to the main page</a>
</div>
</body>
</html>
