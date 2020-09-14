<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Registration:</h1>
    <h3 style="color: green">${message}</h3>
    <h3 style="color: red">${invalidDataMessage}</h3>
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/product/add">
        <div class="form-group">
        <label>Input product name
            <input type="text" name="name" minlength="1">
        </label>
        </div>
        <br/>
        <div class="form-group">
        <label>Input product price
            <input type="text" name="price" min="1">
        </label>
        </div>
        <br/>
        <button class="btn-success btn-lg" type="submit">Add product</button>
    </form>
    <a class="btn-primary btn-lg" href="${pageContext.request.contextPath}/">Go to the main page</a>
</div>
</body>
</html>
