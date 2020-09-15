<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Login form:</h1>
    <h3 style="color: red">${message}</h3>
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/login">
        <div class="form-group">
            <label>Input your login
                <input type="text" name="login" minlength="4" maxlength="12">
            </label>
        </div>
        <div class="form-group">
            <label>Input your password
                <input type="password" name="pass" minlength="6" maxlength="16">
            </label>
        </div>
        <button class="btn-primary btn-lg" type="submit">Login</button>
        <br/>
    </form>
    <a class="btn-info btn-lg" href="${pageContext.request.contextPath}/user/registration">Register</a>
</div>
</body>
</html>
