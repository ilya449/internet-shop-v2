<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
<h1>All users page:</h1>
<table class="table table-bordered">
    <tr>
        <th>User ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}"/>
            </td>
            <td>
                <c:out value="${user.name}"/>
            </td>
            <td>
                <a class="btn-danger btn-lg" href="${pageContext.request.contextPath}/user/delete?id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a class="btn-info btn-lg" href="${pageContext.request.contextPath}/">Go to the main page</a>
</div>
</body>
</html>
