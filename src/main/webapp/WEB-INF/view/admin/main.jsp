<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>For admins</title>
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
<body>
<body class="container-lg">
<h1>Admins actions:</h1>
<div class="btn-group-vertical">
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/admin/orders">Show all orders</a>
    <br/>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/admin/products">Create / Delete product</a>
</div>
</body>
</body>
</html>
