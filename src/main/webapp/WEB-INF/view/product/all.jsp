<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>All products page:</h1>
    <h3 style="color: green">${message}</h3>
    <table class="table table-bordered">
        <tr>
            <th>Product name</th>
            <th>Price</th>
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
                    <a class="btn-success btn-lg" href="${pageContext.request.contextPath}/product/buy?id=${product.id}">Buy</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/">Go to the main page</a>
</div>
</body>
</html>
